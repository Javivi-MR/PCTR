public class runnableImplement implements Runnable{
    int n;
    public runnableImplement (int n){this.n = n;}
    public void run(){
        for(int i=0; i<10; i++)
          System.out.println(n+" saluda...");
    }
	public static void main(String[] args) throws Exception{
	    Thread h1 = new Thread(new runnableImplement(0));
	    Thread h2 = new Thread(new runnableImplement(1));
	    h1.start(); h2.start();
	    h1.join(); h2.join();
	    System.out.println("Finalizando...");
	}
}