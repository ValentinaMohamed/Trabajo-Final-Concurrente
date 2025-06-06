package util;

import java.util.Random;

public class Utilidades {

    // Método que genera un arreglo de enteros con valores aleatorios
    public static int[] generarArrayAleatorio(int n) {
        Random rand = new Random();       // Se crea una instancia de Random para generar números aleatorios
        int[] arr = new int[n];       // Se crea un nuevo arreglo de tamaño n

        // Se llena el arreglo con números aleatorios
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt();  // Se asigna un número entero aleatorio en cada posición
        }

        return arr; // Se retorna el arreglo generado
    }
    
    // Método que verifica si un arreglo de enteros está ordenado de forma ascendente
    public static boolean estaOrdenado(int[] arr) {
        // Se recorre el arreglo comparando elementos consecutivos
        for (int i = 0; i < arr.length - 1; i++) {
            // Si se encuentra un elemento mayor que el siguiente, no está ordenado
            if (arr[i] > arr[i + 1]) {
                return false; // Retorna false si hay un par desordenado
            }
        }
        return true; // Retorna true si el arreglo está completamente ordenado
    }
}