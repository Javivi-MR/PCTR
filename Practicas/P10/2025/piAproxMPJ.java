//javac -cp C:\mpj-v0_44\lib\mpj.jar .\piAproxMPJ.java
//mpjrun.bat -np 5 piAproxMPJ

import mpi.MPI;

public class piAproxMPJ {
    public static void main(String[] args) {
        MPI.Init(args);
        
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        
        double PI25DT = 3.141592653589793238462643;
        int n = 1000; // Número de subintervalos
        
        double a = 0.0;
        double b = 1.0;
        double incX = (b - a) / n;
        
        double localSum = 0.0;
        
        if (rank == 0) {
            int subintervalo = n / (size - 1); // Se divide cada subintervalo para cada proceso
            int restante = n % (size - 1); // Si el nº de subintervalos no es multiplo de 4 el resto se reparte por orden
            
            for (int i = 1; i <= size - 1; i++) {
                int inicio = (i - 1) * subintervalo + Math.min(i - 1, restante);
                int fin = inicio + subintervalo;
                if(i <= restante)
                    fin += 1;
                int[] rango = {inicio, fin};
                MPI.COMM_WORLD.Send(rango, 0, 2, MPI.INT, i, 0); // se envia el rango del subintervalo al proceso slave
            }
            
            // Recibir resultados parciales
            double[] SumaParcial = new double[1];
            double SumaTotal = 0.0;
            for (int i = 1; i <= size - 1; i++) {
                MPI.COMM_WORLD.Recv(SumaParcial, 0, 1, MPI.DOUBLE, i, 0); // Se recibe el resultado de cada proceso y se acumula (se podría usar Reduce)
                SumaTotal += SumaParcial[0];
            }
            
            System.out.printf("El valor aproximado de PI es: %.15f, con un error de %.15f%n", SumaTotal, Math.abs(SumaTotal - PI25DT));
        } 
        
        else {
            // Procesos esclavos: calcular parte asignada
            int[] rango = new int[2];
            MPI.COMM_WORLD.Recv(rango, 0, 2, MPI.INT, 0, 0); // Cada proceso slave recibe el rango del subintervalo
            int inicio = rango[0];
            int fin = rango[1];

            for (int i = inicio; i < fin; i++) {
                double x = a + (i + 0.5) * incX;
                double fx = 4.0 / (1.0 + Math.pow(x, 2));
                localSum += fx * incX;
                }
            
            
            double[] result = {localSum};
            MPI.COMM_WORLD.Send(result, 0, 1, MPI.DOUBLE, 0, 0); // Se envia el resultado al proceso 0
        }
        
        MPI.Finalize();
    }
}
