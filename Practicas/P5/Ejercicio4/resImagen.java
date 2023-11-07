import java.util.Random;

public class resImagen{
    public static void main(String[] args){
        int k = 10024;
        int[][] img = new int[k][k];
        int[][] imgRes = new int[k][k];
        Random gen = new Random();

        for(int i = 0 ; i < k ; i++){
            for(int j = 0 ; j < k ; j++){
                img[i][j] = gen.nextInt(256);
            }
        }

        long iniTiempo = System.nanoTime();
        for(int i = 1 ; i < (k-1) ; i++){
            for(int j = 1 ; j < (k-1) ; j++){
                imgRes[i][j] = (4*img[i][j] - img[i+1][j] - img[i][j+1] - img[i-1][j] - img[i][j-1])/8;
            }
        }
        long finTiempo = System.nanoTime();

        System.out.println("El tiempo tomado por el algoritmo secuencial es de: " + (finTiempo-iniTiempo)/1000000 + " ms");
    }
}