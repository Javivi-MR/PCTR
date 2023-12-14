//transfiere un array de enteros del emisor al receptor, se hace un producto escalar, y se devuelve
//COMPILACION:javac -cp .;%MPJ_HOME%/lib/mpj.jar pEscalar.java
//EJECUCION: mpjrun.bat -np 2 pEscalar

import mpi.*;
import java.util.Arrays;
public class pEscalar {

public static void main(String args[]) throws Exception {
 MPI.Init(args);
 int rank = MPI.COMM_WORLD.Rank();
 int size = MPI.COMM_WORLD.Size();
 int emisor = 0; int receptor = 1;
 int tag = 100; int unitSize = 10;
 
 if(rank==emisor){ //codigo del emsior
 	 int bufer[] = new int[10];
 	 for(int i=0; i<bufer.length; i++)bufer[i] = i;
 	 MPI.COMM_WORLD.Send(bufer, 0, unitSize, MPI.INT, receptor, tag);
         MPI.COMM_WORLD.Recv(bufer, 0, unitSize, MPI.INT, receptor, tag);
         System.out.println("Emisor ha recibido: "+Arrays.toString(bufer));
 } else{ //codigo del receptor
 	 int revbufer[] = new int[10];
 	 MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag);
 	 System.out.println("Receptor ha recibido: "+Arrays.toString(revbufer));
         int k =10;
         for(int i=0; i<revbufer.length; i++)revbufer[i] = revbufer[i]*k;
         MPI.COMM_WORLD.Send(revbufer, 0, unitSize, MPI.INT, emisor, tag);
      }
 MPI.Finalize();
 }
}