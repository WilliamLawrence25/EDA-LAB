// Invertir una matriz de enteros

package Lab02;

public class Ejercicio01{

    public static void main(String[] args){

        int[] A = {1, 2, 3, 4, 5};
        int[] Ainv = invertirArray(A);

        for(int i = 0; i < A.length; i++){
            System.out.println(A[i]);
            System.out.println("--" + Ainv[i]);
        }

    }

    public static int[] invertirArray(int[] A){
        int[] Ainv = new int[A.length];
        for(int i = A.length; i > 0; i--){
            Ainv[A.length - i] = A[i - 1];
        }
    return Ainv;
    }
}
