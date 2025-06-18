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

    private AVLNode<T> insertRec(AVLNode<T> currect, T data, AVLNode<T> parent) {
        if (currect == null) {
            AVLNode<T> newNode = new AVLNode<>(data);
            newNode.setParent(parent);
            return newNode;
        }

        if (data.compareTo(currect.getData()) < 0) {
            AVLNode<T> left = insertRec(currect.getLeft(), data, currect);
            currect.setLeft(left);
        } else if (data.compareTo(currect.getData()) > 0) {
            AVLNode<T> right = insertRec(currect.getRight(), data, currect);
            currect.setRight(right);
        } else {
            return currect; // No duplicados
        }

        updateHeight(currect);
        return balance(currect);
    }

    private void updateHeight(AVLNode<T> node) {
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        node.setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    private int height(AVLNode<T> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private int fe(AVLNode<T> node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    private AVLNode<T> balance(AVLNode<T> node) {
        int balance = fe(node);

        if (balance > 1) {
            if (fe(node.getRight()) < 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }

        if (balance < -1) {
            if (fe(node.getLeft()) > 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }

        return node;
    }

    private AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.getLeft();

        x.setRight(y);
        y.setLeft(x.getRight());

        if (x.getRight() != null) x.getRight().setParent(y);
        x.setParent(y.getParent());
        y.setParent(x);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> x) {
        AVLNode<T> y = x.getRight();

        y.setLeft(x);
        x.setRight(y.getLeft());

        if (y.getLeft() != null) y.getLeft().setParent(x);
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
            return Character.toString((char)node.getLeft().getData());
            //return node.getLeft().getData().toString();
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