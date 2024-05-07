import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class cCLR {
    public int saldo;
    public ReentrantLock lock = new ReentrantLock();
    Condition saldoSuficiente = lock.newCondition();

    public cCLR(int saldo) {
        this.saldo = saldo;
    }

    public void ingreso(int cuantia) {
        lock.lock();
        saldo += cuantia;
        saldoSuficiente.signalAll();
        lock.unlock();
    }

    public void reintegro(int cuantia) throws InterruptedException {
        lock.lock();
        while (saldo < cuantia) {
            try{ saldoSuficiente.await();} catch(Exception e){e.printStackTrace();}
        }
        saldo -= cuantia;
        lock.unlock();
    }
}
