import java.util.Random;

public class matVector {
    public static void main(String[] args){
        int n = 10000;
        int[][] A;
        A = new int[n][n];
        int[] b,y;
        b = new int[n];
        y = new int[n];
        Random g = new Random(System.nanoTime());
        
        //Rellenar matrices
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                A[i][j] = g.nextInt();
            }
            b[i] = g.nextInt();
            y[i] = 0;
        }

        //comenzar multiplicacion
        long tiempoIni = System.nanoTime();
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n ; j++){
                y[i] += A[i][j]*b[i];
            }
        }
        long tiempoFin = System.nanoTime();

        System.out.println("Metodo secuencial: ");
        System.out.println("Se ha tardado: " + (tiempoFin-tiempoIni)/100000 + " ms en realizar la multiplicacion");
    }
}
