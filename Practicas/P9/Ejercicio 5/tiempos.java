import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class tiempos extends Thread{
    public static ReentrantLock lock = new ReentrantLock();
    public static Semaphore sem = new Semaphore(1);
    public static AtomicInteger naux = new AtomicInteger();
    public static int n = 0;
    public static long time = 0;
    private int id;

    public tiempos(int id){
        this.id = id;
        naux.set(0);
    }



    public void run(){
        if(id == 1){
            long iniTime = System.nanoTime();
            for(int i = 0 ; i < 10000000; i++){
                lock.lock();
                n++;
                lock.unlock();
            }
            long finTime = System.nanoTime();
            lock.lock();
            time += (finTime-iniTime);
            lock.unlock();
        }
        if(id == 2){
            long iniTime = System.nanoTime();
            for(int i = 0 ; i < 10000000; i++){
                try{sem.acquire();}catch(Exception e){e.printStackTrace();};
                n++;
                sem.release();
            }
            long finTime = System.nanoTime();
            lock.lock();
            time += (finTime-iniTime);
            lock.unlock();
        }
        if(id == 3){
            long iniTime = System.nanoTime();
            for(int i = 0 ; i < 10000000; i++){
                naux.getAndIncrement();
            }
            long finTime = System.nanoTime();
            lock.lock();
            time += (finTime-iniTime);
            lock.unlock();
        }
    }

    public static void main(String[] args){
        tiempos th1 = new tiempos(1);
        tiempos th2 = new tiempos(1);
        th1.start(); th2.start();
        try{th1.join(); th2.join();}catch(Exception e){e.printStackTrace();}
        System.out.println("Metodo cerrojo Reentrante - n: " + n + " - Tiempo: " + time/1000000 + " ms");

        time = 0; n = 0;

        tiempos th3 = new tiempos(2);
        tiempos th4 = new tiempos(2);
        th3.start(); th4.start();
        try{th3.join(); th4.join();}catch(Exception e){e.printStackTrace();}
        System.out.println("Metodo semaforo - n: " + n + " - Tiempo: " + time/1000000 + " ms");

        time = 0;

        tiempos th5 = new tiempos(3);
        tiempos th6 = new tiempos(3);
        th5.start(); th6.start();
        try{th5.join(); th6.join();}catch(Exception e){e.printStackTrace();}
        System.out.println("Metodo atomic - naux: " + naux + " - Tiempo: " + time/1000000 + " ms");

    }
}
