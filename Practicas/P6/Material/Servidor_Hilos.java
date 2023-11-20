/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
*/


import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class Servidor_Hilos
  extends Thread
{
    Socket enchufe;
    public Servidor_Hilos(Socket s)
    {enchufe = s; this.start();}

    public void run()
    {
    try{
        BufferedReader entrada = new BufferedReader(
                                    new InputStreamReader(
                                        enchufe.getInputStream()));
        String datos = entrada.readLine();
        int j;
        int i = Integer.valueOf(datos).intValue();
        for(j=1; j<=20; j++){
        System.out.println("El hilo "+this.getName()+" escribiendo el dato "+i);
        sleep(1000);}
        enchufe.close();
        
        System.out.println("El hilo "+this.getName()+"cierra su conexion...");
    } catch(Exception e) {System.out.println("Error...");}
    }//run

public static void main (String[] args)
{
    int i;
    int puerto = 2001;
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                

        }//while
      } catch (Exception e)
        {System.out.println("Error en sockets...");}
}//main

}//Servidor_Hilos

