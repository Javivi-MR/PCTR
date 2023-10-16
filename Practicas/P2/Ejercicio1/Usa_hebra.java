public class Usa_hebra {
    public static void main(String[] args) throws InterruptedException{
        hebra h1 = new hebra(10000,0);
        hebra h2 = new hebra(10000,1);
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        System.out.println(hebra.n);
    }
}
