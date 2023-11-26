public class monitorLE {
    private int n; // Tamaño del buffer
    private int first,last;
    private int[] Buffer;
    private int numLectores;
    private int numEscritores;

    public monitorLE(int n){
        this.n = n;
        first = 0;
        last = 0;
        Buffer = new int[n];
        numLectores = 0;
        numEscritores = 0;
    }

    synchronized public void escribir(int elemento){
        Buffer[last % n] = elemento;
        last++;

        notifyAll();
    }

    synchronized public int leer() throws InterruptedException{
        while(first >= last){
            System.out.println("Estoy esperando");
            wait();
        }
        first++;
        return Buffer[(first-1) % n];
    }
}
