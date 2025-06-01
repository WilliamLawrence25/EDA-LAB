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
    public Node<T> getLeft() {
        return left;
    }
    public Node<T> getParent() {
        return parent;
    }
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void insertRec(T newData){
        if(newData.compareTo(this.data) < 0){
            // Insertar a la izquierda
            if(this.left == null){
                left = new Node<>(newData);
                left.setParent(this);
            }else{
                this.left.insertRec(newData);
            }
        } else {
            // Insertar a la derecha
            if(this.right == null) {
                right = new Node<T>(newData);
            } else {
                this.right.insertRec(newData);
                right.setParent(this);
            }
        }
    }
    













    
    public int compareTo(T other){
        return this.data.compareTo(other);
    }
    
    

}
