package entrega.productos;

import entrega.exception.ResourceNotFoundException;
import entrega.model.Producto;
import entrega.repository.ProductoRepository;
import entrega.service.ProductoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarTodos() {
        // Arrange
        Producto p1 = new Producto("Laptop", "Gamer", 1200.0, 5);
        Producto p2 = new Producto("Mouse", "Inalámbrico", 25.0, 50);
        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        // Act
        List<Producto> resultados = productoService.listarTodos();

        // Assert
        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorIdExitoso() {
        // Arrange
        Long id = 1L;
        Producto producto = new Producto("Teclado", "Mecánico", 80.0, 10);
        producto.setId(id);
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        // Act
        Producto resultado = productoService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Teclado", resultado.getNombre());
        verify(productoRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontradoLanzaExcepcion() {
        // Arrange
        Long id = 99L;
        when(productoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            productoService.buscarPorId(id);
        });
        verify(productoRepository, times(1)).findById(id);
    }

    @Test
    void testCrearProducto() {
        // Arrange
        Producto nuevo = new Producto("Monitor", "4K", 300.0, 7);
        when(productoRepository.save(any(Producto.class))).thenReturn(nuevo);

        // Act
        Producto resultado = productoService.crear(nuevo);

        // Assert
        assertNotNull(resultado);
        assertEquals("Monitor", resultado.getNombre());
        verify(productoRepository, times(1)).save(nuevo);
    }

    @Test
    void testActualizarProductoExitoso() {
        // Arrange
        Long id = 1L;
        Producto existente = new Producto("Antiguo", "Desc", 100.0, 2);
        existente.setId(id);

        Producto datosActualizados = new Producto("Nuevo Nombre", "Nueva Desc", 150.0, 4);

        when(productoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(productoRepository.save(any(Producto.class))).thenReturn(existente);

        // Act
        Producto resultado = productoService.actualizar(id, datosActualizados);

        // Assert
        assertNotNull(resultado);
        assertEquals("Nuevo Nombre", resultado.getNombre());
        assertEquals(150.0, resultado.getPrecio());
        verify(productoRepository, times(1)).findById(id);
        verify(productoRepository, times(1)).save(existente);
    }

    @Test
    void testEliminarProductoExitoso() {
        // Arrange
        Long id = 1L;
        Producto existente = new Producto("A borrar", "Desc", 50.0, 1);
        existente.setId(id);

        when(productoRepository.findById(id)).thenReturn(Optional.of(existente));
        doNothing().when(productoRepository).delete(existente);

        // Act
        productoService.eliminar(id);

        // Assert
        verify(productoRepository, times(1)).findById(id);
        verify(productoRepository, times(1)).delete(existente);
    }
}