 //se importan los paquetes necesarios
 import java.rmi.*;
 import java.rmi.server.*;
 import java.rmi.registry.*;
 import java.net.*;

 //start rmiregistry Comenzar la ejecucion en segundo plano
 
 public class EjemploRMI1
   extends UnicastRemoteObject //el servidor debe siempre extender esta clase
     implements IEjemploRMI1   //el servidor debe simpre implementar la interfaz remota definida con caracter previo
 {
   public int Suma(int x, int y) throws RemoteException {return x+y;}  
   public int Resta(int x, int y) throws RemoteException {return x-y;}
   public int Producto(int x, int y) throws RemoteException {return x*y;}
   public float Cociente(int x, int y) throws RemoteException{ 
     if(y == 0) return 1f;
     else return x/y; 
   }
 	
  //es necesario como minimo el constructor (nulo) para lanzar RemoteException
  public EjemploRMI1() throws RemoteException{}  
 	
  //el metodo main siguiente realiza el registro del servicio
 	
  public static void main(String[] args) throws Exception{
    //Se crea el objeto remoto. Podriamos crear mas si interesa.
    IEjemploRMI1 ORemoto = new EjemploRMI1(); //meramente local
    IEjemploRMI1 ORemoto2 = new EjemploRMI1(); //meramente local
    IEjemploRMI1 ORemoto3 = new EjemploRMI1(); //meramente local
    IEjemploRMI1 ORemoto4 = new EjemploRMI1(); //meramente local
    //Se registra el servicio
    Naming.bind("Servidor", ORemoto); // Ya no. Ademas no se devuelve la referencia al objeto remoto, tambien una ip y un puerto
    Naming.bind("Servidor1", ORemoto2); // Ya no. Ademas no se devuelve la referencia al objeto remoto, tambien una ip y un puerto
    Naming.bind("Servidor2", ORemoto3); // Ya no. Ademas no se devuelve la referencia al objeto remoto, tambien una ip y un puerto
    Naming.bind("Servidor3", ORemoto4); // Ya no. Ademas no se devuelve la referencia al objeto remoto, tambien una ip y un puerto
    System.out.println("Servidores Remoto Preparado");
  }	  
}	                              
                               