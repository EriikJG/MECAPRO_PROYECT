package logica;

import java.util.regex.Pattern;
import persistencia.AutomovilJpaController;
import persistencia.InsumoJpaController;
import persistencia.InventarioJpaController;
import persistencia.ServicioJpaController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
    public class Controladora {
        AutomovilJpaController controlAutomovil = new AutomovilJpaController();

        ServicioJpaController controlServicio = new ServicioJpaController();
        InventarioJpaController controlInventario = new InventarioJpaController();

        InsumoJpaController controlInsumo = new InsumoJpaController();

        public Automovil encontrarAuto(String placa) {
            List<Automovil> listaAutomoviles = controlAutomovil.findAutomovilEntities();
            for (Automovil auto : listaAutomoviles) {
                if (auto.getPlaca().equals(placa)) {
                    return auto;
                }
            }
            return null;
        }

        public void crearAutomovil(String placa, String marca, String anioFab, List<Reparacion> reparaciones) {

            // Expresión regular para el formato de placa ecuatoriana
            String placaPattern = "^[A-Z]{3}-\\d{4}$";

            if (!Pattern.matches(placaPattern, placa)) {
                System.out.println("La placa " + placa + " no tiene un formato válido.");
                return;
            }

            Automovil autoExistente = encontrarAuto(placa);
            if (autoExistente == null) {
                Automovil auto = new Automovil();
                aniadirDatosAutomovil(placa, marca, anioFab, reparaciones, auto);
                controlAutomovil.create(auto);
            } else {
                System.out.println("El automóvil con la placa " + placa + " ya está registrado.");
            }

        }

        private static void aniadirDatosAutomovil(String placa, String marca, String anioFab, List<Reparacion> reparaciones, Automovil auto) {
            auto.setPlaca(placa);
            auto.setMarca(marca);
            auto.setAñoFabricacion(anioFab);
            auto.setReparaciones(reparaciones);
        }

        public List<Reparacion> getReparaciones() {
            return controlServicio.findReparacionEntities();
        }

        public void crearReparacion(String descripcion, String costo, String tipoReparacion, Automovil automovil) {
            if (descripcion == null || descripcion.isEmpty() || costo == null || costo.isEmpty()) {
                throw new IllegalArgumentException("La descripción y el costo no pueden estar vacíos");
            }
            Reparacion reparacion = new Reparacion();
            reparacion.setDescripcion(descripcion);
            reparacion.setCosto(costo);
            reparacion.setTipo(tipoReparacion);
            reparacion.setAutomovil(automovil);

            controlServicio.create(reparacion);
            aniadirReparacion(automovil, reparacion);
        }


        private void aniadirReparacion(Automovil automovil, Reparacion reparacion) {
            List<Reparacion> reparaciones = automovil.getReparaciones();
            if (reparaciones == null) {
                reparaciones = new ArrayList<>();
                automovil.setReparaciones(reparaciones);
            }
            reparaciones.add(reparacion);
        }

        public void crearInsumo(String nombreInsumo, int cantidadInsumo, String codigoInsumo, Inventario inventario) {
 // Verificar si la cédula excede el máximo de caracteres permitidos
            if (encontrarInsumo(codigoInsumo)) {
                throw new IllegalArgumentException("El insumo ya existe");

            }

            Insumo insumo = new Insumo(nombreInsumo, cantidadInsumo, codigoInsumo, inventario);
            controlInsumo.create(insumo);
        }

        private boolean encontrarInsumo(String codigoInsumo) {
            List<Insumo> insumos =  controlInsumo.findInsumoEntities();
            for (Insumo insumo : insumos) {
                if (insumo.getCodigo().equals(codigoInsumo)) {
                    return true;
                }
            }
            return false;
        }

        public void crearInventario(String nombre, Date fechaCreacion, Date fechaActualizacion, List<Insumo> insumos) {
            Inventario inventarioControler = controlInventario.findInventario(1);

            if (inventarioControler == null || !nombre.equals(inventarioControler.getNombre())) {
                Inventario inventario = new Inventario(nombre, fechaCreacion, fechaActualizacion, insumos);
                controlInventario.create(inventario);
            }
        }

        public Inventario encontrarInventario(int id) {

           return controlInventario.findInventario(1);
        }

        public List<Insumo> getInsumos() {
            return controlInsumo.findInsumoEntities();

        }
    }





