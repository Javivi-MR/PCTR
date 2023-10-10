import java.util.Scanner;
import java.lang.Math;

public class NewtonRaphsonMejorada {

    public static void main(String[] args)
    throws Exception{
        Scanner s = new Scanner(System.in);
        System.out.print("Introduce el numero de iteraciones: ");
        int n = s.nextInt();
        System.out.print("Introduce un numero del rango [0-1]: ");
        double x1 = s.nextDouble();

        Function f = (double x) -> {return Math.cos(x) - Math.pow(x,3);};
        Function df = (double x) -> {return -Math.sin(x) - 3*Math.pow(x,2);};
        MetodoNewtonRaphson(n,x1,f,df);

        System.out.print("Introduce un numero del rango [2-3]: ");
        x1 = s.nextDouble();
        Function g = (double x) -> {return Math.pow(x,2) - 5;};
        Function dg = (double x) -> {return 2*x;};
        MetodoNewtonRaphson(n,x1,g,dg);
        
        s.close();
    }

    interface Function{
        double value (double x);
    }

    public static void MetodoNewtonRaphson(int n, double x1, Function f, Function df)
    {
        double xn = x1;
        for(int i = 0 ; i < n ; i++)
        {
            if(f.value(xn) != 0)
            {
                double xn1 = xn - (f.value(xn) / df.value(xn));
                System.out.println("Iteracion " + i + ", Aproximacion "+ xn1);
                xn = xn1;
            }
        }
        System.out.println("Resultado: " + xn);
    }
}
