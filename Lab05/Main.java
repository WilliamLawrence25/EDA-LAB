package Lab05;

public class Main {
    
    public static void main(String[] args){
        String word = "CARTETHYIADEIDADXSIEMPRE";
        AVL<Integer> bst = new AVL<>();

        System.out.print("Entered word: ");
        for(char c : word.toCharArray()) {
            bst.insert((int)c);
            System.out.print(c + ", ");
        }

        bst.printInOrder();

        char search = 'M';
        boolean found = bst.search((int)search);
        System.out.println("Buscar '" + search + "': " + found);

        System.out.println("Max: " + bst.getMax().getData());
        System.out.println("Min: " + bst.getMin().getData());

        char searchParent = 'A';
        System.out.println("Padre de '" + searchParent + "': " + bst.parent((int)searchParent));
        char searchSon = 'T';
        System.out.println("Hijos de '" + searchSon + "': " + bst.son((int)searchSon));

        AVL<Character> bstString = new AVL<>();
        for(int i: word.toCharArray()) {
            bstString.insert((char)i);
        }
        bstString.visualize();
    }
}

