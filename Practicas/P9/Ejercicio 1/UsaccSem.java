public class UsaccSem implements Runnable{
    private int id;
    private ccSem c;

    public UsaccSem(int id, ccSem c){
        this.id = id;
        this.c = c;
    }

    public void run(){
        try{
            if(id == 1){
                System.out.println("El hilo con id 1 inserta 1000");
                c.ingreso(1000);
            }
            if(id == 2)
            {
                System.out.println("El hilo con id 2 intenta reintegrar 200");
                c.reintegro(200);
            }
            if(id == 3)
            {
                System.out.println("El hilo con id 3 inserta 500");
                c.ingreso(500);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ccSem c = new ccSem(300);

        Thread h1 = new Thread(new UsaccSem(1,c));
        Thread h2 = new Thread(new UsaccSem(2,c));
        Thread h3 = new Thread(new UsaccSem(3,c));
        Thread h4 = new Thread(new UsaccSem(3,c));

        h1.start(); h2.start(); h3.start(); h4.start();

        try{
            h1.join(); h2.join(); h3.join(); h4.join();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Si lees esto, no existe ningun problema con la configuracion de los hilos. Valor final de la cuenta: " + c.saldo + "$");
    }

}
