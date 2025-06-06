package algoritmo;

public class QuickSortSecuencial {

    // Método principal que inicia el ordenamiento mediante QuickSort
    public void quickSort(int[] arreglo) {
        quickSort(arreglo, 0, arreglo.length - 1);
    }

    // Método recursivo que implementa el algoritmo de ordenamiento QuickSort
    private void quickSort(int[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            // Se obtiene el índice del pivote después de realizar la partición
            int indicePivote = particionar(arreglo, inicio, fin);
            
            // Se aplica QuickSort recursivamente a los subarreglos izquierdo y derecho
            quickSort(arreglo, inicio, indicePivote - 1);
            quickSort(arreglo, indicePivote + 1, fin);
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