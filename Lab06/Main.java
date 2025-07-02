package Lab06;

public class Main {
    public static void main(String[] args) {
        BTree btree = new BTree(5);

        for (int i = 1; i <= 200; i++) {
            btree.insert(i);
        }

        btree.traverse();

        System.out.println("\nEliminando claves: 5, 15, 55, 100, 150, 199\n");
        btree.remove(5);
        btree.remove(15);
        btree.remove(55);
        btree.remove(100);
        btree.remove(150);
        btree.remove(199);

        btree.traverse();

        int searchKey = 120;
        System.out.println("\nBuscando la clave " + searchKey + ":");
        BTreeNode found = btree.search(searchKey);
        if (found != null) {
            System.out.println("Clave " + searchKey + " encontrada en el árbol.");
        } else {
            System.out.println("Clave " + searchKey + " no se encuentra en el árbol.");
        }

        btree.visualize();
    }
}
