import java.util.*;
public class matrizPorFilas
  extends Thread{

  int [][] matrizInput, matrizOutput;
  int inicio, fin;

  public matrizPorFilas(int inicio, int fin, int[][] m, int[][] n){
    this.inicio=inicio; this.fin=fin; this.matrizInput=m; this.matrizOutput=n;}

  public void run(){
    for(int i=inicio; i<=fin; i++)
      for(int j=0; j<matrizInput.length; j++)
        matrizOutput[i][j]=matrizInput[i][j]+matrizOutput[i][j];
  }
  public static void printMatriz (int[][] m){
    for(int i=0; i<m.length; i++)
      for(int j=0; j<m.length; j++)
        if(j==m.length-1)System.out.println(m[i][j]);
        else System.out.print(m[i][j]+" ");
  }
  public static void main(String[] args)
    throws Exception{
   Random generador = new Random();
   int[][] matrix = new int[24000][24000];
   int[][] matrixx = new int[24000][24000];
    System.out.println("Llenando matrices. Este tiempo no cuenta...");
    for(int i=0; i<matrix.length; i++)
      for(int j=0; j<matrix.length; j++){
        matrix[i][j]  = generador.nextInt(32000);
        matrixx[i][j] = generador.nextInt(32000);}
    System.out.println("Matrices llenas...");
    //printMatriz(matrix);
    //System.out.println("\n");
    //printMatriz(matrixx);
    //System.out.println("\n");
    //procesamiento con una tarea...
    System.out.println("procesando con una hebra...");
    long inicTiempo = System.nanoTime();
    matrizPorFilas h1 = new matrizPorFilas(0, 23999, matrix, matrixx);
    h1.start(); h1.join();
    long tiempoTotal = System.nanoTime()-inicTiempo;
    System.out.println("en "+tiempoTotal+" nanosegundos...");
    //printMatriz(matrixx);
    System.out.println("Llenando matrices con nuevos valores. Este tiempo no cuenta...");
    for(int i=0; i<matrix.length; i++)
      for(int j=0; j<matrix.length; j++){
        matrix[i][j]  = generador.nextInt(32000);
        matrixx[i][j] = generador.nextInt(32000);}
    System.out.println("Matrices llenas...");
    //printMatriz(matrix);
    //System.out.println("\n");
    //printMatriz(matrixx);
    System.out.println("Estabilizamos los cores durante unos segundos... e insinuamos a la JVM que limpie...");
    System.gc();
    Thread mainThread=Thread.currentThread();
    mainThread.sleep(6000);
    //procesamiento con cuatro tareas...
    System.out.println("procesando con ocho hebras...");
    inicTiempo = System.nanoTime();  
    h1 = new matrizPorFilas(0, 2999, matrix, matrixx);
    matrizPorFilas h2 = new matrizPorFilas(3000, 5999, matrix, matrixx);
    matrizPorFilas h3 = new matrizPorFilas(6000, 8999, matrix, matrixx);
    matrizPorFilas h4 = new matrizPorFilas(9000, 11999, matrix, matrixx);
    matrizPorFilas h5 = new matrizPorFilas(12000, 14999, matrix, matrixx);
    matrizPorFilas h6 = new matrizPorFilas(15000, 17999, matrix, matrixx);
    matrizPorFilas h7 = new matrizPorFilas(18000, 20999, matrix, matrixx);
    matrizPorFilas h8 = new matrizPorFilas(21000, 23999, matrix, matrixx);
    h1.start(); h2.start(); h3.start(); h4.start(); h5.start(); h6.start(); h7.start(); h8.start(); 
    h1.join();h2.join(); h3.join(); h4.join(); h5.join(); h6.join(); h7.join(); h8.join();
    tiempoTotal = System.nanoTime()-inicTiempo;
    //xºprintMatriz(matrixx);
    System.out.println("en "+tiempoTotal+" nanosegundos...");
  }
}
