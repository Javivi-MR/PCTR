import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class cuentaCompartidaAN {
    public int saldo;
    private final Lock lock = new ReentrantLock();
    private final Condition saldoSuficiente = lock.newCondition();

    public cuentaCompartidaAN(int saldo) {
        this.saldo = saldo;
    }

    public void ingreso(int cuantia) {
        lock.lock();
        try {
            saldo += cuantia;
            saldoSuficiente.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void reintegro(int cuantia) throws InterruptedException {
        lock.lock();
        try {
            while (saldo < cuantia) {
                saldoSuficiente.await();
            }
            saldo -= cuantia;
        } finally {
            lock.unlock();
        }
    }
}
