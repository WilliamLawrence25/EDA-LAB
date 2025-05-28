package Actividades;
public class ListaCircularMain {
    public static void main(String[] args) {
        ListaCircular<Integer> listaEnteros = new ListaCircular<>();
        
        System.out.println("\n Lista con Enteros:");
        listaEnteros.insertar(10);
        listaEnteros.insertar(20);
        listaEnteros.insertar(30);
        listaEnteros.mostrar();
        System.out.println("Cantidad de elementos: " + listaEnteros.contar());
        System.out.println("¿Está el 20? " + listaEnteros.buscar(20));
        System.out.println("¿Está el 40? " + listaEnteros.buscar(40));

        System.out.println("\nLista con Strings:");
        ListaCircular<String> listaStrings = new ListaCircular<>();
        listaStrings.insertar("Eula");
        listaStrings.insertar("Cartethyia");
        listaStrings.insertar("Jinhsi");
        listaStrings.mostrar();
        System.out.println("Cantidad de elementos: " + listaStrings.contar());
        System.out.println("¿Está 'Eula'? " + listaStrings.buscar("Eula"));
        System.out.println("¿Está 'Cartethya'? " + listaStrings.buscar("Camellya"));
        
    }
}
