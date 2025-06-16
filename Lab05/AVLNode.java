package Lab05;

public class AVLNode<T extends Comparable<T>> {
    private T data;
    private AVLNode<T> right;
    private AVLNode<T> left;
    private AVLNode<T> parent;
    private int bf; // factor de equilibrio


    public AVLNode(T data) {
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
    public AVLNode<T> getRight() {
        return right;
    }
    public void setRight(AVLNode<T> right) {
        this.right = right;
    }
    public AVLNode<T> getLeft() {
        return left;
    }
    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }
    public AVLNode<T> getParent() {
        return parent;
    }
    public void setParent(AVLNode<T> parent) {
        this.parent = parent;
    }

    
}

