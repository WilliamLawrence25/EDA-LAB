public class ListaCircularMain {
    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();
        
        System.out.println("Insertando elementos...");
        lista.insertar(10);
        lista.insertar(20);
        lista.insertar(30);
        lista.mostrar();
        /* 
        System.out.println("Eliminando 20...");
        lista.eliminar(20);
        lista.mostrar();
        
        System.out.println("Eliminando 10...");
        lista.eliminar(10);
        lista.mostrar();

        System.out.println("Eliminando 30...");
        lista.eliminar(30);
        lista.mostrar();

        System.out.println("Insertando de nuevo...");
        lista.insertar(100);
        lista.mostrar();*/
    }
    
}
