import mpi.*;
public class ejMasUtil{

  public static void main(String args[]) throws Exception {
    MPI.Init(args);
    int me = MPI.COMM_WORLD.Rank();
    int size = MPI.COMM_WORLD.Size();
    if(me==0){
      int a,b; 
      a=10; b=5;
      int c=a+b;
      System.out.println("Soy el proceso <"+me+"> y a+b es igual a "+c);
     }
    else{System.out.println("Soy el proceso <"+me+"> y a mi no me gusta sumar");}
      
    MPI.Finalize();
  }
} 