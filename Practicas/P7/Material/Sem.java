public class Sem{
  private int s;
  public Sem(int s){
    this.s = s;
  }
  public synchronized void waitS(){
    while(s==0)try{wait();}catch(InterruptedException e){}
    s=s-1;
  }
  public synchronized void signalS(){
    s=s+1;
    notifyAll();
  }
}
