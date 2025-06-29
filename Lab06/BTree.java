package Lab06;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

public class BTree {
    private BTreeNode root;
    private int t;
    private int xOffset = 0;
    private int ySpacing = 10;
    private int xSpacing = 10;

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

    public void visualize() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("B-Tree");
        graph.setAttribute("ui.stylesheet",
            "node { fill-color: #4477aa; size-mode: fit; padding: 5px; text-size: 10px; text-color: white; shape: box; }" +
            "edge { fill-color: #888; }");

        if (root != null) {
            xOffset = 0;
            addToGraph(graph, root, "R", null, 0);
        }

        Viewer viewer = graph.display();
        viewer.disableAutoLayout();
    }

    private void addToGraph(Graph graph, BTreeNode node, String nodeId, String parentId, int level) {
        StringBuilder label = new StringBuilder();
        for (int i = 0; i < node.n; i++) {
            label.append(node.keys[i]);
            if (i < node.n - 1) label.append(" | ");
        }

        Node graphNode = graph.addNode(nodeId);
        graphNode.setAttribute("ui.label", label.toString());
        graphNode.setAttribute("xy", xOffset * 1, -level * 3);
        xOffset++;

        if (parentId != null) {
            graph.addEdge(parentId + "->" + nodeId, parentId, nodeId, true);
        }

        for (int i = 0; i <= node.n; i++) {
            if (node.children[i] != null) {
                addToGraph(graph, node.children[i], nodeId + "." + i, nodeId, level + 1);
            }
        }
    }
}
