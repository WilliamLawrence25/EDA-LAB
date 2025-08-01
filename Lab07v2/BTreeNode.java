package Lab07v2;

public class BTreeNode {
    int[] keys; // Array to store keys
    BTreeNode[] children; // Array to store child pointers
    int t; // Minimum degree
    int numKeys; // Current number of keys
    boolean isLeaf; // Indicates if the node is a leaf

    public BTreeNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.numKeys = 0;
    }

    public void traverse(int level) {
        String indent = "  ".repeat(level);
        System.out.print(indent + "Nivel " + level + " -> Claves: [");
        for (int i = 0; i < numKeys; i++) {
            System.out.print(keys[i]);
            if (i < numKeys - 1) System.out.print(", ");
        }
        System.out.println("]");

        if (!isLeaf) {
            for (int i = 0; i <= numKeys; i++) {
                if (children[i] != null) {
                    children[i].traverse(level + 1);
                }
            }
        }
    }

    public int findKey(int key) {
        int idx = 0;
        while (idx < numKeys && keys[idx] < key) idx++;
        return idx;
    }

    public void remove(int key) {
        int idx = findKey(key);
        // If the key is found in this node
        if (idx < numKeys && keys[idx] == key) {
            if (isLeaf) {
                for (int i = idx + 1; i < numKeys; ++i) keys[i - 1] = keys[i];
                numKeys--;
            } else {
                removeFromNonLeaf(idx);
            }
        } else {
            if (isLeaf) {
                System.out.println("La clave " + key + " no está en el arbol.");
                return;
            }

            // If the key is not found, we go to the child node
            boolean flag = (idx == numKeys);

            if (children[idx].numKeys < t) fill(idx);

            // If the last child is empty, we go to the previous child
            if (flag && idx > numKeys) {
                children[idx - 1].remove(key);
            } else {
                children[idx].remove(key);
            }
        }
    }

    private void removeFromNonLeaf(int idx) {
        int k = keys[idx];

        if (children[idx].numKeys >= t) {
            int pred = getPredecessor(idx);
            keys[idx] = pred;
            children[idx].remove(pred);
        } else if (children[idx + 1].numKeys >= t) {
            int succ = getSuccessor(idx);
            keys[idx] = succ;
            children[idx + 1].remove(succ);
        } else {
            merge(idx);
            children[idx].remove(k);
        }
    }

    private int getPredecessor(int idx) {
        BTreeNode cur = children[idx];
        while (!cur.isLeaf) cur = cur.children[cur.numKeys];
        return cur.keys[cur.numKeys - 1];
    }

    private int getSuccessor(int idx) {
        BTreeNode cur = children[idx + 1];
        while (!cur.isLeaf) cur = cur.children[0];
        return cur.keys[0];
    }

    private void fill(int idx) {
        if (idx != 0 && children[idx - 1].numKeys >= t) {
            borrowFromPrev(idx);
        } else if (idx != numKeys && children[idx + 1].numKeys >= t) {
            borrowFromNext(idx);
        } else {
            if (idx != numKeys) merge(idx);
            else merge(idx - 1);
        }
    }

    private void borrowFromPrev(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx - 1];

        for (int i = child.numKeys - 1; i >= 0; --i)
            child.keys[i + 1] = child.keys[i];

        if (!child.isLeaf) {
            for (int i = child.numKeys; i >= 0; --i)
                child.children[i + 1] = child.children[i];
        }

        child.keys[0] = keys[idx - 1];

        if (!child.isLeaf)
            child.children[0] = sibling.children[sibling.numKeys];

        keys[idx - 1] = sibling.keys[sibling.numKeys - 1];

        child.numKeys++;
        sibling.numKeys--;
    }

    private void borrowFromNext(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];

        child.keys[child.numKeys] = keys[idx];

        if (!child.isLeaf)
            child.children[child.numKeys + 1] = sibling.children[0];

        keys[idx] = sibling.keys[0];

        for (int i = 1; i < sibling.numKeys; ++i)
            sibling.keys[i - 1] = sibling.keys[i];

        if (!sibling.isLeaf) {
            for (int i = 1; i <= sibling.numKeys; ++i)
                sibling.children[i - 1] = sibling.children[i];
        }

        child.numKeys++;
        sibling.numKeys--;
    }

    // Merges two children of this node
    private void merge(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];

        child.keys[t - 1] = keys[idx];

        for (int i = 0; i < sibling.numKeys; ++i)
            child.keys[i + t] = sibling.keys[i];

        if (!child.isLeaf) {
            for (int i = 0; i <= sibling.numKeys; ++i)
                child.children[i + t] = sibling.children[i];
        }

        for (int i = idx + 1; i < numKeys; ++i)
            keys[i - 1] = keys[i];
        for (int i = idx + 2; i <= numKeys; ++i)
            children[i - 1] = children[i];

        child.numKeys += sibling.numKeys + 1;
        numKeys--;
    }

    // Inserts a new key into a non-full node
    public void insertNonFull(int key) {
        int i = numKeys - 1;
        // If this node is a leaf, insert the key directly
        if (isLeaf) {
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            numKeys++;
        } else { // If this node is not a leaf, find the child that will have the new key
            while (i >= 0 && keys[i] > key) i--;
            i++;
            if (children[i].numKeys == 2 * t - 1) {
                splitChild(i, children[i]);
                if (keys[i] < key) i++;
            }
            children[i].insertNonFull(key);
        }
    }

    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.isLeaf);
        z.numKeys = t - 1;

        // Move the last t-1 keys of y to z
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        // If y is not a leaf, move the last t children of y to z
        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        // Reduce the number of keys in y
        for (int j = numKeys; j >= i + 1; j--) children[j + 1] = children[j];
        children[i + 1] = z;
        // Move the middle key of y to this node
        for (int j = numKeys - 1; j >= i; j--) keys[j + 1] = keys[j];
        keys[i] = y.keys[t - 1];

        y.numKeys = t - 1;
        numKeys++;
    }

    // Searches for a key in the B-Tree node
    public BTreeNode search(int key) {
        int left = 0;
        int right = numKeys - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (keys[mid] == key) {
                return this; // Encontrado en este nodo
            } else if (keys[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Si es hoja, no existe la clave
        if (isLeaf) return null;

        // Buscar en el hijo correspondiente
        return children[left].search(key);
    }

}
