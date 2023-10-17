public class concursoLambda{
    public static int n = 0;

    public static void main(String[] args) throws InterruptedException{
        Runnable r1 = () -> {for(int i = 0 ; i < 100000 ; i++) n++;};
        Runnable r2 = () -> {for(int i = 0 ; i < 100000 ; i++) n--;};

        Thread th1 = new Thread(r1);
        Thread th2 = new Thread(r2);

        th1.start(); th2.start();
        th1.join(); th2.join();

        System.out.println("Valor de n al final: " + n);
    }
}
