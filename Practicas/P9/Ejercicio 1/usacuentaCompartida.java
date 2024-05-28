public class usacuentaCompartida implements Runnable{
    private cuentaCompartida c;
    private int id;
    private int ingresar;
    private int retirar;

    public usacuentaCompartida(cuentaCompartida c, int id, int ingresar, int retirar){
        this.id = id;
        this.ingresar = ingresar;
        this.retirar = retirar;
        this.c = c;
    }

    public void run(){
        while (true) {
            try{
                c.ingreso(ingresar);
                System.out.println("El titular " + id + "ha ingresado " + ingresar + " euros");
                System.out.println("Saldo actual: " + c.saldo);
                c.reintegro(retirar);
                System.out.println("El titular " + id + "ha retirado " + retirar + " euros");
                System.out.println("Saldo actual: " + c.saldo);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        
        cuentaCompartida c = new cuentaCompartida(300);

        Thread h1 = new Thread(new usacuentaCompartida(c, 1, 200, 400));
        Thread h2 = new Thread(new usacuentaCompartida(c, 2, 100, 400));

        h1.start();
        h2.start();

        try{
            h1.join();
            h2.join();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Saldo final: " + c.saldo);
    }
}
