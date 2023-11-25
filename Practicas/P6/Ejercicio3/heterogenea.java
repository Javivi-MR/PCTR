public class heterogenea {
    private int n;
    private int m;

    public heterogenea(){
        n = 0;
        m = 0;
    }

    synchronized public void incN(){
        for(int i = 0 ; i < 1000000 ; i++){
            n++;
        }
    }

    public void incM(){
        for(int i = 0 ; i < 1000000 ; i++){
            m++;
        }
    }

    public int getN(){
        return n;
    }

    public int getM(){
        return m;
    }
}
