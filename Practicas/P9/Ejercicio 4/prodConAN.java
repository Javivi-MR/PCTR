import java.util.concurrent.locks.*;

public class prodConAN{
  private int N;
  private int actualCons;
  private int actualProd;
  private int[] buffer; //array circular
  public ReentrantLock lock = new ReentrantLock(); //ahora no son estaticos debido a que el control depende del objeto, no de la clase.
  Condition notEmpty = lock.newCondition();
  Condition notFull = lock.newCondition();

  public prodConAN(int N){
    this.N = N;
    this.buffer = new int[N];
    this.actualCons = 0;
    this.actualProd = 0;
  }

  public void produce(int value){
      lock.lock();
      while(actualProd-actualCons > 0){
        System.out.println("El buffer esta lleno, el hilo " + Thread.currentThread().getName() + " se va a dormir");
        try{notFull.await();}catch(Exception e){e.printStackTrace();} 
      }
      buffer[actualProd % N] = value;
      System.out.println("El hilo " + Thread.currentThread().getName() + " ha producido el valor: " + value);
      actualProd++;
      notEmpty.signal();
      lock.unlock();
  }

  public int consume(){
    lock.lock();
    int value;
    while(actualCons >= actualProd){
      try{notEmpty.await();}catch(Exception e){e.printStackTrace();}
    }
    value = buffer[actualCons%N];
    System.out.println("El hilo " + Thread.currentThread().getName() + " ha consumido el valor: " + value);
    actualCons++;
    notFull.signal();
    lock.unlock();
    return value;
  }

}

