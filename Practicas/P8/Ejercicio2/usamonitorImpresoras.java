public class usamonitorImpresoras implements Runnable{
    private monitorImpresoras m;
    private int id;
    
    public usamonitorImpresoras(monitorImpresoras m,int id){
        this.m = m;
        this.id = id;
    }

    public void run(){
        while(true){
            try {
                int idImpresora = m.pedirImpresora();
                System.out.println("El hilo con id " + id + " esta imprimiendo en la impresora " + idImpresora);
                //usarImpresora(idImpresora);
                m.liberarImpresora(idImpresora);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        monitorImpresoras mon = new monitorImpresoras();
        Thread[] vHilos = new Thread[20];

        for(int i = 0; i < 20 ; i++){
            vHilos[i] = new Thread(new usamonitorImpresoras(mon,i));
        }

        for(int i = 0; i < 20 ; i++){
            vHilos[i].start();
        }

        for(int i = 0 ; i < 20 ; i++){
            vHilos[i].join();
        }
        System.out.println("Todos los hilos imprimieron satisfactoriamente");
    }

}
