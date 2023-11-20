public class secureCriticalSection 
  extends Thread{
  public static long iterations = 1000000;
  public static long n          = 0;
  public static Object lock     = new Object();
  
  public secureCriticalSection(){}
  public void run(){
    for(long i=0; i<iterations; i++)synchronized(lock){n++;}
  }

  public static void main(String[] args) throws Exception{
    secureCriticalSection A = new secureCriticalSection();   
    secureCriticalSection B = new secureCriticalSection();  
    A.start(); B.start();
    A.join(); B.join();
    System.out.println(n);  
  }
}

