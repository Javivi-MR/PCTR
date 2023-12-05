import java.util.concurrent.locks.ReentrantLock;

public class secureCriticalSectionRLock
  extends Thread{
  public static long iterations = 4000000;
  public static long n            = 0;
  public static ReentrantLock lock = new ReentrantLock(); //estatico porque es compartido por todas las hebras
  
  public secureCriticalSectionRLock(){}

  public void run(){
    for(long i=0; i<iterations; i++){
      lock.lock();
      try {n++;} finally {
       lock.unlock();
     }
   }
  }

  public static void main(String[] args) throws Exception{
    secureCriticalSectionRLock A = new secureCriticalSectionRLock();   
    secureCriticalSectionRLock B = new secureCriticalSectionRLock();  
    A.start(); B.start();
    A.join(); B.join();
    System.out.println(n);  
  }
}

