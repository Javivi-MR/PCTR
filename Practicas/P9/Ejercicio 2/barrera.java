import java.util.concurrent.CyclicBarrier;

public class barrera extends Thread{
    public static CyclicBarrier b = new CyclicBarrier(3);
    public static int n = 0;

    public barrera(){}

    public void run(){
        System.out.println(Thread.currentThread().getName() + " ha llegado a la barrera");
        try{b.await();}catch(Exception e){e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + " ha pasado a la barrera!!!");
    }

    public static void main(String[] args){
        barrera th1 = new barrera();
        barrera th2 = new barrera();
        barrera th3 = new barrera();

        th1.start(); th2.start(); th3.start();

        try{th1.join(); th2.join(); th3.join();}catch(Exception e){e.printStackTrace();}
        System.out.println("El programa ha finalizado con exito");
    }
    
}
