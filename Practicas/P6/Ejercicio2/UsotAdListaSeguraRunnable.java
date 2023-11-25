public class UsotAdListaSeguraRunnable implements Runnable{
    private tADListaSegura lista = new tADListaSegura(10);
    private int id;

    public UsotAdListaSeguraRunnable(int id, tADListaSegura lista){
        this.id = id;
        this.lista = lista;
    }

    public void run(){
        try {
            if(id == 1){
                lista.set(0,10);
                System.out.println("El hilo 1 ha dado el valor 10 a la posicion 0");
            }
            else
            {
                System.out.println("El hilo 2 ha obtenido el valor " + lista.get(0) + " de la primera posicion del vector");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        tADListaSegura L = new tADListaSegura(10);

        Thread h1 = new Thread(new UsotAdListaSeguraRunnable(1, L));
        Thread h2 = new Thread(new UsotAdListaSeguraRunnable(2, L));

        h1.start(); h2.start();
        try{
            h1.join(); h2.join();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
