import java.util.Arrays;

import mpi.MPI;

public class scatterGather {
    //javac -cp C:\mpj-v0_44\lib\mpj.jar .\Fichero.java
    //mpjrun.bat -np 10 NombreFicheroClase
    //ESTA DISEÑADO PARA 10
    
    public static void main(String[] args){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int recvBufferSize = 1;
        int sendBufferSize = recvBufferSize * size;
        int[] sendBuffer = new int[sendBufferSize];
        int[] recvBuffer = new int[recvBufferSize];

        if(rank == 0){
            for(int i = 0 ; i < sendBufferSize ; i++){
                sendBuffer[i] = i+1;
            }
            System.out.println("El vector antes de que cada uno multiplique por su rank: " + Arrays.toString(sendBuffer));
        }

        MPI.COMM_WORLD.Scatter(sendBuffer, 0, recvBufferSize, MPI.INT, recvBuffer, 0, recvBufferSize, MPI.INT, emisor);
        
        recvBuffer[0] *= rank;

        MPI.COMM_WORLD.Gather(recvBuffer, 0, recvBufferSize, MPI.INT, sendBuffer, 0, recvBufferSize, MPI.INT, emisor);

        if(rank == 0){
            System.out.println("El vector despues de que cada uno multiplique por su rank: " + Arrays.toString(sendBuffer));
        }
        MPI.Finalize();
    }
        
    
}
