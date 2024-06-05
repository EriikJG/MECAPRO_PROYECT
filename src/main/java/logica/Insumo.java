package logica;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
public class Insumo  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int cantidad;
    private String Codigo;
    @ManyToOne
    @JoinColumn(name="id_inventario")
    private Inventario inventario;

    public Insumo() {
    }

    public Insumo( String nombre, int cantidad, String codigo, Inventario inventario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        Codigo = codigo;
        this.inventario = inventario;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
