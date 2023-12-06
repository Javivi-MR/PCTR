import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class lecEscAN {
    private ReentrantLock lock;
    private Condition isFull;
    private Condition isEmpty;
    private int n,indL,indE;
    private int[] buffer;

    public lecEscAN(int n){
        this.lock = new ReentrantLock();
        this.isFull = lock.newCondition();
        this.isEmpty = lock.newCondition();
        this.n = n;
        this.indE = 0; this.indL = 0;
        this.buffer = new int[n];
    }

    public int leer(){
        while(indL >= indE){
            try{isEmpty.wait();}catch(Exception e){e.printStackTrace();};
        }
        indL++;
        isFull.signal();
        return buffer[indL-1];
    }

    public void escribir(int value){
        while(indE-indL > 0){
            try{isFull.await();}catch(Exception e){e.printStackTrace();}
        }
        buffer[indE % n] = value;
        indE++;
        isEmpty.signal();
    }
}
