package algoritmo;

import java.util.concurrent.RecursiveAction;

// Clase que implementa el algoritmo QuickSort de forma concurrente utilizando el framework Fork/Join
@SuppressWarnings("serial")
public class QuickSortConcurrente extends RecursiveAction {
    private int[] arreglo;            // Arreglo a ordenar
    private int inicio;              // Índice inicial del subarreglo
    private int fin;                 // Índice final del subarreglo
    private int umbral; // Umbral que define cuándo usar ordenamiento secuencial

    // Constructor que recibe el arreglo y los índices que determinan el subarreglo a ordenar
    public QuickSortConcurrente(int[] arreglo, int inicio, int fin, int umbral) {
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
        this.umbral = umbral;
    }

    // Método principal que se ejecuta al invocar la tarea concurrente
    @Override
    protected void compute() {
        // Si el subarreglo es pequeño, se utiliza el algoritmo secuencial
        if (fin - inicio < umbral) {
            quickSortSecuencial(arreglo, inicio, fin);
        } else {
            // Se obtiene el índice del pivote después de la partición
            int indicePivote = particionar(arreglo, inicio, fin);

            // Se crean y ejecutan de forma concurrente dos tareas para ordenar los subarreglos
            invokeAll(
                new QuickSortConcurrente(arreglo, inicio, indicePivote - 1, umbral),
                new QuickSortConcurrente(arreglo, indicePivote + 1, fin, umbral)
            );
        }
    }

    // Método recursivo que implementa el algoritmo de QuickSort de forma secuencial para subarreglos pequeños
    private void quickSortSecuencial(int[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            // Se obtiene el índice del pivote después de realizar la partición
            int indicePivote = particionar(arreglo, inicio, fin);

            // Se ordenan recursivamente a los subarreglos izquierdo y derecho
            quickSortSecuencial(arreglo, inicio, indicePivote - 1);
            quickSortSecuencial(arreglo, indicePivote + 1, fin);
        }
    }

    // Método que organiza los elementos del subarreglo alrededor de un pivote
    private int particionar(int[] arreglo, int inicio, int fin) {
        int pivote = arreglo[fin];       // Se selecciona el último elemento como pivote
        int i = inicio - 1;              // Índice del último elemento menor o igual al pivote

        // Se recorre el subarreglo comparando cada elemento con el pivote
        for (int j = inicio; j < fin; j++) {
            if (arreglo[j] <= pivote) {
                i++;
                intercambiar(arreglo, i, j); // Se intercambian los elementos para mantener el orden
            }
        }

        // Se coloca el pivote en su posición final dentro del arreglo
        intercambiar(arreglo, i + 1, fin);
        return i + 1; // Se retorna la posición donde quedó ubicado el pivote
    }

    // Método auxiliar que intercambia dos elementos dentro del arreglo
    private void intercambiar(int[] arreglo, int i, int j) {
        int temporal = arreglo[i];    // Se almacena temporalmente el valor de la posición i
        arreglo[i] = arreglo[j];      // Se asigna el valor de la posición j a la posición i
        arreglo[j] = temporal;        // Se asigna el valor temporal a la posición j
    }
}