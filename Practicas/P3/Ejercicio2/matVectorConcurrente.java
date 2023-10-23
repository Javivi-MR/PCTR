import java.util.Random;

public class matVectorConcurrente extends Thread{
    private static int[][] A;
    private static int[] b,y;
    private int id,ini,fin;
    public static int n = 10000;

    public matVectorConcurrente(int id,int ini,int fin){
        this.id = id;
        this.ini = ini;
        this.fin = fin;
    }

    public void run(){
        for(int i = ini; i < fin ; i++){
            for(int j = 0 ; j < n ; j++){
                y[i] += (A[i][j]*b[i]);
            }
        }
    }

    //#GraciasTomeu:)
    public static void printMatriz (int[][] m){
        for(int i=0; i<m.length; i++)
          for(int j=0; j<m.length; j++)
            if(j==m.length-1)System.out.println(m[i][j]);
            else System.out.print(m[i][j]+" ");
      }

    public static void printVector (int[] v){
        for(int i = 0 ; i < v.length-1 ; i++){
            System.out.print(v[i] + ", ");
        }
        System.out.print(v[v.length-1] + "\n");
    }

    public static void main(String[] args) throws InterruptedException{
        A = new int[n][n];
        b = new int[n];
        y = new int[n];
        int numThreads = 2;
        Random g = new Random(System.nanoTime());

        //Rellenando matrices
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                A[i][j] = g.nextInt();
            }
            b[i] = g.nextInt();
            y[i] = 0;
        }

        //Definimos threads
        matVectorConcurrente[] threads = new matVectorConcurrente[numThreads];
        if(n%numThreads == 0)
            for(int i = 0 ; i < numThreads ; i++) threads[i] = new matVectorConcurrente(i,(n/numThreads)* i, (n/numThreads)*(i+1)); 
        else{
            for(int i = 0 ; i < numThreads-1 ; i++) threads[i] = new matVectorConcurrente(i,(n/numThreads)* i, (n/numThreads)*(i+1));
            threads[numThreads-1] = new matVectorConcurrente(numThreads-1,(n/numThreads)* (numThreads-1), n);
        }

        //Comenzamos multiplicar
        long tiempoIni = System.nanoTime();
        for(int i = 0 ; i < numThreads ; i++) threads[i].start();
        for(int i = 0 ; i < numThreads ; i++) threads[i].join();
        long tiempoFin = System.nanoTime();

        //System.out.print("Matriz A: \n");
        //printMatriz(A);
        //System.out.print("\n Vector b: \n");
        //printVector(b);
        //System.out.print("\n Vector y: \n");
        //printVector(y);


        System.out.println("\nMetodo paralelo: ");
        System.out.println("Se ha tardado: " + (tiempoFin-tiempoIni)/100000 + " ms en realizar la multiplicacion");   
    }

}
