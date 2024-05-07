import java.util.concurrent.locks.ReentrantLock;

public class cCLR_prueba extends Thread{
    public static ReentrantLock lock = new ReentrantLock();
    public static int n = 0;

    public cCLR_prueba(){}

    public void run(){
        for(int i = 0; i < 1000000; i++){
            lock.lock();
            n++;
            lock.unlock();
        }
    }

    public static void main(String[] args){
        cCLR_prueba th1 = new cCLR_prueba();
        cCLR_prueba th2 = new cCLR_prueba();
        cCLR_prueba th3 = new cCLR_prueba();

        th1.start(); th2.start(); th3.start();

        try{
            th1.join(); th2.join(); th3. join();
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("Valor final de n: " + n);
    }
}
