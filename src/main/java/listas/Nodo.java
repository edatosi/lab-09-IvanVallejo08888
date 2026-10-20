package listas;

/**
 * Nodo de una lista simplemente enlazada.
 * Guarda un dato y una referencia al siguiente nodo (null si es el último).
 *
 * Esta clase YA está completa: no es necesario modificarla.
 */
public class Nodo<T> {

    private T dato;
    private Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
