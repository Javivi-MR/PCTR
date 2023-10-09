import java.util.concurrent.ThreadLocalRandom;
 
class ThreadLocalRandomNumbers extends Thread{
 
    public void run(){
        try {
            int cota = 10;
            int dato;
            double ddato;
            for(int i = 0; i < 5; i++){
              dato = ThreadLocalRandom.current().nextInt(cota);
              System.out.println( "Thread " + Thread.currentThread().getName()+ " genera " + dato);
              ddato = ThreadLocalRandom.current().nextDouble(1.0);
              System.out.println( "Thread " + Thread.currentThread().getName()+ " genera " + ddato);
            }
        }catch (Exception e) {System.out.println("Exception");}
    }

   public static void main(String[] args) throws InterruptedException{
 
        ThreadLocalRandomNumbers t1 = new ThreadLocalRandomNumbers();
        ThreadLocalRandomNumbers t2 = new ThreadLocalRandomNumbers();
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println( "Main terminando...");

    }
}