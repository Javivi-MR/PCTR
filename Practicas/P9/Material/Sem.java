import java.util.concurrent.locks.*;

public class Sem{
  private int s;
  final ReentrantLock lock = new ReentrantLock();
  final Condition  semZero = lock.newCondition();

  public Sem(int s){
    this.s = s;
  }
  public void waitS() throws InterruptedException{
    lock.lock();
    try{
      while(s==0)semZero.await();
      s=s-1; 
    }finally {lock.unlock();}
  }

  public void signalS() throws InterruptedException{
    lock.lock();
    try{
      s=s+1;
      semZero.signalAll();
     }finally {lock.unlock();}
  }
}
