package Problemas;

public class NodoDoble<T> {
    T dato;
    NodoDoble<T> anterior, siguiente;

    public NodoDoble(T dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}
