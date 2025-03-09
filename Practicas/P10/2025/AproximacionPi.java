public class AproximacionPi {

    public static void main(String[] args) {
        double PI25DT = 3.141592653589793238462643; // Para medir error...
        int    n      = 1000; // Subintervalos para la suma de Riemann; aumentando n, mejora la aproximacion...
        double a      = 0.0;
        double b      = 1.0;
        double incX   = (b-a)/n; // Ancho de los subintervalos
        double suma   = 0.0;
        double x, fx;

        for (int i = 0; i < n; i++) {
            // Cada subintervalo se evalua en el punto medio para suma de Rieman...
            x = a+(i+0.5)*incX;
            fx = 4.0/(1.0+Math.pow(x, 2));
            suma = suma+(fx*incX);
        }
        System.out.printf("El valor aproximado de PI es: %.15f, con un error de %.15f%n", suma, Math.abs(suma - PI25DT));
    }
}