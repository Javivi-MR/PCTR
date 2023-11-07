import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class volcadoRed
{
  public static void main(String[] args) throws Exception
  {
    long iniTiempo=0;
    LinkedList<tareaRed> tareas = new LinkedList<tareaRed>();
    int nNuc = Runtime.getRuntime().availableProcessors();
    float Cb = Float.parseFloat(args[0]);
    int tamPool = (int)(nNuc/(1-Cb));
    ThreadPoolExecutor ept = new ThreadPoolExecutor(
    	    tamPool, 
    	    tamPool,
    	    0L,
            TimeUnit.MILLISECONDS, 
            new LinkedBlockingQueue<Runnable>());
    ept.prestartAllCoreThreads(); //crear inmediatamente los hilos. (no se espera a la tarea)
    try {
    	 int cont = 0;
    	 String linea=" ";      
    	 RandomAccessFile direcciones = new RandomAccessFile("direccionesRed.txt","r");
         iniTiempo = System.nanoTime();
         while(linea!=null){
           linea =(String)direcciones.readLine();
           if(linea!=null)tareas.add(new tareaRed(linea, cont));
           cont++;
         }
         direcciones.close();
        } catch (EOFException e) {}
    for (Iterator iter = tareas.iterator(); iter.hasNext();) 
      ept.execute((Runnable)iter.next());
    ept.shutdown();
    while(!ept.isTerminated()){}
    long finTiempo = System.nanoTime();
    System.out.println("Numero de Nucleos: "+nNuc);
    System.out.println("Coficiente de Bloqueo: "+Cb);
    System.out.println("Tamano del Pool: "+tamPool);
    System.out.println("Tiempo Total (segundos): "+(finTiempo-iniTiempo)/1.0e9);                              
  }
}  

//NO TIENE SENTIDO CALCULAR EL SPEEDUP EN PROBLEMAS CON COEFICIENTES DE BLOQUEAMIENTO.