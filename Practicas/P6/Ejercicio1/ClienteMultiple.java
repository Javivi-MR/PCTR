import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteMultiple {
        public static void main (String[] args)
    {
        int[] i = new int[10];
        for(int j = 0; j < 10; j++){
            i[j] = (int)(Math.random()*10);
        }
        int puerto = 2001;
        Socket[] cable = new Socket[10];
        try{
            for(int j = 0; j < 10; j++){
                try{
                    System.out.println("Realizando conexion...");
                    cable[j] = new Socket("localhost", puerto);
                    System.out.println("Realizada conexion a "+cable);
                    PrintWriter salida= new PrintWriter(
                                            new BufferedWriter(
                                                new OutputStreamWriter(
                    cable[j].getOutputStream())));
                    salida.println(i[j]);
                    salida.flush();
                    System.out.println("Cerrando conexion...");
                    cable[j].close();
    
                }
                catch (Exception e)
                {System.out.println("Error en sockets...");}
            }
        }catch(Exception e){
            System.out.println("Error en sockets...");
        }
    }//main
}
