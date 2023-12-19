import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class PiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo{
    private double aproximacion;
    private long numAciertos;
    private long totalPuntos;

    public PiMonteCarlo() throws RemoteException{
        aproximacion = 0.0;
        numAciertos = 0;
        totalPuntos = 0;
    }

    public void reset() throws RemoteException{
        aproximacion = 0.0;
        numAciertos = 0;
        totalPuntos = 0;
    }

    public void masPuntos(int nPuntos)  throws RemoteException{
        Random gen = new Random();
        totalPuntos += nPuntos;
        for(int i = 0 ; i < nPuntos ; i++){
            double x = gen.nextDouble(1.0);
            double y = gen.nextDouble(1.0);
            if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= 1){
                numAciertos++;
            }
        }
        aproximacion = (double) 4*numAciertos/totalPuntos;
    }

    public double aproxActual() throws RemoteException{
        return aproximacion;
    }

    public static void main(String[] args) throws Exception{
        PiMonteCarlo server = new PiMonteCarlo();

        Naming.bind("PiMonteCarlo", server);
        System.out.println("Servidores Remoto Preparado");
    }
}
