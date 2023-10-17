
public class cajero implements Runnable{
    private cuentaCorriente C;
    private int id;

    public cajero(cuentaCorriente C, int id){
        this.C = C;
        this.id = id;
    }

    public void run(){
        switch(id){
            case 0: C.depositar(100); System.out.println("Sumado"); break;
            case 1: C.reintegro(100); System.out.println("Restado"); break;
        }
    }
}
