public class tADListaSegura extends Thread{
    private int[] lista;
    private int numElem;

    public tADListaSegura(int numElem){
        this.numElem = numElem;
        this.lista = new int[numElem];
    }

    synchronized public int get(int index) throws Exception{
        if(index >= 0 && index <= (numElem-1)){
            return lista[index];
        }
        else{
            throw new Exception("Indice fuera de rango");
        }
    }

    synchronized public void set(int index, int value) throws Exception{
        if(index >= 0 && index <= (numElem-1)){
            lista[index] = value;
        }
        else{
            throw new Exception("Indice fuera de rango");
        }
    }

    synchronized public void printVector(){
        for(int i = 0 ; i < numElem ; i++){
            System.out.print(lista[i] + " ");
        }
        System.out.println(" ");
    }

}