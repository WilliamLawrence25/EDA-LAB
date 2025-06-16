package Lab05;

public class AVL<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVL() {
        this.root = null;
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public void insert(T data){
        if (root == null) {
            root = new AVLNode<>(data);
        } else {
            insertRec(root, data);
        }
    }
    private void insertRec(AVLNode<T> current, T newData) {
        int cmp = newData.compareTo(current.getData());
        
        if (cmp < 0) {
            if (current.getLeft() == null) {
                AVLNode<T> newNode = new AVLNode<>(newData);
                current.setLeft(newNode);
                newNode.setParent(current);
            } else {
                insertRec(current.getLeft(), newData);
            }
        } else if (cmp > 0) {
            if (current.getRight() == null) {
                AVLNode<T> newNode = new AVLNode<>(newData);
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
        if (node != null && node.getParent() != null) {
            return node.getParent().getData().toString();
        }
        return "No tiene padre";
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
}