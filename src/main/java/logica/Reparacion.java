package logica;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reparacion implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    private String costo;
    private String Tipo;
    @ManyToOne
    @JoinColumn(name="id_automovil")
    private Automovil automovil;

    public Reparacion() {
    }

    public Reparacion( String descripcion, String costo, String tipo, Automovil automovil) {
        this.descripcion = descripcion;
        this.costo = costo;
        Tipo = tipo;
        this.automovil = automovil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }
}
