import java.rmi.*;


public interface iBonoLoto
  extends Remote //necesario en la interfaz para los throw
{
  public void resetServidor() throws RemoteException;
  public boolean compApuesta(int[] apuesta)  throws RemoteException;

}