import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class integCallable implements Callable<Integer> {
    public static int n = 1000000;

    public Integer call() {
        Integer totalPoints = 0;
        for(int i = 0 ; i < n ; i++){
            double x = ThreadLocalRandom.current().nextDouble(1);
            double y = ThreadLocalRandom.current().nextDouble(1);
            if(y <= Math.cos(x)){
                totalPoints++;
            }
        }
        System.out.println("Total de ptos obtenidos = " + totalPoints);
        return totalPoints;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService executor = Executors.newFixedThreadPool(10);
        integCallable[] tareas = new integCallable[10];
        ArrayList<Future<Integer>> soluciones = new ArrayList<>();
        int totalSoluciones = 0;
        for (int i = 0; i < 10; i++) {
            tareas[i] = new integCallable();
            soluciones.add(executor.submit(tareas[i]));
        }
        executor.shutdown();

        executor.awaitTermination(10, TimeUnit.SECONDS);

        for (Future<Integer> solucion : soluciones) {
            totalSoluciones += solucion.get();
        }

        double valorIntegral = (double) totalSoluciones / (n*10);

        System.out.println("El valor aproximado de la integras es de: " + valorIntegral); 
    }

}

