public class hebra extends Thread{

    public static int n = 0;
    private int id;
    private int x;

    public hebra(int x,int id){
        this.x = x;
        this.id = id;   
    }

    public void run(){
        switch(id)
        {
            case(0):
                for(int i = 0; i < x; i++)
                    n++;
                break;
            case(1):
                for(int i = 0; i < x; i++)
                    n--;
                break;
        }
    }
}
//con pthread se comparte el espacio de memoria,
//Usando fork, el espacio de memoria no se comparten ya que crean procesos con memoria separada
// Tambien se puede usar en c la libreria system v ipc con shm.h qye permite compartir memoria entre procesos. con dos procesos creados con fork podenos usar shmat(puntero) para compartir memoria