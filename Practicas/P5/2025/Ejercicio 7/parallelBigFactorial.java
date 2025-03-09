import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Scanner;

public class parallelBigFactorial {

    // Clase interna que representa una tarea para calcular el producto de un rango de números
    static class FactorialTask implements Callable<BigInteger> {
        private final int inicio;
        private final int fin;

        public FactorialTask(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        public BigInteger call() {
            BigInteger resultado = BigInteger.ONE;
            for (int i = inicio; i <= fin; i++) {
                resultado = resultado.multiply(BigInteger.valueOf(i));
            }
            return resultado;
        }
    }

    // Método para calcular el factorial en paralelo
    public static BigInteger Factorial(int numero) throws InterruptedException, ExecutionException {
        int numHilos = Runtime.getRuntime().availableProcessors(); // Determinamos el número de hilos
        ExecutorService executor = Executors.newFixedThreadPool(numHilos); // Pool de hilos

        List<Future<BigInteger>> resultados = new ArrayList<>();
        int tamanoSegmento = numero / numHilos;

        // Dividimos el rango en segmentos y asignamos cada uno a una tarea
        for (int i = 0; i < numHilos; i++) {
            int inicio = i * tamanoSegmento + 1;
            int fin = (i == numHilos - 1) ? numero : (inicio + tamanoSegmento - 1);

            // Creamos una tarea para el rango de números y la enviamos al executor
            FactorialTask tarea = new FactorialTask(inicio, fin);
            Future<BigInteger> futuro = executor.submit(tarea);
            resultados.add(futuro);
        }

        // Recogemos los resultados parciales y los multiplicamos para obtener el factorial completo
        BigInteger factorial = BigInteger.ONE;
        for (Future<BigInteger> futuro : resultados) {
            factorial = factorial.multiply(futuro.get());
        }

        executor.shutdown(); // Cerramos el executor
        return factorial;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedimos al usuario que introduzca un número
        System.out.print("Introduce un número para calcular su factorial en paralelo: ");
        int numero = scanner.nextInt();

        // Validamos si el número es no negativo
        if (numero < 0) {
            System.out.println("El factorial no está definido para números negativos.");
        } else {
            try {
                // Calculamos el factorial en paralelo y mostramos el resultado
                BigInteger resultado = Factorial(numero);
                System.out.println("El factorial de " + numero + " es: " + resultado);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error en el cálculo paralelo: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
