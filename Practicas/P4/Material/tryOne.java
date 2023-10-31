public class tryOne extends Thread{
	private int tipoHilo;
	private static volatile int Turno = 1;
	private static volatile int nVueltas = 10000;
	private static volatile int n = 0;

    public tryOne(int tipoHilo)
    {this.tipoHilo=tipoHilo;}

    public void run()    {
      switch(tipoHilo){
        case 1:{for(int i=0; i<nVueltas; i++){
        	      while(Turno!=1); //pre protocolo
        	      n++;
        	      Turno = 2; //post protocolo
        	    }
                break;}
        case 2: {for(int i=0; i<nVueltas;i++){
        	      while(Turno!=2);
        	      n--;
        	      Turno = 1;
                  }
        	}break;
      }
    }

    public static void main(String[] args) throws InterruptedException{
      tryOne h1 = new tryOne(1);
      tryOne h2 = new tryOne(2);
      h1.start(); h2.start();
      h1.join(); h2.join();
      System.out.println(n);
    }
}
