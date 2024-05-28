public class cuentaCompartida {
    public int saldo;


    public cuentaCompartida(int saldo){
        this.saldo = saldo;
    }

    synchronized public void ingreso(int cuantia){
        saldo += cuantia;
        notifyAll();
    }

    synchronized public void reintegro(int cuantia) throws InterruptedException{
        while(saldo < cuantia) wait();
        saldo -= cuantia;
    }
}