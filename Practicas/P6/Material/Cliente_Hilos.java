/*Ejemplo de cliente de sockets
*@Antonio Tomeu
*@version 1.0
*/


import java.net.*;
import java.io.*;

public class Cliente_Hilos
{
    public static void main (String[] args)
    {
        int i = (int)(Math.random()*10);
        int puerto = 2001;
        try{
            System.out.println("Realizando conexion...");
            Socket cable = new Socket("localhost", 2001);
            System.out.println("Realizada conexion a "+cable);
            PrintWriter salida= new PrintWriter(
                                    new BufferedWriter(
                                        new OutputStreamWriter(
            cable.getOutputStream())));
            salida.println(i);
            salida.flush();
            System.out.println("Cerrando conexion...");
            cable.close();

            }//try
                catch (Exception e)
        {System.out.println("Error en sockets...");}
    }//main
}//Cliente_Hilos

