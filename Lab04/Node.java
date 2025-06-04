package Lab04;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> right;
    private Node<T> left;
    private Node<T> parent;

    public Node(T data) {
        this.data = data;
        this.right = null;
        this.left = null;
        this.parent = null;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Node<T> getRight() {
        return right;
    }
    public void setRight(Node<T> right) {
        this.right = right;
    }
    public Node<T> getLeft() {
        return left;
    }
    public void setLeft(Node<T> left) {
        this.left = left;
    }
    public Node<T> getParent() {
        return parent;
    }
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    
}
