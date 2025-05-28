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

    // 📌 Problema 2: Eliminar por posición
    public void eliminarPosicion(int pos) {
        if (pos < 0) return;
        NodoDoble<T> actual = cabeza;
        int i = 0;
        while (actual != null) {
            if (i == pos) {
                if (actual == cabeza) {
                    cabeza = actual.siguiente;
                    if (cabeza != null) cabeza.anterior = null;
                    else cola = null;
                } else if (actual == cola) {
                    cola = actual.anterior;
                    cola.siguiente = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }
                return;
            }
            actual = actual.siguiente;
            i++;
        }
    }

    // 📌 Problema 2: Eliminar primer elemento
    public void eliminarPrimero() {
        if (cabeza == null) return;
        cabeza = cabeza.siguiente;
        if (cabeza != null) cabeza.anterior = null;
        else cola = null;
    }

    // 📌 Problema 2: Eliminar último elemento
    public void eliminarUltimo() {
        if (cola == null) return;
        cola = cola.anterior;
        if (cola != null) cola.siguiente = null;
        else cabeza = null;
    }
    
    // 📌 Problema 3: Revertir la lista
    public void invertir() {
        NodoDoble<T> actual = cabeza;
        NodoDoble<T> temporal = null;

        while (actual != null) {
            temporal = actual.anterior;
            actual.anterior = actual.siguiente;
            actual.siguiente = temporal;
            actual = actual.anterior;
        }

        if (temporal != null) {
            cabeza = temporal.anterior;
        }
    }

    // 📌 Problema 4: Eliminar duplicados
    public void eliminarDuplicados() {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            NodoDoble<T> comparador = actual.siguiente;
            while (comparador != null) {
                if (actual.dato.equals(comparador.dato)) {
                    NodoDoble<T> dup = comparador;
                    comparador = comparador.siguiente;
                    if (dup == cola) {
                        cola = dup.anterior;
                        cola.siguiente = null;
                    } else {
                        dup.anterior.siguiente = dup.siguiente;
                        if (dup.siguiente != null)
                            dup.siguiente.anterior = dup.anterior;
                    }
                } else {
                    comparador = comparador.siguiente;
                }
            }
            actual = actual.siguiente;
        }
    }
}
