package Lab04;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public BST() {
        this.root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void insert(T data){
        if (root == null) {
            root = new Node<>(data);
        } else {
            root.insertRec(data);
        }
    }

    public void printInOrder() {
        System.out.print("\nInOrder: ");
        InOrder(root);
        System.out.println();
    }

    private void InOrder(Node<T> node) {
        if (node != null) {
            InOrder(node.getLeft());
            System.out.print(node.getData() + ", ");
            InOrder(node.getRight());
        }
    }

    public Boolean search(T data) {
        return searchRec(root, data) != null;
    }
    private Node<T> searchRec(Node<T> node, T value){
        if(node == null || node.getData().equals(value)) return node;
        if(value.compareTo(node.getData()) < 0) {
            return searchRec(node.getLeft(), value);
        } else {
            return searchRec(node.getRight(), value);
        }
    }

    public Node<T> getMax(){
        if (root == null) return null;
        Node<T> max = root;
        while (max.getRight() != null) {
            max = max.getRight();
        }
        return max;
    }

    public Node<T> getMin() {
        if (root == null) return null;
        Node<T> min = root;
        while (min.getLeft() !=null) {
            min = min.getLeft();
        }
        return min;
    }

    public T parent(T value) {
        Node<T> node = searchRec(root, value);
        if (node != null && node.getParent() != null) {
            return node.getParent().getData();
        }
        return null;
    }

    public String son(T value) {
        Node<T> node = searchRec(root, value);
        if (node == null) return "Nodo no encontrado";
        if (node.getLeft() != null && node.getRight() != null) {
            String son = node.getLeft().getData() + ", " + node.getRight().getData();
            return son;
        } return "No tiene hijos";
    }


    public void visualize() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Árbol BST");
        
        String styleSheet =
            "node {" +
            "   size: 40px, 40px;" +
            "   fill-color: #66CCFF;" +
            "   stroke-mode: plain;" +
            "   stroke-color: black;" +
            "   text-size: 24;" +
            "   text-color: black;" +
            "   text-alignment: center;" +
            "   text-background-mode: rounded-box;" +
            "   text-background-color: white;" +
            "}" +
            "edge {" +
            "   fill-color: #444;" +
            "}";
        graph.setAttribute("ui.stylesheet", styleSheet);

        if (root != null) {
            addNodesAndEdges(graph, root, null);
        }

        graph.display();
    }

    private void addNodesAndEdges(Graph graph, Node<T> actual, Node<T> padre) {
        String actualId = actual.getData().toString();

        if (graph.getNode(actualId) == null) {
            org.graphstream.graph.Node nodeGraph = graph.addNode(actualId);
            nodeGraph.setAttribute("ui.label", actualId);
        }

        if (padre != null) {
            String padreId = padre.getData().toString();
            String edgeId = padreId + "-" + actualId;
            if (graph.getEdge(edgeId) == null) {
                graph.addEdge(edgeId, padreId, actualId, true);
            }
        }

        if (actual.getLeft() != null) {
            addNodesAndEdges(graph, actual.getLeft(), actual);
        }

        if (actual.getRight() != null) {
            addNodesAndEdges(graph, actual.getRight(), actual);
        }
    }

}
