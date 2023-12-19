/**
 * @(#)iBonoLoto.java
 * @author A.T.
 * @version 1.00 2013/1/2
 */

import java.rmi.*;
public interface iBonoLoto
  extends Remote
{
  public void resetServidor() throws RemoteException;
  public boolean compApuesta(int[] apuesta)  throws RemoteException;

}