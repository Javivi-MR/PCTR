public class tryVolatile
  extends Thread
{
	private int tipoHilo;
	private static int Turno = 1;
	private static int nVueltas = 10000;
	private static int n = 0;

    public tryVolatile(int tipoHilo)
    {this.tipoHilo=tipoHilo;}

    public void run()
    {
      switch(tipoHilo){
        case 1:{for(int i=0; i<nVueltas; i++)n++;break;}
        case 2: {for(int i=0; i<nVueltas;i++)n--;break;}
      }
    }

    public static void main(String[] args)
      throws InterruptedException
    {
      tryVolatile h1 = new tryVolatile(1);
      tryVolatile h2 = new tryVolatile(2);
      h1.start(); h2.start();
      h1.join(); h2.join();
      System.out.println(n);
    }
}
