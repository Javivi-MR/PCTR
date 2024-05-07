import java.util.concurrent.Semaphore;

public class ccSem {
    public int saldo;
    public Semaphore sem = new Semaphore(2);

    public ccSem(int saldo) {
        this.saldo = saldo;
    }

    public void ingreso(int cuantia) {
        try{sem.acquire();} catch(Exception e){e.printStackTrace();}
        saldo += cuantia;
        sem.release();
    }

    public void reintegro(int cuantia) throws InterruptedException {
        try{sem.acquire();} catch(Exception e){e.printStackTrace();}
        while (saldo < cuantia) {
            sem.release();
        }
        saldo -= cuantia;
        sem.release();
    }
}