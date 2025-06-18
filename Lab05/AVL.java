package Lab05;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class AVL<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVL() {
        this.root = null;
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public void insert(T data){
        root = insertRec(root, data, null);
    }

    private AVLNode<T> insertRec(AVLNode<T> node, T data, AVLNode<T> parent) {
        if (node == null) {
            AVLNode<T> newNode = new AVLNode<>(data);
            newNode.setParent(parent);
            return newNode;
        }

        if (data.compareTo(node.getData()) < 0) {
            AVLNode<T> left = insertRec(node.getLeft(), data, node);
            node.setLeft(left);
        } else if (data.compareTo(node.getData()) > 0) {
            AVLNode<T> right = insertRec(node.getRight(), data, node);
            node.setRight(right);
        } else {
            return node; // No duplicados
        }

        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(AVLNode<T> node) {
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        node.setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    private int height(AVLNode<T> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private int getBalance(AVLNode<T> node) {
        return (node == null) ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    private AVLNode<T> balance(AVLNode<T> node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.getLeft();
        AVLNode<T> T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        if (T2 != null) T2.setParent(y);
        x.setParent(y.getParent());
        y.setParent(x);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> x) {
        AVLNode<T> y = x.getRight();
        AVLNode<T> T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        if (T2 != null) T2.setParent(x);
        y.setParent(x.getParent());
        x.setParent(y);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void printInOrder() {
        System.out.print("\nInOrder: ");
        InOrder(root);
        System.out.println();
    }

    private void InOrder(AVLNode<T> node) {
        if (node != null) {
            InOrder(node.getLeft());
            System.out.print(node.getData() + ", ");
            InOrder(node.getRight());
        }
    }

    public Boolean search(T data) {
        return searchRec(root, data) != null;
    }

    private AVLNode<T> searchRec(AVLNode<T> current, T value){
        if(current == null || current.getData().equals(value)) return current;
        if(value.compareTo(current.getData()) < 0) {
            return searchRec(current.getLeft(), value);
        } else {
            return searchRec(current.getRight(), value);
        }
    }

    public AVLNode<T> getMax(){
        if (root == null) return null;
        AVLNode<T> max = root;
        while (max.getRight() != null) {
            max = max.getRight();
        }
        return max;
    }

    public AVLNode<T> getMin() {
        if (root == null) return null;
        AVLNode<T> min = root;
        while (min.getLeft() !=null) {
            min = min.getLeft();
        }
        return min;
    }

    public String parent(T value) {
        AVLNode<T> node = searchRec(root, value);
        if (node == null) return "Nodo no encontrado";
        if (node.getParent() == null) {
            return "Not tiene padre";
        } else {
            return node.getParent().getData().toString();
        }
    }

    public String son(T value) {
        AVLNode<T> node = searchRec(root, value);
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

    private void addNodesAndEdges(Graph graph, AVLNode<T> current, AVLNode<T> parent) {
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