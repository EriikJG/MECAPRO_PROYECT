package logica;

import org.junit.Before;
import org.junit.Test;
import persistencia.AutomovilJpaController;
import persistencia.InsumoJpaController;
import persistencia.InventarioJpaController;
import persistencia.ServicioJpaController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControladoraTest {
    private Controladora controladora;
    private AutomovilJpaController mockAutomovilJpaController;
    private InsumoJpaController mockInsumoJpaController;
    private InventarioJpaController mockInventarioJpaController;


    private ServicioJpaController mockServicioJpaController;
    @Before
    public void setUp() {
        mockInsumoJpaController = mock(InsumoJpaController.class);
        mockInventarioJpaController = mock(InventarioJpaController.class);
        mockAutomovilJpaController = mock(AutomovilJpaController.class);
        mockServicioJpaController = mock(ServicioJpaController.class); // Add this line
        controladora = new Controladora();
        controladora.controlAutomovil = mockAutomovilJpaController;
        controladora.controlInsumo = mockInsumoJpaController;
        controladora.controlInventario = mockInventarioJpaController;
        controladora.controlServicio = mockServicioJpaController; // Initialize controlServicio

    }


    @Test
    public void givenValidPlaca_whenCrearAutomovil_thenAutomovilCreated() {
        String placa = "XYZ-5678";
        String marca = "Honda";
        String anioFab = "2021";
        List<Reparacion> reparaciones = new ArrayList<>();

        when(mockAutomovilJpaController.findAutomovilEntities()).thenReturn(new ArrayList<>());

        controladora.crearAutomovil(placa, marca, anioFab, reparaciones);

        verify(mockAutomovilJpaController, times(1)).create(any(Automovil.class));
    }

    @Test
    public void givenInvalidPlaca_whenCrearAutomovil_thenAutomovilNotCreated() {
        String placa = "123-XYZ";
        String marca = "Ford";
        String anioFab = "2022";
        List<Reparacion> reparaciones = new ArrayList<>();

        controladora.crearAutomovil(placa, marca, anioFab, reparaciones);

        verify(mockAutomovilJpaController, never()).create(any(Automovil.class));
    }
    @Test
    public void givenPlaca_whenEncontrarAuto_thenReturnAutomovil() {
        Automovil auto = new Automovil("ABC-1234", "Toyota", "2020", null);
        when(mockAutomovilJpaController.findAutomovilEntities()).thenReturn(Arrays.asList(auto));

        Automovil result = controladora.encontrarAuto("ABC-1234");

        assertNotNull(result);
        assertEquals("Toyota", result.getMarca());
        assertEquals("2020", result.getAñoFabricacion());
        verify(mockAutomovilJpaController, times(1)).findAutomovilEntities();
    }


    @Test
    public void givenValidData_whenCrearReparacion_thenReparacionCreated() {
        Automovil automovil = new Automovil("ABC-1234", "Toyota", "2020", new ArrayList<>());
        when(mockAutomovilJpaController.findAutomovilEntities()).thenReturn(Arrays.asList(automovil));

        controladora.crearReparacion("Cambio de aceite", "50", "Mantenimiento", automovil);

        verify(controladora.controlServicio, times(1)).create(any(Reparacion.class));
        assertEquals(1, automovil.getReparaciones().size());
    }


    @Test
    public void givenValidData_whenCrearInsumo_thenInsumoCreated() {
        Inventario inventario = new Inventario("Inventario Principal", new Date(), new Date(), new ArrayList<>());
        when(controladora.controlInventario.findInventario(1)).thenReturn(inventario);

        controladora.crearInsumo("Aceite", 10, "ACE123", inventario);

        verify(controladora.controlInsumo, times(1)).create(any(Insumo.class));
    }


}
