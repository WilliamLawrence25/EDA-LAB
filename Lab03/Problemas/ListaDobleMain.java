package Problemas;

public class ListaDobleMain {
    public static void main(String[] args) {
        ListaDoble<Integer> lista = new ListaDoble<>();

        // Problema 1
        lista.insertarInicio(2);
        lista.insertarFinal(3);
        lista.insertarInicio(1);
        lista.insertarFinal(4);
        System.out.println("Lista en orden:");
        lista.mostrarNormal();
        System.out.println("Lista en reverso:");
        lista.mostrarReverso();

        // Problema 2
        lista.eliminarValor(3);
        lista.eliminarPosicion(1);
        lista.eliminarPrimero();
        lista.eliminarUltimo();
        System.out.println("Después de eliminaciones:");
        lista.mostrarNormal();

        // Problema 3
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(3);
        lista.insertarFinal(4);
        lista.invertir();
        System.out.println("Lista invertida:");
        lista.mostrarNormal();

        // Problema 4
        ListaDoble<Integer> dupes = new ListaDoble<>();
        dupes.insertarFinal(2);
        dupes.insertarFinal(3);
        dupes.insertarFinal(2);
        dupes.insertarFinal(5);
        dupes.insertarFinal(3);
        dupes.eliminarDuplicados();
        System.out.println("Sin duplicados:");
        dupes.mostrarNormal();

        // Problema 5
        ListaDoble<Integer> ordenada = new ListaDoble<>();
        ordenada.insertarOrdenado(4);
        ordenada.insertarOrdenado(1);
        ordenada.insertarOrdenado(3);
        ordenada.insertarOrdenado(2);
        System.out.println("Lista ordenada:");
        ordenada.mostrarNormal();
    }
}
