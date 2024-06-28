package gestorrevista;
import java.util.ArrayList;

public class Sistema {
    public void iniciarSesion(Usuario usuario, String contraseña){

    }
    public void someterArt(){
        
    }
    public static void main(String[] args){
        System.out.println("Hola");
        Usuario u1=new Usuario("Mario","Viteri","mhviteri@espol.edu.ec");
        ManejoArchivos.EscribirUsuario("Usuarios.txt",u1);
        System.out.println(ManejoArchivos.LeeFichero("Usuarios.txt"));
        System.out.println(ManejoArchivos.obtenerDatos("Usuarios.txt"));
    }
    
}
