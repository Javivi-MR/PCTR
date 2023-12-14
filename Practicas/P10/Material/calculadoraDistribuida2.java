//calculadora distribuida algo mejor...

import mpi.*;
public class calculadoraDistribuida2 {

public static void main(String args[]) throws Exception {
 MPI.Init(args);
 int rank = MPI.COMM_WORLD.Rank();
 int size = MPI.COMM_WORLD.Size();
 int emisor = 0;
 int tag = 100; int unitSize = 2;
 
 if(rank==emisor){ 
 	 int bufer[] = new int[2];
 	 bufer[0] = 4;
	 bufer[1] = 3;
         for(int i=1; i<size; i++){
 	   MPI.COMM_WORLD.Send(bufer, 0, unitSize, MPI.INT, i, tag+i);
 	 }
 } else if(rank==1){ 
 	 int res;
         int revbufer[] = new int[2];
 	 MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag+rank);
         res = revbufer[0]+revbufer[1];
 	 System.out.println("Suma: "+res);
   }else if(rank==2){ 
 	 int res;
         int revbufer[] = new int[2];
 	 MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag+rank);
         res = revbufer[0]-revbufer[1];
 	 System.out.println("Resta: "+res);
       }else if(rank==3){ 
 	 int res;
         int revbufer[] = new int[2];
 	 MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag+rank);
         res = revbufer[0]*revbufer[1];
 	 System.out.println("Producto: "+res);
       }else if(rank==4){ 
 	 float res;
         int revbufer[] = new int[2];
 	 MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag+rank);
         if(revbufer[1]!=0){res = revbufer[0]/(float)revbufer[1]; System.out.println("Cociente: "+res);}
         else System.out.println("No se puede dividir por cero...");
       }
 MPI.Finalize();
 
 }
}