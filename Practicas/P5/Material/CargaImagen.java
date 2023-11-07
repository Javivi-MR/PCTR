
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uca
 */
public class CargaImagen {

    /**
     * Carga una imagen en una matriz. La imagen es convertida automáticamente
     * a una escala de niveles de grises [0-255].
     * @param fichero La imagen
     * @return La matriz que representa la imagen en escala de grises.
     * @throws IOException 
     */
    public static int[][] cargar(String fichero) throws IOException{
        
        BufferedImage imagen = ImageIO.read(new File(fichero));
        
        int[][] matriz = new int[imagen.getHeight()][imagen.getWidth()];
        
        for(int i = 0 ; i < imagen.getHeight() ; ++i)
        {
            for(int j = 0 ; j < imagen.getWidth() ; ++j)
            {
                Color c = new Color(imagen.getRGB(j, i));
                matriz[i][j] = (int) ((c.getRed() + c.getGreen() + c.getBlue()) / 3);
            }
        }
        
        return matriz;
    }
    
    /**
     * Guarda la matriz en un fichero en formato PNG.
     * @param matriz Cada celda de la matriz representa un pixel de la imagen, codificado en 256 niveles de grises [0-255].
     * @param fichero Fichero en formato PNG en el que se guardará la matriz.
     * @throws IOException 
     */
    public static void guardar(int[][] matriz, String fichero) throws IOException{
        
        BufferedImage imagen = new BufferedImage(matriz[0].length, matriz.length, BufferedImage.TYPE_4BYTE_ABGR);
        
        for(int i = 0 ; i < matriz.length ; ++i)
        {
            for(int j = 0 ; j < matriz[0].length ; ++j)
            {
                Color c = new Color(matriz[i][j], matriz[i][j], matriz[i][j]);
                imagen.setRGB(j, i, c.getRGB());
            }
        }
        
        ImageIO.write(imagen, "png", new File(fichero));
    }
    
    public static void ConvertirAGris(String ficheroOriginal, String ficheroResultado) throws IOException {
        
        BufferedImage imagen = ImageIO.read(new File(ficheroOriginal));
        
        for(int i = 0 ; i < imagen.getHeight() ; ++i)
        {
            for(int j = 0 ; j < imagen.getWidth() ; ++j)
            {
                Color c = new Color(imagen.getRGB(j, i));
                int gris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                imagen.setRGB(j, i, (new Color(gris, gris, gris)).getRGB());
            }
        }
        
        ImageIO.write(imagen, "png", new File(ficheroResultado));
    }
    
    public static void main(String[] args) throws IOException {
        
        System.out.println("Convirtiendo a escala de grises");        
        ConvertirAGris("uca.png", "uca_gris.png");
        
        System.out.println("Cargando fichero en matriz");
        int[][] matriz = cargar("uca_gris.png");
        
        System.out.println("Guardando matriz en fichero");
        guardar(matriz, "uca_gris_procesada.png");
    }
}
