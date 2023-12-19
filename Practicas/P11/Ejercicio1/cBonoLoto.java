import java.util.Arrays;
import java.util.Random;

import java.rmi.*;
import java.rmi.registry.*;

//start rmiregistry

public class cBonoLoto {
    private static int[] apuesta;
    
    public cBonoLoto(){
        apuesta = new int[6];
        Random g = new Random();
        for(int i = 0 ; i < 6 ; i++){
            apuesta[i] = g.nextInt(49) + 1;
        }
        System.out.println("Apuesto: " + Arrays.toString(apuesta));
    }

    public static void nuevaApuesta(){
        Random g = new Random();
        for(int i = 0 ; i < 6 ; i++){
            apuesta[i] = g.nextInt(49) + 1;
        }
        System.out.println("Apuesto: " + Arrays.toString(apuesta));
    }

    public static void main(String[] args) throws Exception{
        cBonoLoto c1 = new cBonoLoto();

        iBonoLoto refObjRemoto = (iBonoLoto)Naming.lookup("//localhost/Bonoloto"); 

        while(!refObjRemoto.compApuesta(apuesta)){
            nuevaApuesta();
        }

        System.out.println("GANE!!!!!");
    }
}
