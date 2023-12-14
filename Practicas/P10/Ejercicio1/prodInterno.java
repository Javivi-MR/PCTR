import java.util.Random;

import mpi.*;

public class prodInterno {
    //javac -cp C:\mpj-v0_44\lib\mpj.jar .\Fichero.java
    //mpjrun.bat -np 10 NombreFicheroClase

    public static void main(String[] args){
        MPI.Init(args);
            int rank = MPI.COMM_WORLD.Rank();
            int size = MPI.COMM_WORLD.Size();
            int emisor = 0;
            int receptor = 1;
            int unitSize = 4;
            int tag = 100;
            int[] buffer1 = new int[4];
            int[] buffer2 = new int[4];
            int[] bufferResultado = new int[4];

            if(rank == 0){
                Random r = new Random();
                for(int i = 0 ; i < 4 ; i++){
                    buffer1[i] = r.nextInt();
                    buffer2[i] = r.nextInt();
                }
                MPI.COMM_WORLD.Send(buffer1,0,unitSize,MPI.INT,receptor,tag);
                MPI.COMM_WORLD.Send(buffer2,0, unitSize, MPI.INT, receptor,tag);
                MPI.COMM_WORLD.Recv(bufferResultado, 0, unitSize, MPI.INT, receptor, tag);

                for(int i = 0 ; i < 4 ; i++){
                    System.out.print(bufferResultado[i] + " ");
                }
            }
            else{
                MPI.COMM_WORLD.Recv(buffer1, 0, unitSize, MPI.INT, emisor, tag);
                MPI.COMM_WORLD.Recv(buffer2, 0, unitSize, MPI.INT, emisor, tag);

                for(int i = 0 ; i < unitSize ; i++){
                    bufferResultado[i] = (buffer1[i] * buffer2[i]);
                }

                MPI.COMM_WORLD.Send(bufferResultado,0, unitSize, MPI.INT, emisor,tag);
            }
            


        MPI.Finalize();
    }

}
