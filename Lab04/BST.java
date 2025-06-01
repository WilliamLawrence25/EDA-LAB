package Lab04;

public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public BST() {
        this.root = null;
    }

    public void insert(T data){
        if (root == null) {
            root = new Node<>(data);
        } else {
            root.insert(data);
        }
    }
    
}
