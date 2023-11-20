import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;


public class ServidorHiloconPool implements Runnable{
    private Socket conexion;
    private int id;

    public ServidorHiloconPool(Socket conexion, int id){
        this.conexion = conexion;
        this.id = id;
    }

    public void run()
    {
    try{
        BufferedReader entrada = new BufferedReader(
                                    new InputStreamReader(
                                        conexion.getInputStream()));
        String datos = entrada.readLine();
        int j;
        int i = Integer.valueOf(datos).intValue();
        for(j=1; j<=20; j++){
            System.out.println("El hilo "+this.id+" escribiendo el dato "+i);
            Thread.sleep(3000);
        }
        conexion.close();
        
        System.out.println("El hilo "+this.id+"cierra su conexion...");
    } catch(Exception e) {System.out.println("Error...");}
    }//run

    public static void main(String[] args){
    int i, j = 0;
    int puerto = 2001;
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);
            int nNucleos = Runtime.getRuntime().availableProcessors();
            ExecutorService ept = Executors.newFixedThreadPool(nNucleos);

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                j++;
                System.out.println("Recibida solicitud de conexion...");
                ept.submit(new ServidorHiloconPool(cable,j));
        }//while
      } catch (Exception e)
        {System.out.println("Error en sockets...");}
    }
}