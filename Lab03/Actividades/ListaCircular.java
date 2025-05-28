package Actividades;
public class ListaCircular<T> {
    private Nodo<T> ultimo;

    public ListaCircular() {
        this.ultimo = null;
    }  

    // Insertar un nodo al final
    public void insertar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (ultimo == null) {
            ultimo = nuevo;
            ultimo.siguiente = ultimo; // Apunta a sí mismo
        } else {
            nuevo.siguiente = ultimo.siguiente; // Nuevo apunta al primer nodo
            ultimo.siguiente = nuevo; // Último apunta al nuevo nodo
            ultimo = nuevo; // Actualiza el último nodo
        }
    }

    // Moastrar la lista
    public void mostrar() {
        if (ultimo == null) {
            System.out.println("Lista vacía.");
            return;
        }
        Nodo<T> actual = ultimo.siguiente; // Comienza desde el primer nodo
        do {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente; // Avanza al siguiente nodo
        } while (actual != ultimo.siguiente); // Termina cuando vuelve al inicio
        System.out.println("(inicio)");
    }

    // ACTIVIDAD NRO 1: Metodo para contar elementos
    public int contar() {
        if (ultimo == null) {
            return 0; // Lista vacía
        }
        int contador = 0;
        Nodo<T> actual = ultimo.siguiente; // Comienza desde el primer nodo
        do {
            contador++;
            actual = actual.siguiente; // Avanza al siguiente nodo
        } while (actual != ultimo.siguiente); // Termina cuando vuelve al inicio
        return contador;
    }

    // ACTIVIDAD NRO 2: Metodo buscar un elemento
    public boolean buscar(T dato) {
        if (ultimo == null) {
            return false; // Lista vacía
        }
        Nodo<T> actual = ultimo.siguiente; // Comienza desde el primer nodo
        do {
            if (actual.dato == dato) {
                return true; // Elemento encontrado
            }
            actual = actual.siguiente; // Avanza al siguiente nodo
        } while (actual != ultimo.siguiente); // Termina cuando vuelve al inicio
        return false; // Elemento no encontrado
    }

    // ACTIVIDAD NRO 3: Metodo para aceptar datos tipo String (usando generics)
    // - Para esta parte generalizamos la clase Nodo y ListaCircular para aceptar cualquier tipo de dato

}
