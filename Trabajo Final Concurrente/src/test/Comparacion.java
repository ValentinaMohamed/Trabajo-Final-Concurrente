package test;

import algoritmo.QuickSortConcurrente;
import algoritmo.QuickSortSecuencial;
import util.Utilidades;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Comparacion {

    // Método principal que ejecuta la comparación
    public static void main(String[] args) {
        int[] tamanios = {10, 100, 1000, 100_000, 1_000_000}; // Tamaños de los arreglos a ordenar
        int umbral = 10_000; // Umbral para QuickSort concurrente

        // Se itera sobre cada tamaño definido
        for (int n : tamanios) {
            System.out.println("Tamanio del array: " + n);

            // Se genera un arreglo aleatorio de tamaño n y se crean copias para cada método de ordenamiento
            int[] original = Utilidades.generarArrayAleatorio(n);
            int[] copiaSecuencial = Arrays.copyOf(original, original.length);
            int[] copiaConcurrente = Arrays.copyOf(original, original.length);

            // Se mide el tiempo de ejecución del QuickSort secuencial
            QuickSortSecuencial qs = new QuickSortSecuencial();
            long inicioSec = System.nanoTime();
            qs.quickSort(copiaSecuencial);
            long finSec = System.nanoTime();
            System.out.printf("Secuencial:  %.3f ms%n", (finSec - inicioSec) / 1_000_000.0);
            
            // Verifica si el resultado del ordenamiento secuencial es correcto
            if (!Utilidades.estaOrdenado(copiaSecuencial)) {
                System.out.println("Error: el array secuencial no está ordenado");
            }
            
            // Se mide el tiempo de ejecución del QuickSort concurrente utilizando un ForkJoinPool
            try (ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
                QuickSortConcurrente qc = new QuickSortConcurrente(copiaConcurrente, 0, copiaConcurrente.length - 1, umbral);
                long inicioConc = System.nanoTime();
                pool.invoke(qc);
                long finConc = System.nanoTime();
                System.out.printf("Concurrente: %.3f ms%n", (finConc - inicioConc) / 1_000_000.0);
            }
            
            // Verifica si el resultado del ordenamiento concurrente es correcto
            if (!Utilidades.estaOrdenado(copiaConcurrente)) {
                System.out.println("Error: el array concurrente no está ordenado");
            }

            System.out.println("------------------------------");
        }
    }
}