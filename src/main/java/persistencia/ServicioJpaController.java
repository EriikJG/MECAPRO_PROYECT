package persistencia;

import logica.Automovil;
import logica.Reparacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class ServicioJpaController implements Serializable {

    // EntityManagerFactory para la gestión de entidades
    private final EntityManagerFactory emf;

    // Constructor que recibe una instancia de EntityManagerFactory
    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor por defecto que crea una instancia de EntityManagerFactory
    public ServicioJpaController() {
        emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }

    // Método para obtener un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para crear una nueva reparación en la base de datos
    public void create(Reparacion reparacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            // Adjunta el automóvil a la reparación si existe
            reparacion.setAutomovil(adjuntarAutomovil(em, reparacion.getAutomovil()));
            // Persiste la reparación en la base de datos
            em.persist(reparacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método privado para adjuntar un automóvil a la reparación
    private Automovil adjuntarAutomovil(EntityManager em, Automovil automovil) {
        if (automovil != null) {
            // Obtiene una referencia al automóvil desde la base de datos
            automovil = em.getReference(Automovil.class, automovil.getId());
        }
        return automovil;
    }

    public List<Reparacion> findReparacionEntities() {
        return findReparacionEntities(true, -1, -1);
    }

    private List<Reparacion> findReparacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reparacion.class));
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

