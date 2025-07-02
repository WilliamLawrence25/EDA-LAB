package Lab06;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

public class BTree {
    private BTreeNode root;
    private int t;
    private int xOffset;

    public BTree(int t) {
        this.t = t;
        root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.numKeys = 1;
        } else { // If the root is full, we need to split it
            if (root.numKeys == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);
                // Insert the new key into the appropriate child
                int i = (s.keys[0] < key) ? 1 : 0;
                s.children[i].insertNonFull(key);

                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    public void remove(int key) {
        if (root == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }

        root.remove(key);

        if (root.numKeys == 0) {
            root = root.isLeaf ? null : root.children[0];
        }
    }

    public BTreeNode search(int key) {
        return (root == null) ? null : root.search(key);
    }

    public void traverse() {
        if (root != null) {
            System.out.println("=== ARBOL B ===");
            root.traverse(0);
        }
    }

    public void visualize() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("B-Tree");
        graph.setAttribute("ui.stylesheet",
            "node { fill-color: #4477aa; size-mode: fit; padding: 8px; text-size: 15px; text-color: white; shape: box; }" +
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
        for (int i = 0; i < node.numKeys; i++) {
            label.append(node.keys[i]);
            if (i < node.numKeys - 1) label.append(" | ");
        }

        Node graphNode = graph.addNode(nodeId);
        graphNode.setAttribute("ui.label", label.toString());
        graphNode.setAttribute("xy", xOffset * 1, -level * 3);
        xOffset++;

        if (parentId != null) {
            graph.addEdge(parentId + "->" + nodeId, parentId, nodeId, true);
        }

        for (int i = 0; i <= node.numKeys; i++) {
            if (node.children[i] != null) {
                addToGraph(graph, node.children[i], nodeId + "." + i, nodeId, level + 1);
            }
        }
    }
}
