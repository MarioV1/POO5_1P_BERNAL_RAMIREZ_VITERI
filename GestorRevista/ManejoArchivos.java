package gestorrevista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManejoArchivos {
    
        public static void limpiarArchivo(){
            try (FileWriter fw = new FileWriter("Usuarios.txt",false)) {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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


}
