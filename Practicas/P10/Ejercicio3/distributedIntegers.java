import mpi.*;

public class distributedIntegers {
    //javac -cp C:\mpj-v0_44\lib\mpj.jar .\Fichero.java
    //mpjrun.bat -np 10 NombreFicheroClase

    public static boolean isPrime(int n){
        for(int i = 2 ; i < Math.sqrt(n) ; i++){
            if(n % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int unitSize = 1;
        int[] buffer = new int[1];
        int[] totalPrimos = new int[1];
        int numPrimos = 0;

        int rango = 10000000;
        
        if(rank == 0){
            buffer[0] = rango / 5;
        }
        MPI.COMM_WORLD.Bcast(buffer, 0, unitSize, MPI.INT, 0);

        if(rank > 0){
            for(int i = (rank-1)*buffer[0] ; i < rank*buffer[0] ; i++){
                if(isPrime(i) && i != 0 ){
                    numPrimos++;
                }
            }
            buffer[0] = numPrimos;
        }
        else{ //rank == 0
            buffer[0] = 0; //no quiero tenerlo en cuenta para la suma
        }
        MPI.COMM_WORLD.Reduce(buffer,0,totalPrimos,0,1,MPI.INT,MPI.SUM,0);

        if(rank == 0){
            System.out.println("El numero de primos encontrados es de " + totalPrimos[0]);
        }
        MPI.Finalize();
    }

}
