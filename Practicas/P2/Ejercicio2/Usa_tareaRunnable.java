public class Usa_tareaRunnable {
    public static void main(String[] args) throws InterruptedException {
        ClaseCompartida C = new ClaseCompartida();
        tareaRunnable T = new tareaRunnable(10000, 0, C);
        Thread h1 = new Thread(T);
        Thread h2 = new Thread(T);
        h1.start(); h2.start();
        h1.join(); h2.join();
        System.out.println(C.getN());
    }
}
