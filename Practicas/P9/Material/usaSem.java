public class usaSem 
  extends Thread{

  public static long n = 0;
  public Sem semaforo;

  public usaSem(Sem semaforo){this.semaforo=semaforo;}

  public void run(){
    for(long i=0; i<40000000; i++){
      try{semaforo.waitS();}catch (InterruptedException e){}
      n++;
      try{semaforo.signalS();}catch (InterruptedException e){}
    }
  }

  public static void main(String[] args) throws Exception{
    Sem mon_semaforo = new Sem(1);
    usaSem A         = new usaSem(mon_semaforo);  
    usaSem B         = new usaSem(mon_semaforo);
    A.start(); B.start();
    A.join(); B.join();
    System.out.println(n);
  }
}