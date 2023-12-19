import java.rmi.*;
import java.rmi.server.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
import java.rmi.registry.*;
import java.net.*;

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto{
    private int[] NumerosGanadores;

    public sBonoLoto() throws RemoteException{
        Random gen = new Random();
        NumerosGanadores = new int[6];
        for(int i = 0 ; i < 6 ; i++){
            NumerosGanadores[i] = gen.nextInt(49) + 1;
        }
        System.out.println("Numeros ganadores: " + Arrays.toString(NumerosGanadores));
    }

    public void resetServidor() throws RemoteException{
        Random gen = new Random();
        NumerosGanadores = new int[6];
        for(int i = 0 ; i < 6 ; i++){
            NumerosGanadores[i] = gen.nextInt(49) + 1;
        }
        System.out.println("Numeros ganadores: " + Arrays.toString(NumerosGanadores));
    }

    public boolean compApuesta(int[] apuesta) throws RemoteException{
        for(int i = 0 ; i < 6 ; i++){
            if(NumerosGanadores[i] != apuesta[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        sBonoLoto server = new sBonoLoto();

        Naming.bind("Bonoloto", server);
        System.out.println("Servidores Remoto Preparado");
    }
}
