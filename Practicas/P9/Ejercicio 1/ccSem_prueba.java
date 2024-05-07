import java.util.concurrent.Semaphore;

public class ccSem_prueba extends Thread{
    public static Semaphore sem = new Semaphore(1);
    public static int n = 0;

    public ccSem_prueba(){}

    public void run(){
        for(int i = 0; i < 1000000; i++){
            try{sem.acquire();} catch(Exception e){e.printStackTrace();}
            n++;
            sem.release();
        }
    }

    public static void main(String[] args){
        ccSem_prueba th1 = new ccSem_prueba();
        ccSem_prueba th2 = new ccSem_prueba();
        ccSem_prueba th3 = new ccSem_prueba();

        th1.start(); th2.start(); th3.start();
        try{
            th1.join(); th2.join(); th3.join();
        } catch(Exception e){e.printStackTrace();}
        System.out.println("Valor final de n: " + n);
    }
}
