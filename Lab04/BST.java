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
            insertRec(root, data);
        }
    }
    private void insertRec(Node<T> current, T newData) {
        int cmp = newData.compareTo(current.getData());
        
        if (cmp < 0) {
            if (current.getLeft() == null) {
                Node<T> newNode = new Node<>(newData);
                current.setLeft(newNode);
                newNode.setParent(current);
            } else {
                insertRec(current.getLeft(), newData);
            }
        } else if (cmp > 0) {
            if (current.getRight() == null) {
                Node<T> newNode = new Node<>(newData);
                current.setRight(newNode);
                newNode.setParent(current);
            } else {
                insertRec(current.getRight(), newData);
            }
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

    public String parent(T value) {
        Node<T> node = searchRec(root, value);
        if (node == null) return "Nodo no encontrado";
        if (node != null && node.getParent() != null) {
            return node.getParent().getData().toString();
        }
        return "No tiene padre";
    }

    public String son(T value) {
        Node<T> node = searchRec(root, value);
        if (node == null) return "Nodo no encontrado";

        boolean tieneIzq = node.getLeft() != null;
        boolean tieneDer = node.getRight() != null;

        if (tieneIzq && tieneDer) {
            return node.getLeft().getData() + ", " + node.getRight().getData();
        } else if (tieneIzq) {
            return node.getLeft().getData().toString();
        } else if (tieneDer) {
            return node.getRight().getData().toString();
        } else {
            return "No tiene hijos";
        }
    }


    public void visualize() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("BST");
        
        String styleSheet =
            "node {" +
            "   size: 50px, 50px;" +
            "   fill-color: #66CCFF;" +
            "   stroke-mode: plain;" +
            "   text-size: 24;" +
            "   text-color: black;" +
            "   text-alignment: center;" +
            "   text-style: bold;" +
            "   text-background-mode: rounded-box;" +
            "   text-background-color: #66CCFF;" +
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

    private void addNodesAndEdges(Graph graph, Node<T> current, Node<T> parent) {
        String currentID = current.getData().toString();

        if (graph.getNode(currentID) == null) {
            org.graphstream.graph.Node nodeGraph = graph.addNode(currentID);
            nodeGraph.setAttribute("ui.label", currentID);
        }

        if (parent != null) {
            String padreId = parent.getData().toString();
            String edgeId = padreId + "-" + currentID;
            if (graph.getEdge(edgeId) == null) {
                graph.addEdge(edgeId, padreId, currentID, true);
            }
        }

        if (current.getLeft() != null) {
            addNodesAndEdges(graph, current.getLeft(), current);
        }

        if (current.getRight() != null) {
            addNodesAndEdges(graph, current.getRight(), current);
        }
    }

}
