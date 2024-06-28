package gestorrevista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManejoArchivos {
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

    public static void EscribirUsuario(String nombreArchivo, Usuario usuario) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(usuario.nombre+"-");
            bw.write(usuario.apellido+"-");
            bw.write(usuario.correo+"\n");    
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public static ArrayList<ArrayList<String>> obtenerDatos (String archivo){
        ArrayList<String> lineas=ManejoArchivos.LeeFichero(archivo);  
        ArrayList<ArrayList<String>>  listaUsuarios= new ArrayList<>();
        for(String s:lineas){
            String [] datos=s.split("-");
            String nombre=datos[0];
            String apellido=datos[1];
            String correo=datos[2];
            Usuario u=new Usuario(nombre,apellido,correo);
            ArrayList<String> usuario = new ArrayList<>();
            usuario.add(u.nombre);
            usuario.add(u.apellido);
            usuario.add(u.correo);
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
        }


}
