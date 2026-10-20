package listas;

import java.util.NoSuchElementException;

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

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        // TODO Etapa 1: escriba su código aquí
        throw new UnsupportedOperationException("TODO: estaVacia");
    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        // TODO Etapa 1: escriba su código aquí
        throw new UnsupportedOperationException("TODO: tamanio");
    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
        // TODO Etapa 2: cree un Nodo, enlácelo a la cabeza actual
        //               y actualice la cabeza y la cantidad.
        throw new UnsupportedOperationException("TODO: agregarAlInicio");
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {
        // TODO Etapa 2: si la lista está vacía es igual que agregarAlInicio;
        //               si no, recorra hasta el último nodo y enlace el nuevo.
        throw new UnsupportedOperationException("TODO: agregarAlFinal");
    }

    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {
        // TODO Etapa 3: escriba su código aquí
        throw new UnsupportedOperationException("TODO: obtenerPrimero");
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerUltimo() {
        // TODO Etapa 3: escriba su código aquí
        throw new UnsupportedOperationException("TODO: obtenerUltimo");
    }

    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {
        // TODO Etapa 3: valide el índice y recorra la lista 'indice' veces.
        throw new UnsupportedOperationException("TODO: obtener");
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {
        // TODO Etapa 4: use indiceDe o recorra la lista.
        //               Cuidado: para comparar use Objects.equals(a, b).
        throw new UnsupportedOperationException("TODO: contiene");
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        // TODO Etapa 4: escriba su código aquí
        throw new UnsupportedOperationException("TODO: indiceDe");
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
        // TODO Etapa 5: casos: indice 0 (inicio), o detenerse en el nodo
        //               ANTERIOR a la posición y enlazar allí el nuevo nodo.
        throw new UnsupportedOperationException("TODO: insertar");
    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {
        // TODO Etapa 5: escriba su código aquí
        throw new UnsupportedOperationException("TODO: reemplazar");
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {
        // TODO Etapa 6: mueva la cabeza al siguiente nodo.
        throw new UnsupportedOperationException("TODO: eliminarPrimero");
    }

    /**
     * Elimina y retorna el último elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {
        // TODO Etapa 6: hay que detenerse en el PENULTIMO nodo.
        //               Atención al caso de un solo elemento.
        throw new UnsupportedOperationException("TODO: eliminarUltimo");
    }

    /**
     * Elimina y retorna el elemento de la posición indicada.
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {
        // TODO Etapa 6: escriba su código aquí
        throw new UnsupportedOperationException("TODO: eliminar(int)");
    }

    /**
     * Elimina la primera aparición del dato.
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {
        // TODO Etapa 6: puede apoyarse en indiceDe y eliminar(int).
        throw new UnsupportedOperationException("TODO: eliminarValor");
    }

    /** Deja la lista vacía. */
    public void vaciar() {
        // TODO Etapa 6: escriba su código aquí
        throw new UnsupportedOperationException("TODO: vaciar");
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
        // TODO Etapa 7: recorra la lista con un StringBuilder.
        throw new UnsupportedOperationException("TODO: toString");
    }

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {
        // TODO Etapa 8: use tres referencias: anterior, actual, siguiente.
        throw new UnsupportedOperationException("TODO: invertir");
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {
        // TODO Etapa 8: escriba su código aquí
        throw new UnsupportedOperationException("TODO: obtenerMedio");
    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        // TODO Etapa 8: escriba su código aquí
        throw new UnsupportedOperationException("TODO: eliminarDuplicados");
    }
}
