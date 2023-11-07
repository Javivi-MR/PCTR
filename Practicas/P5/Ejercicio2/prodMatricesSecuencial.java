public class prodMatricesSecuencial {
    public static void main(String[] args){
        int n = 1000;
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        int[][] C = new int[n][n];

        // Inicializamos las matrices
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                A[i][j] = 1;
                B[i][j] = 2;
                C[i][j] = 0;
            }
        }

        long iniTiempo = System.nanoTime();
        // Realizamos el producto de las matrices
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                for(int k = 0 ; k < n ; k++){
                    C[i][j] += (A[j][k] * B[k][j]);
                }
            }
        }
        long finTiempo = System.nanoTime();

        System.out.println("Tiempo: " + (finTiempo - iniTiempo)/1000000 + " ms");

        // Mostramos el resultado
        /* 
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }*/
    }
}
