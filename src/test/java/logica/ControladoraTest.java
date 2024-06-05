package logica;

import org.junit.Before;
import org.junit.Test;
import persistencia.*;

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
        mockAutomovilJpaController = mock(AutomovilJpaController.class);
        mockInsumoJpaController = mock(InsumoJpaController.class);
        mockInventarioJpaController = mock(InventarioJpaController.class);
        mockServicioJpaController = mock(ServicioJpaController.class);

        controladora = new Controladora();
        controladora.controlAutomovil = mockAutomovilJpaController;
        controladora.controlInsumo = mockInsumoJpaController;
        controladora.controlInventario = mockInventarioJpaController;
        controladora.controlServicio = mockServicioJpaController;
    }

    @Test
    public void givenValidPlaca_whenCrearAutomovil_thenAutomovilCreated() {
        String placa = "ABC-1234";
        String marca = "Toyota";
        String anioFab = "2022";
        List<Reparacion> reparaciones = new ArrayList<>();

        when(mockAutomovilJpaController.findAutomovilEntities()).thenReturn(new ArrayList<>());

        controladora.crearAutomovil(placa, marca, anioFab, reparaciones);

        verify(mockAutomovilJpaController, times(1)).create(any(Automovil.class));
    }

    @Test
    public void givenInvalidPlaca_whenCrearAutomovil_thenAutomovilNotCreated() {
        String placa = "123-ABC";
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

        verify(mockServicioJpaController, times(1)).create(any(Reparacion.class));
        assertEquals(1, automovil.getReparaciones().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidData_whenCrearReparacion_thenThrowException() {
        Automovil automovil = new Automovil("ABC-1234", "Toyota", "2020", new ArrayList<>());

        controladora.crearReparacion("", "", "Mantenimiento", automovil);
    }

    @Test
    public void givenValidData_whenCrearInsumo_thenInsumoCreated() {
        Inventario inventario = new Inventario("Inventario Principal", new Date(), new Date(), new ArrayList<>());
        when(mockInventarioJpaController.findInventario(1)).thenReturn(inventario);
        when(mockInsumoJpaController.findInsumoEntities()).thenReturn(new ArrayList<>());

        controladora.crearInsumo("Aceite", 10, "ACE123", inventario);

        verify(mockInsumoJpaController, times(1)).create(any(Insumo.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenDuplicateCodigoInsumo_whenCrearInsumo_thenThrowException() {
        Inventario inventario = new Inventario("Inventario Principal", new Date(), new Date(), new ArrayList<>());
        Insumo insumoExistente = new Insumo("Aceite", 10, "ACE123", inventario);

        when(mockInventarioJpaController.findInventario(1)).thenReturn(inventario);
        when(mockInsumoJpaController.findInsumoEntities()).thenReturn(Arrays.asList(insumoExistente));

        controladora.crearInsumo("Aceite", 10, "ACE123", inventario);
    }

    @Test
    public void givenValidData_whenCrearInventario_thenInventarioCreated() {
        List<Insumo> insumos = new ArrayList<>();
        Date fechaCreacion = new Date();
        Date fechaActualizacion = new Date();
        when(mockInventarioJpaController.findInventario(1)).thenReturn(null);

        controladora.crearInventario("Inventario Principal", fechaCreacion, fechaActualizacion, insumos);

        verify(mockInventarioJpaController, times(1)).create(any(Inventario.class));
    }

    @Test
    public void givenExistingInventario_whenCrearInventario_thenInventarioNotCreated() {
        List<Insumo> insumos = new ArrayList<>();
        Date fechaCreacion = new Date();
        Date fechaActualizacion = new Date();
        Inventario inventarioExistente = new Inventario("Inventario Principal", fechaCreacion, fechaActualizacion, insumos);

        when(mockInventarioJpaController.findInventario(1)).thenReturn(inventarioExistente);

        controladora.crearInventario("Inventario Principal", fechaCreacion, fechaActualizacion, insumos);

        verify(mockInventarioJpaController, never()).create(any(Inventario.class));
    }

    @Test
    public void whenEncontrarInventario_thenReturnInventario() {
        Inventario inventario = new Inventario("Inventario Principal", new Date(), new Date(), new ArrayList<>());
        when(mockInventarioJpaController.findInventario(1)).thenReturn(inventario);

        Inventario result = controladora.encontrarInventario(1);

        assertNotNull(result);
        assertEquals("Inventario Principal", result.getNombre());
        verify(mockInventarioJpaController, times(1)).findInventario(1);
    }

    @Test
    public void whenGetInsumos_thenReturnInsumoList() {
        List<Insumo> insumos = Arrays.asList(new Insumo("Aceite", 10, "ACE123", null));
        when(mockInsumoJpaController.findInsumoEntities()).thenReturn(insumos);

        List<Insumo> result = controladora.getInsumos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Aceite", result.get(0).getNombre());
        verify(mockInsumoJpaController, times(1)).findInsumoEntities();
    }
}

