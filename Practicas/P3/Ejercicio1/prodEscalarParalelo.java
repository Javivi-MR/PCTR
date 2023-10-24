public class prodEscalarParalelo extends Thread{
    private int id;
    private int inicio, fin;
    private static double[] sumasParciales;
    private static double[] a;
    private static double[] b;

    public prodEscalarParalelo(int id, int inicio, int fin){
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run(){
        double total = 0;
        for(int i = inicio ; i < fin ; i++){
            total += (a[i]*b[i]);
        }
        sumasParciales[id] = total;
    }

    public static void main(String[] args) throws InterruptedException{
        int numHebras = 8;
        int n = 100000000;
        a = new double[n];
        b = new double[n];
        double sumaTotal = 0;
        sumasParciales = new double[numHebras];
        //Llenando vectores
        for(int i = 0 ; i < n ; i++){
            a[i] = Math.random();
            b[i] = Math.random();
        }
        //declaracion de hebras
        prodEscalarParalelo[] hebras = new prodEscalarParalelo[numHebras];
        if(n%numHebras == 0) //cuando el rango del problema es divisible por el numero de hebras
            for(int i = 0 ; i < numHebras ; i++) hebras[i] = new prodEscalarParalelo(i,(n/numHebras)* i, (n/numHebras)*(i+1)); 
        else{ //Hay que asignar a la ultima una mayor carga de trabajo
            for(int i = 0 ; i < numHebras-1 ; i++) hebras[i] = new prodEscalarParalelo(i,(n/numHebras)* i, (n/numHebras)*(i+1));
            hebras[numHebras-1] = new prodEscalarParalelo(numHebras-1,(n/numHebras)* (numHebras-1), n);
        }
        //inicio del producto
        long iniTiempo = System.nanoTime();
        for(int i = 0 ; i < numHebras ; i++) hebras[i].start();
        for(int i = 0 ; i < numHebras ; i++) hebras[i].join();
        for(int i = 0 ; i < numHebras ; i++) sumaTotal += sumasParciales[i];
        long finTiempo = System.nanoTime();

        System.out.println("Metodo paralelo: ");
        System.out.println("El tiempo del producto escalar - " + (finTiempo-iniTiempo)/1000000 + " miliseg y su suma total es: " + sumaTotal);   
    }
}