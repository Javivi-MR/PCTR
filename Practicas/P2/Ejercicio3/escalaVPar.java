import java.util.Vector;
import java.util.Random;

public class escalaVPar extends Thread{
    private int start,end;
    public static double escalar = 2.5;
    public static Vector<Double> vector = new Vector<Double>(1000000);

    public escalaVPar(int start,int end){
        this.start = start;
        this.end = end;
    }

    public void run(){
        for(int i = start ; i < end ; i++){
            vector.set(i,vector.get(i)*escalar);
        }
    }
    public static void main(String[] args){
        
        Random rand = new Random();

        for(int i = 0 ; i < 1000000 ; i++){
            vector.add(rand.nextDouble());
        }

        escalaVPar th1 = new escalaVPar(0,250000);
        escalaVPar th2 = new escalaVPar(250000,500000);
        escalaVPar th3 = new escalaVPar(500000, 750000);
        escalaVPar th4 = new escalaVPar(750000, 1000000);

        long startTime = System.nanoTime();

        th1.start(); th2.start(); th3.start(); th4.start();

        try{
            th1.join(); th2.join(); th3.join(); th4.join();
        }catch(Exception e){
            System.out.println(e);
        }

        long endTime = System.nanoTime();

        System.out.println("Tiempo de ejecucion: " + (endTime - startTime)/1000000 + " ms");
        
    }
}
