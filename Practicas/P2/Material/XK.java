import java.util.*;
public class XK  extends Thread{

    static double[] data = new double[10];
    double escalar = 2;
    int inicio;int fin;

    public XK(int ini, int fin){
      this.inicio = ini;
      this.fin    = fin;
    }

    public void run(){
      for(int i=inicio; i<=fin; i++)
        data[i]=data[i]*escalar;
    }

    public static void main(String[] args)
    	throws Exception{
      for(int i=0; i<data.length; i++)
        data[i]=Math.random();
      System.out.println(Arrays.toString(data));
      XK h1 = new XK(0, 4);
      XK h2 = new XK(5, 9);
      h1.start(); h2.start();
      h1.join(); h2.join();
      System.out.println(Arrays.toString(data));
    }
}