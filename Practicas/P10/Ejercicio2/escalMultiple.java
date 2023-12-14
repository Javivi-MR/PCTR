import java.util.Arrays;

import mpi.*;

public class escalMultiple {
    //javac -cp C:\mpj-v0_44\lib\mpj.jar .\Fichero.java
    //mpjrun.bat -np 10 NombreFicheroClase
    
    public static void main(String[] args){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int unitSize = 10;
        int[] buffer = new int[10];

        if(rank == 0){
            for(int i = 0 ; i < 10 ; i++){
                buffer[i] = i;
            }    
        }
        
        MPI.COMM_WORLD.Bcast(buffer, 0, unitSize, MPI.INT, emisor);
        
        if(rank > 0)
        {
            for(int i = 0 ; i < 10 ; i++){
                buffer[i] *= rank;
            }
            System.out.println("Array escalado por rank<"+rank+"> " + Arrays.toString(buffer));
        }
        MPI.Finalize();
    }
}
