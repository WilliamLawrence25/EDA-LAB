package Problemas;

public class ListaDoble<T extends Comparable<T>> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;

    public ListaDoble() {
        this.cabeza = null;
        this.cola = null;
    }

    // 📌 PROBLEMA 1: Insertar al inicio
    public void insertarInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
    }

    // 📌 Problema 1: Insertar al final
    public void insertarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cola == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    // Mostrar en orden normal
    public void mostrarNormal() {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ⇄ ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    // Mostrar en orden reverso
    public void mostrarReverso() {
        NodoDoble<T> actual = cola;
        while (actual != null) {
            System.out.print(actual.dato + " ⇄ ");
            actual = actual.anterior;
        }
        System.out.println("null");
    }
    
}
