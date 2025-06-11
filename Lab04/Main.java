package Lab04;

public class Main {
    
    public static void main(String[] args){
        String word = "CARTETHYIA";
        BST<Integer> bst = new BST<>();

        System.out.print("Entered word: ");
        for(char c : word.toCharArray()) {
            bst.insert((int)c);
            System.out.print(c + ", ");
        }

        bst.printInOrder();

        char search = 'H';
        boolean found = bst.search((int)search) != null;
        System.out.println("Buscar '" + search + "': " + found);

        System.out.println("Max: " + bst.getMax().getData());
        System.out.println("Min: " + bst.getMin().getData());

        int searchParent = 110;
        System.out.println("Padre de '" + searchParent + "': " + bst.parent(searchParent));
        int searchSon = 72;
        System.out.println("Hijos de '" + searchSon + "': " + bst.son(searchSon));

        BST<Character> bstString = new BST<>();
        for(int i: word.toCharArray()) {
            bstString.insert((char)i);
        }
        bstString.visualize();

    }
    
}
