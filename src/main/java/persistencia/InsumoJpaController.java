package persistencia;

import logica.Automovil;
import logica.Insumo;
import logica.Inventario;
import logica.Reparacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InsumoJpaController implements Serializable {

    public InsumoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    // Método para obtener un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Constructor por defecto que inicializa el EntityManagerFactory
    public InsumoJpaController() {
        emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }

    // Método para crear un nuevo registro de Automovil en la base de datos
    public void create(Insumo insumo) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Adjunta el inventario al insumo si existe
            adjuntarInventarioSiExiste(em, insumo);
            // Persiste el automóvil en la base de datos
            em.persist(insumo);
            // Actualiza la relación bidireccional con la mecánica, si existe
            actualizarRelacionClienteSiExiste(em, insumo);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método privado para adjuntar la mecánica al automóvil si existe
    private void adjuntarInventarioSiExiste(EntityManager em, Insumo insumo) {
        Inventario inventario = insumo.getInventario();
        if (inventario != null) {
            inventario = em.getReference(inventario.getClass(), inventario.getId());
            insumo.setInventario(inventario);
        }
    }

    // Método privado para adjuntar las reparaciones al automóvil si existen
    private void adjuntarReparaciones(EntityManager em, Automovil automovil) {
        List<Reparacion> reparaciones = automovil.getReparaciones();
        if (reparaciones != null && !reparaciones.isEmpty()) {
            List<Reparacion> reparacionesAdjuntas = new ArrayList<>();
            for (Reparacion reparacion : reparaciones) {
                reparacionesAdjuntas.add(em.getReference(reparacion.getClass(), reparacion.getId()));
            }
            automovil.setReparaciones(reparacionesAdjuntas);
        }
    }

    // Método privado para actualizar la relación con la mecánica si existe
    private void actualizarRelacionClienteSiExiste(EntityManager em, Insumo insumo) {
        Inventario inventario = insumo.getInventario();
        if (inventario != null) {
            inventario.getInsumos().add(insumo);
            inventario = em.merge(inventario);
        }
    }

    // Método para encontrar todas las entidades Automovil
    public List<Insumo> findInsumoEntities() {
        return findInsumoEntities(true, -1, -1);
    }

    // Método privado para encontrar las entidades Automovil con opciones de paginación
    private List<Insumo> findInsumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Insumo> cq = em.getCriteriaBuilder().createQuery(Insumo.class);
            cq.select(cq.from(Insumo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}