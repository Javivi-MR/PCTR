import mpi.*;

// javac -cp C:\mpj-v0_44\lib\mpj.jar .\testMPJ.java
// mpjrun.bat -np 2 testMPJ

public class testMPJ{
    public static void main(String[] args) throws Exception{
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        System.out.println("Hello world from rank " + rank + " of " + size);
        MPI.Finalize();
    }
}