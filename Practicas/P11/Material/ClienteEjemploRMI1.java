import java.rmi.*;
import java.rmi.registry.*;

public class ClienteEjemploRMI1{

  public static void main(String[] args) throws Exception{
    int a =  10;
    int b = -10;
    //SIEMPRE debe convertirse el retorno del metodo Naming.lookup a la interfaz remota
		
  IEjemploRMI1 RefObRemoto =  (IEjemploRMI1)Naming.lookup("//localhost/Servidor");
  IEjemploRMI1 RefObRemoto2 =  (IEjemploRMI1)Naming.lookup("//localhost/Servidor1");
  IEjemploRMI1 RefObRemoto3 =  (IEjemploRMI1)Naming.lookup("//localhost/Servidor2");
  IEjemploRMI1 RefObRemoto4 =  (IEjemploRMI1)Naming.lookup("//localhost/Servidor3");

  //OJO el framework NO te va a proteger de EM frente a distintos clientes usando distintas hebras. Asi que es necesario protegerlas.
		  	
  System.out.println(RefObRemoto.Suma(a,b));	
  System.out.println(RefObRemoto2.Resta(a,b));	
  System.out.println(RefObRemoto3.Producto(a,b));	
  System.out.println(RefObRemoto4.Cociente(a,b));	
		
  }	
}	