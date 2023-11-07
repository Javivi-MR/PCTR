import java.util.*;
import java.io.*;
import java.net.*;

public class volcadoRedSecuencial{

  public static void descargar(String dir, int i) throws  URISyntaxException, MalformedURLException{
      try{
           URI uri = new URI(dir);
           URL url = uri.toURL();
           BufferedReader read   =  new BufferedReader(new InputStreamReader(url.openStream())); 
           String         nombre =  String.valueOf(i)+".html";
           BufferedWriter writer =  new BufferedWriter(new FileWriter(nombre)); 
            String line; 
            while ((line = read.readLine()) != null){writer.write(line);} 
            read.close(); 
            writer.close();
            System.out.println(dir+" descargada...");
      }catch(IOException e){}
   } 
  
  public static void main(String[] args) throws  URISyntaxException{

    int cont=0;
    long iniTiempo = System.nanoTime();	  
    try {
    	 String linea=" ";      
    	 RandomAccessFile direcciones = new RandomAccessFile("direccionesRed.txt","r");
         while(linea!=null){
           linea =direcciones.readLine();
           if(linea!=null)descargar(linea, cont);
           cont++;
         }
         direcciones.close();
        }catch (FileNotFoundException e) {}
         catch (IOException e) {}
    long finTiempo = System.nanoTime();
    System.out.println("Tiempo Total (segundos): "+(finTiempo-iniTiempo)/1.0e9);
  }  
}
