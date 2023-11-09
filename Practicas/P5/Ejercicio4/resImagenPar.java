import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

public class resImagenPar implements Runnable{
    
    private int ini,fin;
    public static int[][] img,imgRes;
    public static int k = 10024;

    public resImagenPar(int ini, int fin){
        this.ini = ini;
        this.fin = fin;
    }

    public void run(){
        for(int i = ini ; i < fin ; i++){
            for(int j = 0 ; j < k-1 ; j++){
                imgRes[i][j] = (4*img[i][j] - img[i+1][j] - img[i][j+1] - img[i-1][j] - img[i][j-1])/8;
                if (imgRes[i][j] > 255) {
                    imgRes[i][j] = 255;
                }

                if (imgRes[i][j] < 0) {
                    imgRes[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        img = new int[k][k];
        imgRes = new int[k][k];
        Random gen = new java.util.Random();
        //inicializamos la imagen
        for(int i = 0 ; i < k ; i++){
            for(int j = 0 ; j < k ; j++){
                img[i][j] = gen.nextInt(256);
                //img[i][j] = 1;
            }
        }

        int nNucleos = Runtime.getRuntime().availableProcessors();
        int nTareas = (k - 2) / nNucleos; //la primera y ultima fila no hay que tocarlas.

        ExecutorService ept = Executors.newFixedThreadPool(nNucleos);

        long iniTiempo = System.nanoTime();
        for(int i = 1 ; i < k-1 ; i+=2){
            ept.submit(new resImagenPar(i,i+2));
        }

        for(int i = 0 ; i < k ; i++){
            imgRes[0][i] = img[0][i]; //primera fila
            imgRes[i][0] = img[i][0]; //primera columna
            imgRes[k-1][i] = img[k-1][i]; //ultima fila
            imgRes[i][k-1] = img[i][k-1]; //ultima columna
        }

        ept.shutdown();
        ept.awaitTermination(10,TimeUnit.SECONDS);
        long finTiempo = System.nanoTime();

        //mostrar img
        /*for(int i = 0 ; i < k ; i++){
            for(int j = 0 ; j < k ; j++){
                System.out.print(imgRes[i][j] + " ");
            }
            System.out.println();
        }*/

        System.out.println("El tiempo de la version paralela es de: " + (finTiempo - iniTiempo)/1000000 + " ms");
    }
}
