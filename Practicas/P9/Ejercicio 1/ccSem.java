import java.util.concurrent.Semaphore;

public class ccSem extends Thread{
    public static Semaphore sem = new Semaphore(1);
    public static int n = 0;

    public ccSem(){}

    public void run(){
        for(int i = 0 ; i < 4000000 ; i++){
            try{sem.acquire();}catch(Exception e){e.printStackTrace();}
            n++;
            sem.release();
        }
    }

    public static void main(String[] args){
        ccSem th1 = new ccSem();
        ccSem th2 = new ccSem();
        ccSem th3 = new ccSem();


        th1.start(); th2.start(); th3.start();

        try{
            th1.join(); th2.join(); th3.join();
        }catch(Exception e){e.printStackTrace();}

        System.out.println("Valor final de n: " + n);
    }
}
