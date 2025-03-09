import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class usamonitorImpresorasAN implements Runnable{
    private monitorImpresorasAN m = new monitorImpresorasAN();
    private int id;

    public usamonitorImpresorasAN(int id){
        this.id = id;
    }

    public void run(){
            try{
                id = m.pedirImpresora();
                System.out.println("Coge la impresora...");
                m.liberarImpresora(id);
                System.out.println("Libera la impresora...");
            } catch(Exception e){
                e.printStackTrace();
            }
    }


    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++){
            executor.execute(new usamonitorImpresorasAN(i));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Fin de ejecucion");
    }
}
