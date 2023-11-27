public class cuentaCompartida {
    private int total;

    public cuentaCompartida() {
        total = 0;
    }

    synchronized public void ingreso(int cuantia){
        total += cuantia;
        notifyAll();
    }

    synchronized public void reintegro(int cuantia) throws InterruptedException{
        while(cuantia > total){
            wait();
        }
        total -= cuantia;
    }
}
