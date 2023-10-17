import java.util.Vector;
import java.util.Random;

public class escalaVector{
    public static void main(String[] args){
        Vector<Double> vector = new Vector<Double>(1000000);
        Random rand = new Random();
        double escalar = 2.5;

        for(int i = 0; i < 1000000; i++){
            vector.add(rand.nextDouble());
        }

        long startTime = System.nanoTime();

        for(int i = 0; i < 1000000; i++){
            vector.set(i, vector.get(i) * escalar);
        }

        long endTime = System.nanoTime();

        System.out.println("Tiempo de ejecucion: " + (endTime - startTime)/1000000 + " ms");
    }
}