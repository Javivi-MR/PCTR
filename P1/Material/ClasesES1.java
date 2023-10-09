import java.io.*;
public class ClasesES1{
  public static void main(String[] args)
   throws Exception{
  System.out.println("ahora vamos a leer y escribir desde tecladoa pantalla");
  BufferedReader dato = new BufferedReader(
                         new InputStreamReader(System.in));
  short q = Short.parseShort(dato.readLine());
  System.out.println(2*q);
  int x = Integer.parseInt(dato.readLine());
  System.out.println(2*x);
  float y = Float.parseFloat(dato.readLine());
  System.out.println(2*y);
  byte k = Byte.parseByte(dato.readLine());
  System.out.println(2*k);
  double g = Double.parseDouble(dato.readLine());
  System.out.println(2*g);
  long m = Long.parseLong(dato.readLine());
  System.out.println(2*m);
//pero para cadenas es mas facil, y no hay que hacer Parse...
  String u = dato.readLine();
  System.out.println(u);
//y para caracteres...como es conocido
  int i = dato.read();
 }
}
