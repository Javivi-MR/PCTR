  public class PrimosSecuencial{
	
  public static boolean esPrimo(long n){
    if(n<=1) return(false);
    for(long i=2; i<=Math.sqrt(n); i++)
      if(n%i == 0) return(false);
    return(true);
  }	
		
  public static void main(String[] args) 
    throws Exception{

    long intervalo = Long.parseLong(args[0]); 	  
    int total = 0;
   
    long inicTiempo = System.nanoTime();
    for(long i=0; i<=intervalo;i++)
      if(esPrimo(i)) total++;
    long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e9;
    System.out.println("Encontrados "+total+" primos"+" en "+ tiempoTotal+" segundos");
  }
}
