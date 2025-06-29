package Lab06;

public class BTree {
    private BTreeNode root;
    private int t;

    public BTree(int t) {
        this.t = t;
        root = new BTreeNode(t, true);
    }

    public void insert(int k) {
        if (root.n == 2 * t - 1) {
            BTreeNode s = new BTreeNode(t, false);
            s.children[0] = root;
            splitChild(s, 0);
            insertNonFull(s, k);
            root = s;
        } else {
            insertNonFull(root, k);
        }
    }

    private void insertNonFull(BTreeNode x, int k) {
        int i = x.n - 1;

        if (x.leaf) {
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = k;
            x.n++;
        } else {
            while (i >= 0 && k < x.keys[i]) {
                i--;
            }
            i++;
            if (x.children[i].n == 2 * t - 1) {
                splitChild(x, i);
                if (k > x.keys[i]) {
                    i++;
                }
            }
            insertNonFull(x.children[i], k);
        }
    }

    private void splitChild(BTreeNode x, int i) {
        BTreeNode y = x.children[i];
        BTreeNode z = new BTreeNode(t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        for (int j = x.n; j >= i + 1; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[i + 1] = z;

        for (int j = x.n - 1; j >= i; j--) {
            x.keys[j + 1] = x.keys[j];
        }
        x.keys[i] = y.keys[t - 1];
        x.n++;

        y.n = t - 1;
    }

    public void print() {
        System.out.println("\n=== ARBOL B ===");
        if (root != null) {
            root.traverse(0);
        }
    }
}