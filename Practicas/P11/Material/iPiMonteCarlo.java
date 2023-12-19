/**
 * @(#)iPiMonteCarlo.java
 * @author A.T.
 * @version 2.00 15/12/2021
 * Metodos:
 * reset: pone a cero el servidor;
 * masPuntos: envia puntos al servidor para aproximar a PI;
 * aproxActual: para preguntar al servidor el valor de la aproximacion a PI;
 */


import java.rmi.*;
public interface iPiMonteCarlo
  extends Remote{
  public void reset() throws RemoteException;
  public void masPuntos(int nPuntos)  throws RemoteException;
  public double aproxActual() throws RemoteException;
}