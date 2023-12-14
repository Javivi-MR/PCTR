//transfiere un array de enteros del emisor al recpetor
//COMPILACION:javac -cp .;%MPJ_HOME%/lib/mpj.jar ArrayPuntoAPunto.java
//EJECUCION: mpjrun.bat -np 2 ArrayPuntoAPunto 
//NOTA: CONFIGURAR de manera previa MPJ-Express adecuadamente

import mpi.*;
import java.util.Arrays;
public class ArrayPuntoAPunto {

public static void main(String args[]) throws Exception {
 MPI.Init(args);
 int rank = MPI.COMM_WORLD.Rank();
 int size = MPI.COMM_WORLD.Size();
 int emisor = 0; int receptor = 1;
 int tag = 100; int unitSize = 10; //tag dependera de la naturaleza que le de el programador (en este caso no tiene sentido, pero para una comunciacion tcp puede ser util para ordenar los mensajes)
 
 if(rank==emisor){ //codigo del emisor
 	 int bufer[] = new int[10];
 	 for(int i=0; i<bufer.length; i++)bufer[i] = i;
 	 MPI.COMM_WORLD.Send(bufer, 0, unitSize, MPI.INT, receptor, tag); //LOS SEND QUE USAMOS SIEMPRE SERAN BLOQEUANTE Primero recibe una posicion de memoria (en este caso es el array), el siguiente es el offset (posicion inicial del array), ek siguiente es el numero de elementos del buffer, el siguiente el tipo de datos a enviar, el siguiente es el identificador del receptor, el siguiente es el tag.
		// para pasar un objeto abra que pasar el objeto mediante SERIALIZACION, (Transformar un objeto en un array de bytes) y luego enviarlo mediante el metodo send
	} else{ //codigo del receptor
 	 int revbufer[] = new int[10];
 	 MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag); //LOS RECV QUE USAMOS SIEMPRE SERAN BLOQEUANTE Primero recibe una posicion de memoria donde se va a recibir los datos, el siguiente es el offset (posicion inicial del array), ek siguiente es el numero de elementos del buffer, el siguiente el tipo de datos a enviar, el siguiente es el identificador del emisor, el siguiente es el tag.
 	 System.out.println("Recibido: "+Arrays.toString(revbufer));
   }

 MPI.Finalize();
 
 }
}