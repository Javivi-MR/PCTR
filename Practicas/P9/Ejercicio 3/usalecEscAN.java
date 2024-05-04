public class usalecEscAN implements Runnable{
    private lecEscAN m;
    private int id;

    public usalecEscAN(lecEscAN m, int id){
        this.m = m;
        this.id = id;
    }

    public void run(){
        if(id == 1){
            m.escribir(1);
            System.out.println("El hilo 1 ha escrito un 1");
        }
        if(id == 2){
            m.escribir(1);
            System.out.println("El hilo 2 ha escrito un 2");
        }
        if(id == 3){
            try{
                int value = m.leer();
                System.out.println("El hilo 3 ha leido " + value);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        lecEscAN monitor = new lecEscAN(10);
        Thread h1 = new Thread(new usalecEscAN(monitor, 1));

        Thread h3 = new Thread(new usalecEscAN(monitor, 3));
        Thread h4 = new Thread(new usalecEscAN(monitor, 3));

        h1.start(); h3.start(); h4.start();

        Thread h2 = new Thread(new usalecEscAN(monitor, 2));
        h2.start();

        try{
            h1.join(); h4.join(); h3.join(); h2.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Fin del programa xd");
    }
}