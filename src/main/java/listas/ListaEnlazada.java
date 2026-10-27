package listas;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista simplemente enlazada genérica.
 *
 * INSTRUCCIONES PARA EL ESTUDIANTE
 * --------------------------------
 * 1. Cada método marcado con "TODO" lanza UnsupportedOperationException.
 *    Reemplace esa línea por su implementación.
 * 2. NO modifique las pruebas (src/test). 
 * 3. Resuelva en orden (Etapa 1, 2, 3...): cada etapa usa las anteriores.
 * 4. Solo se permite usar nodos propios (clase Nodo). No use
 *    java.util.LinkedList ni ArrayList dentro de esta clase.
 */
public class ListaEnlazada<T> {

    /** Primer nodo de la lista (null cuando la lista está vacía). */
    private Nodo<T> cabeza;

    /** Cantidad de elementos. Debe mantenerse actualizada en cada operación. */
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    /**
     * Valida que el índice esté en el rango [0, limite).
     *
     * @param indice posición a validar
     * @param limite valor máximo permitido (exclusivo)
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    private void validarIndice(int indice, int limite) {
        if (indice < 0 || indice >= limite) {
            throw new IndexOutOfBoundsException(
                    "Índice " + indice + " fuera de rango (tamaño: " + cantidad + ")");
        }
    }

    /**
     * Recorre la lista y retorna el nodo que está en la posición indicada.
     * Se asume que el índice ya fue validado.
     */
    private Nodo<T> nodoEn(int indice) {
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        return cantidad;
    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        // El nuevo nodo apunta a la cabeza actual y pasa a ser la nueva cabeza
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        cantidad++;
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {
        if (estaVacia()) {
            agregarAlInicio(dato);
            return;
        }
        // Se avanza hasta el último nodo (el que no tiene siguiente)
        Nodo<T> ultimo = nodoEn(cantidad - 1);
        ultimo.setSiguiente(new Nodo<>(dato));
        cantidad++;
    }

    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return cabeza.getDato();
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerUltimo() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return nodoEn(cantidad - 1).getDato();
    }

    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {
        validarIndice(indice, cantidad);
        return nodoEn(indice).getDato();
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {
        return indiceDe(dato) != -1;
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        Nodo<T> actual = cabeza;
        int posicion = 0;
        while (actual != null) {
            // Objects.equals compara correctamente aunque alguno de los dos sea null
            if (Objects.equals(actual.getDato(), dato)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }
        return -1;
    }

    // =====================================================================
    // ETAPA 5. Insertar y reemplazar en cualquier posición
    // =====================================================================

    /**
     * Inserta el dato en la posición indicada, desplazando los siguientes.
     * Se permite indice == tamanio() (equivale a agregar al final).
     *
     * @throws IndexOutOfBoundsException si indice < 0 o indice > tamanio()
     */
    public void insertar(int indice, T dato) {
        // Aquí se permite indice == cantidad, por eso el límite es cantidad + 1
        validarIndice(indice, cantidad + 1);
        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        // Nos detenemos en el nodo ANTERIOR a la posición de inserción
        Nodo<T> anterior = nodoEn(indice - 1);
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevo);
        cantidad++;
    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {
        validarIndice(indice, cantidad);
        Nodo<T> nodo = nodoEn(indice);
        T datoAnterior = nodo.getDato();
        nodo.setDato(dato);
        return datoAnterior;
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        T dato = cabeza.getDato();
        // La cabeza avanza al siguiente nodo; el anterior queda sin referencias
        cabeza = cabeza.getSiguiente();
        cantidad--;
        return dato;
    }

    /**
     * Elimina y retorna el último elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        // Con un solo elemento, eliminar el último equivale a eliminar el primero
        if (cantidad == 1) {
            return eliminarPrimero();
        }
        // Nos detenemos en el penúltimo nodo para desenlazar al último
        Nodo<T> penultimo = nodoEn(cantidad - 2);
        T dato = penultimo.getSiguiente().getDato();
        penultimo.setSiguiente(null);
        cantidad--;
        return dato;
    }

    /**
     * Elimina y retorna el elemento de la posición indicada.
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {
        validarIndice(indice, cantidad);
        if (indice == 0) {
            return eliminarPrimero();
        }
        // El nodo anterior "salta" sobre el nodo que se quiere eliminar
        Nodo<T> anterior = nodoEn(indice - 1);
        Nodo<T> aEliminar = anterior.getSiguiente();
        anterior.setSiguiente(aEliminar.getSiguiente());
        cantidad--;
        return aEliminar.getDato();
    }

    /**
     * Elimina la primera aparición del dato.
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {
        int indice = indiceDe(dato);
        if (indice == -1) {
            return false;
        }
        eliminar(indice);
        return true;
    }

    /** Deja la lista vacía. */
    public void vaciar() {
        cabeza = null;
        cantidad = 0;
    }

    // =====================================================================
    // ETAPA 7. Representación en texto
    // =====================================================================

    /**
     * Formato: "[1, 2, 3]". Lista vacía: "[]".
     * Un elemento null se imprime como "null".
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.getDato());   // StringBuilder imprime null como "null"
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {
        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            Nodo<T> siguiente = actual.getSiguiente(); // guardamos el resto de la lista
            actual.setSiguiente(anterior);             // invertimos el enlace
            anterior = actual;                         // avanzamos los punteros
            actual = siguiente;
        }
        // El último nodo visitado es la nueva cabeza
        cabeza = anterior;
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        // Puntero lento avanza de a 1 y el rápido de a 2:
        // cuando el rápido llega al final, el lento está en el medio
        Nodo<T> lento = cabeza;
        Nodo<T> rapido = cabeza;
        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        return lento.getDato();
    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            // Se eliminan todos los nodos posteriores iguales al actual
            Nodo<T> anterior = actual;
            Nodo<T> revisado = actual.getSiguiente();
            while (revisado != null) {
                if (Objects.equals(actual.getDato(), revisado.getDato())) {
                    anterior.setSiguiente(revisado.getSiguiente());
                    cantidad--;
                } else {
                    anterior = revisado;
                }
                revisado = revisado.getSiguiente();
            }
            actual = actual.getSiguiente();
        }
    }
}