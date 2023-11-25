public class usoHeterogenea implements Runnable{
    private heterogenea h;

    public usoHeterogenea(heterogenea h){
        this.h = h;
    }

    public void run(){
        h.incN();
        h.incM();
    }

    public static void main(String[] args){
        heterogenea h = new heterogenea();

        Thread h1 = new Thread(new usoHeterogenea(h));
        Thread h2 = new Thread(new usoHeterogenea(h));

        h1.start(); h2.start();

        try{
            h1.join(); h2.join();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Valor final de n: " + h.getN() + " valor final de m: " + h.getM());
    }
}
