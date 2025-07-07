package Lab07;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int[] grados = {2, 5, 10, 20, 50, 10000};
        int cantidad = 10000000;
        long[] tiempos = new long[grados.length];

        System.out.println("Comparación de tiempos de inserción para distintos grados de B-Tree:");
        System.out.println("Insertando " + cantidad + " elementos...\n");

        for (int i = 0; i < grados.length; i++) {
            BTree btree = new BTree(grados[i]);
            long inicio = System.nanoTime();
            for (int j = 1; j <= cantidad; j++) {
                btree.insert(j);
            }
            long fin = System.nanoTime();
            tiempos[i] = fin - inicio;
            System.out.printf("Grado %2d: %8.3f ms\n", grados[i], tiempos[i] / 1_000_000.0);
        }

        // Comparativa de diferencias
        // Encontrar el mejor tiempo
        long mejorTiempo = tiempos[0];
        int mejorIndice = 0;
        for (int i = 1; i < tiempos.length; i++) {
            if (tiempos[i] < mejorTiempo) {
                mejorTiempo = tiempos[i];
                mejorIndice = i;
            }
        }
        System.out.println("\nEl mejor tiempo fue con grado " + grados[mejorIndice] +
                " (" + String.format("%.3f", mejorTiempo / 1_000_000.0) + " ms)");

        System.out.println("\nDiferencias respecto al mejor:");
        for (int i = 0; i < grados.length; i++) {
            double diferencia = (tiempos[i] - mejorTiempo) / 1_000_000.0;
            if (i == mejorIndice) {
                System.out.printf("Grado %2d: Mejor tiempo\n", grados[i]);
            } else {
                System.out.printf("Grado %2d: +%.3f ms\n", grados[i], diferencia);
            }
        }

        // Mostrar gráfico
        mostrarGrafico(grados, tiempos, cantidad);
    }

    public static void mostrarGrafico(int[] grados, long[] tiempos, int cantidad) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < grados.length; i++) {
            dataset.addValue(tiempos[i] / 1_000_000.0, "Tiempo (ms)", String.valueOf(grados[i]));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Relación entre Grado y Tiempo de Inserción\n(" + cantidad + " elementos)",
                "Grado del B-Tree",
                "Tiempo de inserción (ms)",
                dataset
        );

        JFrame frame = new JFrame("Comparativa de tiempos de inserción");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
