// Rotación a la Izquierda

package Lab02;

public class Ejercicio02 {

    public static void main(String[] args){

        int[] A = {1, 2, 3, 4, 5, 6};
        int[] Aiz = rotarIzquierdaArray(A, 2);

        for(int i = 0; i < A.length; i++){
            System.out.println(A[i] + " | " + Aiz[i]);
        }
    }

    public static int[] rotarIzquierdaArray(int[] A, int d){
        int[] Aiz = new int[A.length];
        for(int i = 0; i < A.length - d; i++){
            Aiz[i] = A[i + d];
        }
        for(int j = A.length - d; j < A.length; j++){
            Aiz[j] = A[j - A.length + d];
        }
        return Aiz;
    }
}
