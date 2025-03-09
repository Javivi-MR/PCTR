import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class monitorImpresorasAN {
    private final int totalImpresoras = 3;
    private int impresorasDisponibles;
    private final boolean[] impresorasLibres;
    private final Lock lock = new ReentrantLock();
    private final Condition impresoraDisponible = lock.newCondition();

    public monitorImpresorasAN() {
        this.impresorasDisponibles = totalImpresoras;
        this.impresorasLibres = new boolean[totalImpresoras];
        for (int i = 0; i < totalImpresoras; i++) {
            this.impresorasLibres[i] = true;
        }
    }

    public int pedirImpresora() throws InterruptedException {
        lock.lock();
        try {
            while (impresorasDisponibles == 0) {
                impresoraDisponible.await();
            }
            
            int i = 0;
            while (i < totalImpresoras && !impresorasLibres[i]) {
                i++;
            }
            
            impresorasLibres[i] = false;
            impresorasDisponibles--;
            return i;
        } finally {
            lock.unlock();
        }
    }

    public void liberarImpresora(int n) {
        lock.lock();
        try {
            impresorasLibres[n] = true;
            impresorasDisponibles++;
            impresoraDisponible.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
