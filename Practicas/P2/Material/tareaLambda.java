public class tareaLambda{
  public static void main (String[] args) throws Exception{
    Runnable runnable = () -> {
      String nombre = Thread.currentThread().getName();
      System.out.println("Hola, soy " + nombre);
    };

  Thread thread = new Thread(runnable);
  thread.start();thread.join();
  System.out.println("Finalizando...");
  }
}