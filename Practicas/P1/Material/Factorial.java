public class Factorial{
  private static int factorial(int n){
    if (n == 0) return(1);
    if (n == 1) return(1);
    return (n*factorial(n-1));
  }
  
  public static void main(String[] args){ 
    int dato;  	
    if (args.length == 0){
      System.out.println ("Debe dar un natural como argumento...");
      System.exit(-1);
     }
    dato = Integer.valueOf(args[0]).intValue();
    System.out.println ("El factorial de "+ dato+" es "+factorial(dato));  
  }
}