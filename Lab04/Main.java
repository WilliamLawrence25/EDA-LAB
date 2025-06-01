package Lab04;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Main {
    
    public static void main(String[] args){
        String word = "Cartethyia";
        BST<Integer> bst = new BST<>();

        System.out.print("Entered word: ");
        for(char c : word.toCharArray()) {
            bst.insert((int)c);
            System.out.print(c + ", ");
        }

        bst.printInOrder();

        //bst.graficar();     // Muestra el árbol visual

        Graph graph = new SingleGraph("Tutorial 1");
        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addNode("Z");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("AZ", "A", "Z");
        graph.addNode("WAAA");
        System.setProperty("org.graphstream.ui", "swing"); 
        graph.display();


    }
    
}
