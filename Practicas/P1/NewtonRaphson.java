import java.util.Scanner;
import java.lang.Math;

public class NewtonRaphson{
    public static void main(String[] args)
    throws Exception{
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        MetodoNewtonRaphson(n);
        s.close();
    }

    public static void MetodoNewtonRaphson(int n)
    {
        //funcion cos(x) - x^3 -> f' -sin(x) - 3x^2
        System.out.println("------ Funcion cos(x) - x^3 ------");
        double xn = 1; //cota superior 1
        for(int i = 0 ; i < n ; i++)
        {
            if(Math.cos(xn) - Math.pow(xn,3) != 0)
            {
                double xn1 = xn - ((Math.cos(xn) - Math.pow(xn,3))/(-Math.sin(xn) - 3*Math.pow(xn,2)));
                System.out.println("Iteracion " + i + ", Aproximacion "+ xn1);
                xn = xn1;
            }
        }
        System.out.println("Resultado: " + xn);

        //funcion x^2 - 5 -> f' 2x
        System.out.println("------ Funcion x^2 - 5 ------");
        xn = 3; //cota superior 3
        for(int i = 0 ; i < n ; i++)
        {
            if(Math.cos(xn) - Math.pow(xn,3) != 0)
            {
                double xn1 = xn - ((Math.pow(xn,2) - 5)/(2*xn));
                System.out.println("Iteracion " + i + ", Aproximacion "+ xn1);
                xn = xn1;
            }
        }
        System.out.println("Resultado: " + xn);
    }
}
