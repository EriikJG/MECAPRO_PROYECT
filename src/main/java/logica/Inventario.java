package logica;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;





    @OneToMany(mappedBy = "inventario")
    private List<Insumo> insumos;

    public Inventario( String nombre, Date fechaCreacion, Date fechaActualizacion, List<Insumo> insumos) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.insumos = insumos;
    }

    public Inventario() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }



    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }
}
