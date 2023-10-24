public class prodEscalar {
    public static void main(String[] args){
        int n = 50000000;
        double[] v = new double[n];
        double[] w = new double[n];
        //llenando el vector
        for(int i=0; i<v.length; i++)v[i]=Math.random();
        for(int i=0; i<v.length; i++)w[i]=Math.random();
        //vector lleno
        
        //realizando prod escalar
        double resultado = 0;
        long iniTiempo = System.nanoTime();
        for(int i = 0 ; i < v.length ; i++) resultado += (v[i] * w[i]);
        long finTiempo = System.nanoTime();

        System.out.println("Metodo secuencial: ");
        System.out.println("El tiempo del producto escalar - " + (finTiempo-iniTiempo)/1000000 + " miliseg y la suma total es de " + resultado);
    }
}
