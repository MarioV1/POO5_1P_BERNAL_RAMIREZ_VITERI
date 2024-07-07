package gestorrevista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManejoArchivos {
    /**
     * Limpia el archivo de usuario con cada ejecución para que no se dupliquen y se pueda trabajar mejor
     **/
    public static void limpiarArchivo(String archivo){
        try (FileWriter fw = new FileWriter(archivo,false)) {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param nombrearchivo Nombre.extension del archivo que se desea leer
     * @return ArrayList<String> Devuelve un arreglo del contenido del documento separado por cada salto de línea
     **/
    public static ArrayList<String> LeeFichero(String nombrearchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;
    }


        /**
     * @param nombrearchivo Nombre.extension del archivo donde se desea escribir
     * @param contenido     El contenido que se desea escribir en el archivo
     **/
    public static void escribirArchivo(String nombrearchivo, String contenido) {
        try (FileWriter fw = new FileWriter(nombrearchivo, true)) {
            fw.write(contenido + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
