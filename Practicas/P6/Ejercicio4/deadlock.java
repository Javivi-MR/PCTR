public class deadlock extends Thread{   
    private Object lock1; //CERROJO UTILIZANDO OBJETO
    private Object lock2;
    private Object lock3;

    public deadlock(Object l1, Object l2, Object l3){
        this.lock1 = l1;
        this.lock2 = l2;
        this.lock3 = l3;
    }
    
    public void run(){
        while(true){
            synchronized(lock1){
                synchronized(lock2){
                    synchronized(lock3){
                        System.out.println(this.getName());
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Object l1 = new Object();
        Object l2 = new Object();
        Object l3 = new Object();

        deadlock h1 = new deadlock(l1,l2,l3);
        deadlock h2 = new deadlock(l2,l1,l3);
        deadlock h3 = new deadlock(l3,l2,l1);


        h1.start(); h2.start(); h3.start();

        try{
            h1.join(); h2.join(); h3.join();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Este mensaje nunca aparecera :((");
    }
}
