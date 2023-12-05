public class usaProdConAn extends Thread{
    private prodConAN m;
    private int id;

    public usaProdConAn(prodConAN m, int id){
        this.m = m;
        this.id = id;
    }

    public void run(){
        if(id == 1){
            m.produce(10);
        }
        if(id == 2){
            int value = m.consume();
        }
        if(id == 3){
            m.produce(20);
        }
    }

    public static void main(String[] args){
        prodConAN m = new prodConAN(1);

        usaProdConAn h1 = new usaProdConAn(m, 1);
        usaProdConAn h3 = new usaProdConAn(m, 3);
        usaProdConAn h2 = new usaProdConAn(m, 2);
        usaProdConAn h4 = new usaProdConAn(m, 2);

        h1.start(); h2.start(); h3.start(); h4.start();
        try{
            h1.join(); h2.join(); h3.join(); h4.join();
        }catch(Exception e){e.printStackTrace();}

        System.out.println("El programa ha finalizado exitosamente");
    }
}
