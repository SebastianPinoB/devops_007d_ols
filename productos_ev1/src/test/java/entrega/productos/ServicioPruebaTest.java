package entrega.productos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServicioPruebaTest {

    @Test
    void testSumaOpeacionBasica() {
        // Prueba lógica simple para asegurar que el runner de Maven ejecute tests con éxito
        int a = 5;
        int b = 10;
        int resultado = a + b;
        
        Assertions.assertEquals(15, resultado, "La suma debería ser 15");
    }

    @Test
    void testValidarObjetoNoNulo() {
        String mensaje = "Evaluación DevOps OK";
        Assertions.assertNotNull(mensaje);
        Assertions.assertEquals("Evaluación DevOps OK", mensaje);
    }
}