import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class intDefinidaMonteCarlo{

    public static void main(String[] args)
    throws Exception{
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        MonteCarlo(n);
        s.close();
    }

    public static void MonteCarlo(int n)
    {
        // para la funcion x

        int contExitos = 0;
        Random random = new Random();
        for(int i = 0 ; i < n ; i++)
        {
            float x = random.nextFloat((float) 1.0);
            float y = random.nextFloat((float) 1.0);
            if (y <= x)
                contExitos++;
        }
        System.out.println("La integral aproximada de la funcion x en el rango [0-1] es: " + (float) contExitos/n);
        
        //para la funcion sin(x)

        contExitos = 0;
        for(int i = 0 ; i < n ; i++)
        {
            float x = random.nextFloat((float) 1.0);
            float y = random.nextFloat((float) 1.0);
            if(y <= Math.sin(x))
                contExitos++;
        }
        System.out.println("La integral aproximada de la funcion sin(x) en el rango [0-1] es: " + (float) contExitos/n);
    }
}