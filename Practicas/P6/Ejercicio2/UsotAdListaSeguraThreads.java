public class UsotAdListaSeguraThreads extends Thread{
    static public tADListaSegura lista = new tADListaSegura(10);
    private int id;

    public UsotAdListaSeguraThreads(int id) throws Exception{
        this.id = id;
        for(int i = 0; i < 10 ; i++){
            lista.set(i,0);
        }
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

    public static void main(String[] args) throws Exception{
        UsotAdListaSeguraThreads h1 = new UsotAdListaSeguraThreads(1);
        UsotAdListaSeguraThreads h2 = new UsotAdListaSeguraThreads(2);
        
        h2.start(); h1.start();
        try{
            h2.join(); h1.join(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
