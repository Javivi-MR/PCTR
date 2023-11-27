public class monitorImpresoras{
    private boolean[] impresoras;
    private int impresorasLibres;

    public monitorImpresoras(){
        this.impresoras = new boolean[3];
        for(int i = 0 ; i < 3 ; i++){
            impresoras[i] = true;
        }
        this.impresorasLibres = 3;
    }

    synchronized public int pedirImpresora() throws InterruptedException{
        while(impresorasLibres == 0){
            wait();
        }
        int index = -1;
        for(int i = 0 ; i < 3; i++){
            if(impresoras[i]){
                index = i;
                break;
            }
        }
        impresoras[index] = false;
        impresorasLibres--;
        return index;
    }

    synchronized public void liberarImpresora(int n){
        impresoras[n] = true;
        impresorasLibres++;
        notifyAll(); 
    }
}