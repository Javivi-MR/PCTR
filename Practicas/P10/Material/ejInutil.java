import mpi.*;
public class ejInutil{

  //MPI.Init(args); //inicializa el entorno MPI para MULTICORE, existe una version para CLUSTER que ademas de inicializar el entorno MPI, inicializa el entorno de comunicacion entre los nodos del cluster
  //comunicador: procesos que forma parte de la comunicacion y tiene un identificador que lo identifica de forma unica
  //COMO MINIMO habra siempre 1 comunicador... pero puede haber mas
  //el comunicador nos permitiria redigir el envio de mensajes a un grupo de procesos
  public static void main(String args[]) throws Exception {
    MPI.Init(args);
    int me = MPI.COMM_WORLD.Rank();
    int size = MPI.COMM_WORLD.Size();
    System.out.println("Soy el proceso <"+me+">");
    MPI.Finalize();
  }
} 