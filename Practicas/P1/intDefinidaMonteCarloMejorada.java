import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class intDefinidaMonteCarloMejorada{

    public static void main(String[] args)
    throws Exception{
        Function f = (double x) -> { return x; };
        Function g = (double x) -> { return Math.sin(x); };
        Scanner s = new Scanner(System.in);
        System.out.print("Introduce el numero de puntos: ");
        int n = s.nextInt();
        MonteCarlo(n,f);
        MonteCarlo(n,g);
        s.close();
    }

    interface Function{
        double value (double x);
    }

    public static void MonteCarlo(int n, Function f)
    {
        // para la funcion x
        int contExitos = 0;
        Random random = new Random();
        for(int i = 0 ; i < n ; i++)
        {
            double x = random.nextDouble((double) 1.0);
            double y = random.nextDouble((double) 1.0);
            if (y <= f.value(x))
                contExitos++;
        }
        System.out.println("La integral aproximada de la funcion x en el rango [0-1] es: " + (float) contExitos/n);
    }
}