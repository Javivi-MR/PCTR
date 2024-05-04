import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class lecEscAN {
    private ReentrantLock lock;
    private Condition isFull;
    private Condition isEmpty;
    private int n, indL, indE;
    private int[] buffer;

    public lecEscAN(int n) {
        this.lock = new ReentrantLock();
        this.isFull = lock.newCondition();
        this.isEmpty = lock.newCondition();
        this.n = n;
        this.indL = 0;
        this.indE = 0;
        this.buffer = new int[n];
    }

    public int leer() throws InterruptedException {
        lock.lock();
        try {
            while (indL >= indE) {
                isEmpty.await();
            }

            indL++;
            isFull.signal();
            return buffer[indL - 1];
        } finally {
            lock.unlock();
        }
    }

    public void escribir(int valor) {
        lock.lock();
        try {
            while (indE - indL >= n) {
                isFull.await();
            }

            buffer[indE % n] = valor;
            indE++;
            isEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}