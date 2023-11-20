import java.util.concurrent.*;

public class usaProdCon extends Thread{
    private int id;
    public PCMonitor monitor;

    public usaProdCon(int id, PCMonitor m){
        this.id = id;
        this.monitor = m;
    }
    
    public void run(){
        if(id == 0){  //consumidor
            int V = monitor.Take();
        }
        else{ //productor
            //use threadrandom to generate a random number
            int V = ThreadLocalRandom.current().nextInt(0, 1000);
            monitor.Append(V);
        }
    }

    public static void main(String[] args){
        PCMonitor mon = new PCMonitor();
        usaProdCon A = new usaProdCon(1, mon);
        usaProdCon B = new usaProdCon(1, mon);
        usaProdCon C = new usaProdCon(0, mon);
        //usaProdCon D = new usaProdCon(1, mon);
        A.start(); B.start(); C.start(); //D.start();
        try{
            A.join(); C.join(); B.join();  //D.join();
        }catch(InterruptedException e){}
    }
}
