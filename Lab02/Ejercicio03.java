// Triángulo recursivo

package Lab02;

public class Ejercicio03 {

    public static void main(String[] args){

        trianguloRecursivo(7);

    }

    public static void trianguloRecursivo(int base){
        System.out.println();
        for(int i = 1; i <= base; i++){
            for(int j = 1; j <= i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
