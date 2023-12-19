import java.rmi.*;
import java.rmi.registry.*;

public class cPiMonteCarlo {
    public cPiMonteCarlo(){}

    public static void main(String[] args) throws Exception{
        cPiMonteCarlo c1 = new cPiMonteCarlo();

        iPiMonteCarlo refObjRemoto = (iPiMonteCarlo)Naming.lookup("//localhost/PiMonteCarlo"); 

        refObjRemoto.masPuntos(1000000); // 1 millon
        System.out.println("Prueba con 1 millon de ptos: " + refObjRemoto.aproxActual());

        refObjRemoto.reset();
        refObjRemoto.masPuntos(5000000); //5 millones
        System.out.println("Prueba con 5 millones de ptos: " + refObjRemoto.aproxActual());

        refObjRemoto.masPuntos(5000000);
        System.out.println("Anterior muestra con 5 millones de ptos más: " + refObjRemoto.aproxActual());
    }
}
