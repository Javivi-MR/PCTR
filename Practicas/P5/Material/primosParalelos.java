
import java.util.concurrent.*;
import java.util.*;

public class primosParalelos {

  public static void main(String[] args) throws Exception {
    long nPuntos     = Long.parseLong(args[0]);
    int  nTareas     = Integer.parseInt(args[1]);
    //int  nTareas     = Runtime.getRuntime().availableProcessors();
    long tVentana    = nPuntos/nTareas;
    long primosTotal = 0;
    long linf        = 0;
    long lsup        = tVentana;
    
    List<Future<Long>> contParciales = Collections.synchronizedList(
      new ArrayList<Future<Long>>());
    long inicTiempo = System.nanoTime();  
    ThreadPoolExecutor ept = new ThreadPoolExecutor( //no tiene sentido hacer un pool en un problema que tiene coeficiente de bloqueamiento (cb) = 0
      nTareas,
      nTareas,
      0L,
      TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<Runnable>());
    for(int i=0; i<nTareas; i++){
      contParciales.add(ept.submit(
      	 new tareaPrimos(linf, lsup)));
      linf=lsup+1;
      lsup+=tVentana;
    }  
    for(Future<Long> iterador:contParciales)
      try{
      	  primosTotal +=  iterador.get(); 
      }catch (CancellationException e){}
       catch (ExecutionException e){}
       catch (InterruptedException e){}     
    long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e9;   
    ept.shutdown();
    System.out.println("Primos hallados: "+primosTotal);
    System.out.println("Calculo finalizado en "+tiempoTotal+" segundos");
 }   
}
