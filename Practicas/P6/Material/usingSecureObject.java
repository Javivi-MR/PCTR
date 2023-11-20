public class usingSecureObject
  extends Thread{
  public static long iterations = 1000000;
  public secureObject obj;
  
  public usingSecureObject(secureObject obj){this.obj = obj;}
  public void run(){
    for(long i=0; i<iterations; i++)obj.inc();
  }

  public static void main(String[] args) throws Exception{
    secureObject o      = new secureObject();
    usingSecureObject A = new usingSecureObject(o);   
    usingSecureObject B = new usingSecureObject(o);  
    A.start(); B.start();
    A.join(); B.join();
    System.out.println(o.get());  
  }
}

