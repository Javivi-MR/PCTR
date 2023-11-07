import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class prodMatricesParalelo{
    public static int n = 1000;
    public static int[][] A,B,C;

    public static void main(String[] args) throws Exception{
        A = new int[n][n];
        B = new int[n][n];
        C = new int[n][n];

        // Inicializamos las matrices
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                A[i][j] = 1;
                B[i][j] = 2;
                C[i][j] = 0;
            }
        }

        // Inicializamos y lanzamos los hilos
        //int nHilos = Integer.parseInt(args[0]);
        long iniTiempo = System.nanoTime();
        int nHilos = Runtime.getRuntime().availableProcessors();
        ExecutorService eFijo = Executors.newFixedThreadPool(nHilos);
        int nTareas = n / nHilos;
        for(int i = 0 ; i < nHilos*nTareas ; i+=nTareas){
            if(i == 0) eFijo.execute(new tareaProdMatrices(i,(i+1)*nTareas));
            else eFijo.execute(new tareaProdMatrices(i,i+nTareas));
        }
        eFijo.shutdown();
        eFijo.awaitTermination(10, TimeUnit.SECONDS);
        long finTiempo = System.nanoTime();

        System.out.println("Tiempo: " + (finTiempo - iniTiempo)/1000000 + " ms");

        // Mostramos el resultado
         /* 
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }*/
    }
}

class tareaProdMatrices implements Runnable{
    private int ini, fin;

    public tareaProdMatrices(int ini,int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public void run(){
        for(int i = ini ; i < fin ; i++){
            for(int j = 0 ; j < prodMatricesParalelo.n ; j++){
                for(int k = 0 ; k < prodMatricesParalelo.n; k++){
                    prodMatricesParalelo.C[i][j] += (prodMatricesParalelo.A[j][k] * prodMatricesParalelo.B[k][j]);
                }
            }
        }
    }
}