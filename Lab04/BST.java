package Lab04;


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
    }

    private void InOrder(Node<T> node) {
        if (node != null) {
            InOrder(node.getLeft());
            System.out.print(node.getData() + ", ");
            InOrder(node.getRight());
        }
    }

    public void graficar() {
        System.setProperty("org.graphstream.ui", "javafx"); // <--- esta línea es clave

        org.graphstream.graph.Graph graph = new org.graphstream.graph.implementations.SingleGraph("BST");
        graph.setAttribute("ui.stylesheet", "node { fill-color: #33c; text-size: 20px; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        graficarNodo(root, graph);
        graph.display();
    }



    private void graficarNodo(Node<T> actual, org.graphstream.graph.Graph graph) {
    if (actual == null) return;

    // Usamos el valor como nombre del nodo
    String nombreNodo = actual.getData().toString();

    // ✅ Creamos el nodo en el grafo y le damos una etiqueta
    if (graph.getNode(nombreNodo) == null) {
        org.graphstream.graph.Node gNode = graph.addNode(nombreNodo);
        gNode.setAttribute("ui.label", nombreNodo);
    }

    // Graficar hijo izquierdo
    if (actual.getLeft() != null) {
        String hijoIzq = actual.getLeft().getData().toString();
        if (graph.getNode(hijoIzq) == null) {
            org.graphstream.graph.Node gLeft = graph.addNode(hijoIzq);
            gLeft.setAttribute("ui.label", hijoIzq);
        }
        String edgeId = nombreNodo + "-" + hijoIzq;
        if (graph.getEdge(edgeId) == null) {
            graph.addEdge(edgeId, nombreNodo, hijoIzq, true);
        }
        graficarNodo(actual.getLeft(), graph);
    }

    // Graficar hijo derecho
    if (actual.getRight() != null) {
        String hijoDer = actual.getRight().getData().toString();
        if (graph.getNode(hijoDer) == null) {
            org.graphstream.graph.Node gRight = graph.addNode(hijoDer);
            gRight.setAttribute("ui.label", hijoDer);
        }
        String edgeId = nombreNodo + "-" + hijoDer;
        if (graph.getEdge(edgeId) == null) {
            graph.addEdge(edgeId, nombreNodo, hijoDer, true);
        }
        graficarNodo(actual.getRight(), graph);
    }
}





    

}

