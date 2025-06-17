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
            if(current.getLeft() == null) {
                AVLNode<T> newNode = new AVLNode<>(newData);
            } else {
                insertRec(current.getLeft(), newData);
                
            }
        }
        else if (cmp > 0) {
            if(current.getRight() == null) {
                AVLNode<T> newNode = new AVLNode<>(newData);
            } else {
                insertRec(current.getRight(), newData);
            }
        } 

        /*
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
        */
        
        
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

    private void updateHeight(AVLNode<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalance(AVLNode<T> node) {
        return node == null ? 0 : node.getLeft().getHeight() - node.getRight().getHeight();
    }
    
    private AVLNode<T> balance(AVLNode<T> node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }
    private AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.left;
        AVLNode<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        if (T2 != null) T2.parent = y;

        x.parent = y.parent;
        y.parent = x;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        if (T2 != null) T2.parent = x;

        y.parent = x.parent;
        x.parent = y;

        updateHeight(x);
        updateHeight(y);

        return y;
    }
}