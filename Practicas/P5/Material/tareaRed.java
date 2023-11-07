import java.util.*;
import java.io.*;
import java.net.*;

public class tareaRed implements Runnable{
   
  private String dir;
  private URL url;
  private int j;
   	
  public tareaRed(String d, int i){dir=d; j=i;}
  
  public void run(){
    try{
           URI uri = new URI(dir);
           URL url = uri.toURL();
           BufferedReader read   =  new BufferedReader(new InputStreamReader(url.openStream())); 
           String         nombre =  String.valueOf(j)+".html";
           BufferedWriter writer =  new BufferedWriter(new FileWriter(nombre)); 
           String line; 
           while ((line = read.readLine()) != null){writer.write(line);} 
           read.close(); 
           writer.close();
           System.out.println(dir+" descargada...");
      }catch(IOException | URISyntaxException  e){}
}
}