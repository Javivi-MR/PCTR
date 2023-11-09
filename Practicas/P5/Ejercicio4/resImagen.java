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
                //img[i][j] = 255;
            }
        }

        long iniTiempo = System.nanoTime();

        for(int i = 0 ; i < k ; i++){
            imgRes[0][i] = img[0][i]; //primera fila
            imgRes[i][0] = img[i][0]; //primera columna
            imgRes[k-1][i] = img[k-1][i]; //ultima fila
            imgRes[i][k-1] = img[i][k-1]; //ultima columna
        }

        for(int i = 1 ; i < (k-1) ; i++){
            for(int j = 1 ; j < (k-1) ; j++){
                imgRes[i][j] = (4*img[i][j] - img[i+1][j] - img[i][j+1] - img[i-1][j] - img[i][j-1])/8;
                if (imgRes[i][j] > 255) {
                    imgRes[i][j] = 255;
                }

                if (imgRes[i][j] < 0) {
                    imgRes[i][j] = 0;
                }
            }
        }
        long finTiempo = System.nanoTime();

        //mostrar imgRes
        /*for(int i = 0 ; i < k ; i++){
            for(int j = 0 ; j < k ; j++){
                System.out.print(imgRes[i][j] + " ");
            }
            System.out.println();
        }*/

        System.out.println("El tiempo tomado por el algoritmo secuencial es de: " + (finTiempo-iniTiempo)/1000000 + " ms");
    }
}