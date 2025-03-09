import java.math.BigInteger;
import java.util.Scanner;

public class bigFactorial {
    // Método para calcular el factorial de un número
    public static BigInteger calcularFactorial(int numero) {
        BigInteger factorial = BigInteger.ONE; // Inicializamos el factorial en 1

        for (int i = 1; i <= numero; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i)); // factorial = factorial * i
        }

        return factorial;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedimos al usuario que introduzca un número
        System.out.print("Introduce un número para calcular su factorial: ");
        int numero = scanner.nextInt();

        // Validamos si el número es no negativo
        if (numero < 0) {
            System.out.println("El factorial no está definido para números negativos.");
        } else {
            // Calculamos el factorial usando el método calcularFactorial
            BigInteger resultado = calcularFactorial(numero);
            System.out.println("El factorial de " + numero + " es: " + resultado);
        }
        
        scanner.close();
    }
}
