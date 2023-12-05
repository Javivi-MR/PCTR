import java.util.concurrent.Semaphore;

public class secureCriticalSectionSem	
  extends Thread{
  public static long iterations = 4000000;
  public static long n          = 0;
  public static Semaphore sem  = new Semaphore(1);
  
  public void run(){
    for(long i=0; i<iterations; i++){
      	try{sem.acquire();}catch(InterruptedException e){}
	try{n++;}
	finally{sem.release();}
    }
  }

  public static void main(String[] args) throws Exception{
    secureCriticalSectionSem A = new secureCriticalSectionSem();   
    secureCriticalSectionSem B = new secureCriticalSectionSem();  
    A.start(); B.start();
    A.join(); B.join();
    System.out.println(n);  
  }
}

