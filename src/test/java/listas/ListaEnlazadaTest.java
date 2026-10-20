package listas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Pruebas del taller de lista enlazada.
 * NO MODIFIQUE ESTE ARCHIVO: su trabajo es hacer que pasen escribiendo
 * código en src/main/java/listas/ListaEnlazada.java
 */
class ListaEnlazadaTest {

    ListaEnlazada<Integer> lista;

    @BeforeEach
    void crearLista() {
        lista = new ListaEnlazada<>();
    }

    /** Crea una lista con los valores dados, usando solo agregarAlFinal. */
    private static ListaEnlazada<Integer> de(Integer... valores) {
        ListaEnlazada<Integer> l = new ListaEnlazada<>();
        for (Integer v : valores) {
            l.agregarAlFinal(v);
        }
        return l;
    }


    /**
     * Verifica tamaño y contenido usando solo tamanio() y obtener(i)
     * (disponibles desde la Etapa 3; no depende de toString).
     */
    private static void assertLista(ListaEnlazada<Integer> l, Integer... esperado) {
        assertEquals(esperado.length, l.tamanio(), "tamaño");
        for (int i = 0; i < esperado.length; i++) {
            assertEquals(esperado[i], l.obtener(i), "elemento en la posición " + i);
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 1. Estado de la lista")
    class Etapa1_Estado {

        @Test
        void listaNuevaEstaVacia() {
            assertTrue(lista.estaVacia());
            assertEquals(0, lista.tamanio());
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 2. Agregar en los extremos")
    class Etapa2_AgregarEnLosExtremos {

        // En esta etapa solo se puede comprobar con estaVacia() y tamanio().
        // El ORDEN de los elementos se verifica en la Etapa 3.

        @Test
        void agregarAlInicioEnListaVacia() {
            lista.agregarAlInicio(10);
            assertFalse(lista.estaVacia());
            assertEquals(1, lista.tamanio());
        }

        @Test
        void agregarAlInicioIncrementaElTamanio() {
            lista.agregarAlInicio(1);
            lista.agregarAlInicio(2);
            lista.agregarAlInicio(3);
            assertEquals(3, lista.tamanio());
        }

        @Test
        void agregarAlFinalEnListaVacia() {
            lista.agregarAlFinal(10);
            assertFalse(lista.estaVacia());
            assertEquals(1, lista.tamanio());
        }

        @Test
        void agregarAlFinalIncrementaElTamanio() {
            lista.agregarAlFinal(1);
            lista.agregarAlFinal(2);
            lista.agregarAlFinal(3);
            assertEquals(3, lista.tamanio());
        }

        @Test
        void sePuedenCombinarInicioYFinal() {
            lista.agregarAlFinal(2);
            lista.agregarAlInicio(1);
            lista.agregarAlFinal(3);
            assertEquals(3, lista.tamanio());
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 3. Consultar elementos")
    class Etapa3_Consultar {

        @Test
        void agregarAlInicioDejaElNuevoPrimero() {
            lista.agregarAlInicio(1);
            lista.agregarAlInicio(2);
            lista.agregarAlInicio(3);
            assertLista(lista, 3, 2, 1);
        }

        @Test
        void agregarAlFinalMantieneElOrden() {
            lista.agregarAlFinal(1);
            lista.agregarAlFinal(2);
            lista.agregarAlFinal(3);
            assertLista(lista, 1, 2, 3);
        }

        @Test
        void combinarInicioYFinalRespetaElOrden() {
            lista.agregarAlFinal(2);
            lista.agregarAlInicio(1);
            lista.agregarAlFinal(3);
            assertLista(lista, 1, 2, 3);
        }

        @Test
        void admiteElementosNull() {
            lista.agregarAlFinal(null);
            assertEquals(1, lista.tamanio());
            assertNull(lista.obtenerPrimero());
        }

        @Test
        void obtenerPrimeroYUltimo() {
            lista = de(5, 6, 7);
            assertEquals(5, lista.obtenerPrimero());
            assertEquals(7, lista.obtenerUltimo());
        }

        @Test
        void primeroYUltimoCoincidenConUnSoloElemento() {
            lista = de(9);
            assertEquals(9, lista.obtenerPrimero());
            assertEquals(9, lista.obtenerUltimo());
        }

        @Test
        void obtenerPorIndice() {
            lista = de(10, 20, 30, 40);
            assertEquals(10, lista.obtener(0));
            assertEquals(20, lista.obtener(1));
            assertEquals(40, lista.obtener(3));
        }

        @Test
        void obtenerNoModificaLaLista() {
            lista = de(1, 2, 3);
            lista.obtener(1);
            lista.obtenerPrimero();
            lista.obtenerUltimo();
            assertEquals(3, lista.tamanio());
            assertLista(lista, 1, 2, 3);
        }

        @Test
        void primeroEnListaVaciaLanzaExcepcion() {
            assertThrows(NoSuchElementException.class, () -> lista.obtenerPrimero());
        }

        @Test
        void ultimoEnListaVaciaLanzaExcepcion() {
            assertThrows(NoSuchElementException.class, () -> lista.obtenerUltimo());
        }

        @Test
        void obtenerConIndiceInvalidoLanzaExcepcion() {
            lista = de(1, 2, 3);
            assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(3));
            assertThrows(IndexOutOfBoundsException.class, () -> new ListaEnlazada<Integer>().obtener(0));
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 4. Búsqueda")
    class Etapa4_Busqueda {

        @Test
        void contieneElementosPresentes() {
            lista = de(1, 2, 3);
            assertTrue(lista.contiene(1));
            assertTrue(lista.contiene(3));
        }

        @Test
        void noContieneElementosAusentes() {
            lista = de(1, 2, 3);
            assertFalse(lista.contiene(99));
            assertFalse(new ListaEnlazada<Integer>().contiene(1));
        }

        @Test
        void contieneUsaEqualsYNoIgualdadDeReferencia() {
            ListaEnlazada<String> nombres = new ListaEnlazada<>();
            nombres.agregarAlFinal(new String("Maria"));
            assertTrue(nombres.contiene(new String("Maria")));
        }

        @Test
        void contieneYIndiceDeAdmitenNull() {
            lista = de(1, null, 3);
            assertTrue(lista.contiene(null));
            assertEquals(1, lista.indiceDe(null));
            assertFalse(de(1, 2).contiene(null));
        }

        @Test
        void indiceDeRetornaLaPosicion() {
            lista = de(10, 20, 30);
            assertEquals(0, lista.indiceDe(10));
            assertEquals(2, lista.indiceDe(30));
        }

        @Test
        void indiceDeRetornaLaPrimeraAparicion() {
            lista = de(7, 8, 7, 8);
            assertEquals(0, lista.indiceDe(7));
            assertEquals(1, lista.indiceDe(8));
        }

        @Test
        void indiceDeRetornaMenosUnoSiNoExiste() {
            assertEquals(-1, de(1, 2, 3).indiceDe(99));
            assertEquals(-1, new ListaEnlazada<Integer>().indiceDe(1));
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 5. Insertar y reemplazar")
    class Etapa5_InsertarYReemplazar {

        @Test
        void insertarAlInicio() {
            lista = de(2, 3);
            lista.insertar(0, 1);
            assertLista(lista, 1, 2, 3);
            assertEquals(3, lista.tamanio());
        }

        @Test
        void insertarEnElMedio() {
            lista = de(1, 3, 4);
            lista.insertar(1, 2);
            assertLista(lista, 1, 2, 3, 4);
            assertEquals(4, lista.tamanio());
        }

        @Test
        void insertarAlFinalConIndiceIgualAlTamanio() {
            lista = de(1, 2);
            lista.insertar(2, 3);
            assertLista(lista, 1, 2, 3);
            assertEquals(3, lista.obtenerUltimo());
        }

        @Test
        void insertarEnListaVacia() {
            lista.insertar(0, 5);
            assertLista(lista, 5);
        }

        @Test
        void insertarConIndiceInvalidoLanzaExcepcion() {
            lista = de(1, 2);
            assertThrows(IndexOutOfBoundsException.class, () -> lista.insertar(-1, 9));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.insertar(3, 9));
            assertLista(lista, 1, 2);
        }

        @Test
        void reemplazarRetornaElValorAnterior() {
            lista = de(1, 2, 3);
            assertEquals(2, lista.reemplazar(1, 20));
            assertLista(lista, 1, 20, 3);
            assertEquals(3, lista.tamanio());
        }

        @Test
        void reemplazarEnLosExtremos() {
            lista = de(1, 2, 3);
            lista.reemplazar(0, 10);
            lista.reemplazar(2, 30);
            assertLista(lista, 10, 2, 30);
        }

        @Test
        void reemplazarConIndiceInvalidoLanzaExcepcion() {
            lista = de(1, 2);
            assertThrows(IndexOutOfBoundsException.class, () -> lista.reemplazar(-1, 9));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.reemplazar(2, 9));
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 6. Eliminar")
    class Etapa6_Eliminar {

        @Test
        void eliminarPrimeroRetornaYQuita() {
            lista = de(1, 2, 3);
            assertEquals(1, lista.eliminarPrimero());
            assertLista(lista, 2, 3);
            assertEquals(2, lista.tamanio());
        }

        @Test
        void eliminarPrimeroHastaVaciar() {
            lista = de(1, 2);
            lista.eliminarPrimero();
            lista.eliminarPrimero();
            assertTrue(lista.estaVacia());
            assertLista(lista);
        }

        @Test
        void eliminarPrimeroEnListaVaciaLanzaExcepcion() {
            assertThrows(NoSuchElementException.class, () -> lista.eliminarPrimero());
        }

        @Test
        void eliminarUltimoRetornaYQuita() {
            lista = de(1, 2, 3);
            assertEquals(3, lista.eliminarUltimo());
            assertLista(lista, 1, 2);
            assertEquals(2, lista.tamanio());
        }

        @Test
        void eliminarUltimoConUnSoloElemento() {
            lista = de(7);
            assertEquals(7, lista.eliminarUltimo());
            assertTrue(lista.estaVacia());
            assertEquals(0, lista.tamanio());
        }

        @Test
        void eliminarUltimoEnListaVaciaLanzaExcepcion() {
            assertThrows(NoSuchElementException.class, () -> lista.eliminarUltimo());
        }

        @Test
        void despuesDeEliminarUltimoSePuedeAgregarAlFinal() {
            lista = de(1, 2, 3);
            lista.eliminarUltimo();
            lista.agregarAlFinal(9);
            assertLista(lista, 1, 2, 9);
        }

        @Test
        void eliminarPorIndice() {
            lista = de(10, 20, 30, 40);
            assertEquals(10, lista.eliminar(0));
            assertEquals(30, lista.eliminar(1));
            assertEquals(40, lista.eliminar(1));
            assertLista(lista, 20);
            assertEquals(1, lista.tamanio());
        }

        @Test
        void eliminarPorIndiceInvalidoLanzaExcepcion() {
            lista = de(1, 2);
            assertThrows(IndexOutOfBoundsException.class, () -> lista.eliminar(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.eliminar(2));
            assertThrows(IndexOutOfBoundsException.class, () -> new ListaEnlazada<Integer>().eliminar(0));
            assertEquals(2, lista.tamanio());
        }

        @Test
        void eliminarValorEliminaSoloLaPrimeraAparicion() {
            lista = de(1, 2, 1, 3);
            assertTrue(lista.eliminarValor(1));
            assertLista(lista, 2, 1, 3);
            assertEquals(3, lista.tamanio());
        }

        @Test
        void eliminarValorInexistenteRetornaFalse() {
            lista = de(1, 2, 3);
            assertFalse(lista.eliminarValor(99));
            assertLista(lista, 1, 2, 3);
        }

        @Test
        void eliminarValorFuncionaEnLosExtremos() {
            lista = de(1, 2, 3);
            assertTrue(lista.eliminarValor(3));
            assertTrue(lista.eliminarValor(1));
            assertLista(lista, 2);
        }

        @Test
        void vaciarDejaLaListaVacia() {
            lista = de(1, 2, 3);
            lista.vaciar();
            assertTrue(lista.estaVacia());
            assertEquals(0, lista.tamanio());
            lista.agregarAlFinal(5);
            assertLista(lista, 5);
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 7. Representación en texto")
    class Etapa7_ToString {

        @Test
        void listaVaciaSeImprimeCorchetes() {
            assertEquals("[]", lista.toString());
        }

        @Test
        void unSoloElemento() {
            assertEquals("[42]", de(42).toString());
        }

        @Test
        void variosElementosSeparadosPorComaYEspacio() {
            assertEquals("[1, 2, 3]", de(1, 2, 3).toString());
        }

        @Test
        void elementoNullSeImprimeComoNull() {
            assertEquals("[1, null, 3]", de(1, null, 3).toString());
        }

        @Test
        void funcionaConCadenas() {
            ListaEnlazada<String> platos = new ListaEnlazada<>();
            platos.agregarAlFinal("Ajiaco");
            platos.agregarAlFinal("Sancocho");
            assertEquals("[Ajiaco, Sancocho]", platos.toString());
        }
    }

    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Etapa 8. Retos")
    class Etapa8_Retos {

        @Test
        void invertirListaVaciaYDeUnElemento() {
            lista.invertir();
            assertEquals("[]", lista.toString());

            lista = de(1);
            lista.invertir();
            assertEquals("[1]", lista.toString());
        }

        @Test
        void invertirVariosElementos() {
            lista = de(1, 2, 3, 4, 5);
            lista.invertir();
            assertEquals("[5, 4, 3, 2, 1]", lista.toString());
            assertEquals(5, lista.tamanio());
            assertEquals(5, lista.obtenerPrimero());
            assertEquals(1, lista.obtenerUltimo());
        }

        @Test
        void invertirDosVecesRestauraLaLista() {
            lista = de(1, 2, 3, 4);
            lista.invertir();
            lista.invertir();
            assertEquals("[1, 2, 3, 4]", lista.toString());
        }

        @Test
        void despuesDeInvertirSePuedeAgregarAlFinal() {
            lista = de(1, 2, 3);
            lista.invertir();
            lista.agregarAlFinal(0);
            assertEquals("[3, 2, 1, 0]", lista.toString());
        }

        @Test
        void medioConCantidadImpar() {
            assertEquals(3, de(1, 2, 3, 4, 5).obtenerMedio());
            assertEquals(1, de(1).obtenerMedio());
        }

        @Test
        void medioConCantidadParRetornaElSegundoCentral() {
            assertEquals(3, de(1, 2, 3, 4).obtenerMedio());
            assertEquals(2, de(1, 2).obtenerMedio());
        }

        @Test
        void medioEnListaVaciaLanzaExcepcion() {
            assertThrows(NoSuchElementException.class, () -> lista.obtenerMedio());
        }

        @Test
        void eliminarDuplicadosConservaLaPrimeraAparicion() {
            lista = de(1, 2, 1, 3, 2, 1);
            lista.eliminarDuplicados();
            assertEquals("[1, 2, 3]", lista.toString());
            assertEquals(3, lista.tamanio());
        }

        @Test
        void eliminarDuplicadosSinRepetidosNoCambiaNada() {
            lista = de(1, 2, 3);
            lista.eliminarDuplicados();
            assertEquals("[1, 2, 3]", lista.toString());
        }

        @Test
        void eliminarDuplicadosTodosIguales() {
            lista = de(4, 4, 4, 4);
            lista.eliminarDuplicados();
            assertEquals("[4]", lista.toString());
            assertEquals(1, lista.tamanio());
        }

        @Test
        void eliminarDuplicadosEnListaVacia() {
            lista.eliminarDuplicados();
            assertTrue(lista.estaVacia());
        }
    }
}
