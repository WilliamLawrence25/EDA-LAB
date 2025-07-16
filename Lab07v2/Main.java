package Lab07v2;

import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.awt.BasicStroke;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

public class Main {
    public static void main(String[] args) {
        int[] grados = {2, 5, 20, 50, 100, 500, 1000, 2000, 5000, 10000};
        int cantidad = 1000000;
        long[] tiempos = new long[grados.length];

        // Generar 100000 números únicos aleatorios entre 1 y 1_000_000
        List<Integer> numeros = generarNumerosUnicos(cantidad, 10_000_000);

        System.out.println("Comparación de tiempos de búsqueda para distintos grados de B-Tree:");
        System.out.println("Insertando " + cantidad + " elementos aleatorios únicos...\n");

        for (int i = 0; i < grados.length; i++) {
            BTree btree = new BTree(grados[i]);

            // Insertar todos los números (no medido)
            for (int num : numeros) {
                btree.insert(num);
            }

            // Medir tiempo de búsqueda de todos los números
            long inicio = System.nanoTime();
            for (int num : numeros) {
                btree.search(num);
            }
            long fin = System.nanoTime();
            tiempos[i] = fin - inicio;

            System.out.printf("Grado %5d: %10.3f ms\n", grados[i], tiempos[i] / 1_000_000.0);
        }

        // Buscar el mejor tiempo
        long mejorTiempo = tiempos[0];
        int mejorIndice = 0;
        for (int i = 1; i < tiempos.length; i++) {
            if (tiempos[i] < mejorTiempo) {
                mejorTiempo = tiempos[i];
                mejorIndice = i;
            }
        }

        System.out.println("\nEl mejor tiempo de búsqueda fue con grado " + grados[mejorIndice] +
                " (" + String.format("%.3f", mejorTiempo / 1_000_000.0) + " ms)");

        System.out.println("\nDiferencias respecto al mejor:");
        for (int i = 0; i < grados.length; i++) {
            double diferencia = (tiempos[i] - mejorTiempo) / 1_000_000.0;
            if (i == mejorIndice) {
                System.out.printf("Grado %5d: Mejor tiempo\n", grados[i]);
            } else {
                System.out.printf("Grado %5d: +%.3f ms\n", grados[i], diferencia);
            }
        }

        mostrarGrafico(grados, tiempos, cantidad);
    }

    public static List<Integer> generarNumerosUnicos(int cantidad, int maximo) {
        Set<Integer> set = new HashSet<>();
        Random rand = new Random();
        while (set.size() < cantidad) {
            int num = rand.nextInt(maximo) + 1; // entre 1 y maximo
            set.add(num);
        }
        List<Integer> lista = new ArrayList<>(set);
        Collections.shuffle(lista); // Mezclarlos para más aleatoriedad
        return lista;
    }



    // Mostrar gráfico de tiempos de búsqueda
    public static void mostrarGrafico(int[] grados, long[] tiempos, int cantidad) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < grados.length; i++) {
            dataset.addValue(tiempos[i] / 1_000_000.0, "Tiempo de búsqueda (ms)", String.valueOf(grados[i]));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Relación entre Grado y Tiempo de Búsqueda\n(" + cantidad + " elementos)",
                "Grado del B-Tree",
                "Tiempo total de búsqueda (ms)",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(245, 245, 255));
        plot.setRangeGridlinePaint(new Color(180, 180, 180));
        plot.setOutlinePaint(Color.DARK_GRAY);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(33, 147, 176));
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-4, -4, 8, 8));

        plot.setRenderer(renderer);
        chart.setBackgroundPaint(Color.WHITE);

        JFrame frame = new JFrame("Comparativa de tiempos de búsqueda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
