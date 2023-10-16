public class tareaRunnable implements Runnable{
    private int x;
    private int id;
    private ClaseCompartida C;

    public tareaRunnable(int x, int id, ClaseCompartida C){
        this.x = x;
        this.id = id;
        this.C = C;
    }

    public void run()
    {
        switch(id)
        {
            case(0):
                for(int i = 0; i < x; i++)
                    C.inc();
                break;
            case(1):
                for(int i = 0; i < x; i++)
                    C.dec();
                break;
        }
    }
}
