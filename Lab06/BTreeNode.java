package Lab06;

public class BTreeNode {
    int[] keys;
    BTreeNode[] children;
    int t;
    int n;
    boolean leaf;

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    public void traverse(int level) {
        String indent = "  ".repeat(level);
        System.out.print(indent + "Nivel " + level + " -> Claves: [");
        for (int i = 0; i < n; i++) {
            System.out.print(keys[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("]");

        if (!leaf) {
            for (int i = 0; i <= n; i++) {
                if (children[i] != null) {
                    children[i].traverse(level + 1);
                }
            }
        }
    }
}