import java.util.concurrent.*;

public class timing{

  public static long f(long iter){
    int n = 0;
    Semaphore s = new Semaphore(1);
    long ini=System.nanoTime();
    for(long i=0; i<iter; i++){
	try{s.acquire();}catch(InterruptedException e){}
	try{n++;}
	finally{s.release();}
    }
   long fin=System.nanoTime();
   return(fin-ini);
  }

public static long g(long iter){
    int n = 0;
    Object o = new Object();
    long ini=System.nanoTime();
    for(long i=0; i<iter; i++){
	synchronized(o){n++;}
    }
   long fin=System.nanoTime();
   return(fin-ini);
  }
  public static void main(String[] args){
    long it = 100000000;
    System.out.println("Tiempo para semaforos   : "+f(it)+" nanosegundos...");
    System.out.println("Tiempo para synchronized: "+g(it)+" nanosegundos...");
  }
  
}

