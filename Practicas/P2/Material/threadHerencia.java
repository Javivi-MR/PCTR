public class threadHerencia extends Thread{
    public threadHerencia (){}
    public void run(){
        for(int i=0; i<10; i++)
          System.out.println(this.getName()+ "saluda...");
    }
	public static void main(String[] args) throws Exception{
	    threadHerencia h1 = new threadHerencia();
	    threadHerencia h2 = new threadHerencia();
	    h1.start(); h2.start();
	    h1.join(); h2.join();
		System.out.println("Finalizando...");
	}
}