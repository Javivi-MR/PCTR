public class redCajeros {
    public static void main(String[] args) throws InterruptedException{
        cuentaCorriente C = new cuentaCorriente(1024,1000);
        Thread th1 = new Thread(new cajero(C, 0));
        Thread th2 = new Thread(new cajero(C, 1));

        th1.start(); th2.start();
        th1.join(); th2.join();

        System.out.println("Valor final de la cuenta: " + C.verSaldo());
    }
}
