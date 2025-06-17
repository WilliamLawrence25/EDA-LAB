package Lab05;

public class AVLNode<T> {
    private T data;
    private AVLNode<T> right;
    private AVLNode<T> left;
    private AVLNode<T> parent;
    private int height;

    public AVLNode(T data, AVLNode<T> right, AVLNode<T> left, AVLNode<T> parent) {
        this.data = data;
        this.right = right;
        this.left = left;
        this.parent = parent;
        this.height = 1;
    }

    public AVLNode(T data) {
        this.data = data;
        this.right = null;
        this.left = null;
        this.parent = null;
        this.height = 1;
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
    public int getHeight() {
        return height;
    }
    public void setHeight(int bf) {
        this.height = bf;
    }

}

