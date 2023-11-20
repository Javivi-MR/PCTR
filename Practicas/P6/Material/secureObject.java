public class secureObject{
  public long n = 0;
   
  public secureObject(){}
  public synchronized void inc(){n++;}
  public synchronized long get(){return this.n;}
}

