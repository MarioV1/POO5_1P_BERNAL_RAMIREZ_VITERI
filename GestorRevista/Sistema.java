package gestorrevista;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    
    public static void inicio(ArrayList<Usuario> usuarios){
        Scanner sc = new Scanner(System.in);
        System.out.println("**************GESTOR DE PUBLICACIONES**************");
        System.out.println("Ingrese su rol:\nR-Revisor\nE-Editor\nA-Autor");
        String rol=sc.nextLine();
        if(rol.equalsIgnoreCase("e")){
            System.out.println("Ingrese usuario:");
            String usuario = sc.next();
            System.out.println("Ingrese su contraseña:");
            String contraseña = sc.next();
            if(iniciarSesion(usuario, contraseña, usuarios)==true){
                System.out.println("SE DA PASO A LAS OPCIONES DE EDITOR");
            }
        }
        if(rol.equalsIgnoreCase("r")){
            System.out.println("Ingrese usuario:");
            String usuario = sc.next();
            System.out.println("Ingrese su contraseña:");
            String contraseña = sc.next();
            if(iniciarSesion(usuario, contraseña, usuarios)==true){
                System.out.println("SE DA PASO A LAS OPCIONES DE REVISOR");
            }
        }
        if(rol.equalsIgnoreCase("a")){
            System.out.println("SE DA PASO A LAS OPCIONES DE AUTOR");
        }
        

    }
    public static boolean iniciarSesion(String usuario, String contraseña,ArrayList<Usuario> usuarios){
        for (Usuario u: usuarios) {
            while(u.correo.contains(usuario)){
                if (u.getClass()==Editor.class) {
                    Editor editor=(Editor) u;
                        if(editor.getUsuario().equals(usuario)){
                            if(editor.getContraseña().equals(contraseña)){
                                System.out.println("INGRESO EXITOSO");
                                return true;
                            }
                            else{
                                System.out.println("CONTRASEÑA INCORRECTA");
                                return false;
                            }
                        }
                        else{
                            System.out.println("USUARIO INEXISTENTE");
                            return false;
                        }
                }
                if(u.getClass()==Revisor.class){
                    Revisor revisor=(Revisor) u;
                    if(revisor.getUsuario().equals(usuario)){
                        if(revisor.getContraseña().equals(contraseña)){
                            System.out.println("INGRESO EXITOSO");
                            return true;
                        }
                        else{
                            System.out.println("CONTRASEÑA INCORRECTA");
                            return false;
                        }
                    }
                    else{
                        System.out.println("USUARIO INEXISTENTE");
                        return false;
                    }
                } 
            }
    }
    return false;
}
    public void someterArt(){
        
    }
    public static void main(String[] args){
        /*Sección de pruebas Mario Viteri */
        ManejoArchivos.limpiarArchivo();
        System.out.println("Hola");
        //CREACIÓN DE USUARIOS
        Usuario u1=new Editor("Mario","Viteri","mhviteri@espol.edu.ec","ESPOL","mhviteri","1726419417");
        Usuario u2=new Editor("Andres","Perez","aperez@hotmail.com","UG","aperez","12345");
        Usuario u3=new Editor("Alejandra","Castro", "acastro@hotmail.com", "UEES","acastro","acastro12");
        Usuario u4=new Revisor("Juan","Holguin", "jholguin@hotmail.com", "Matematicas","jholguin","JH954",4);
        Usuario u5=new Autor("Hector","Morales", "hmorales@espol.edu.ec","iD446", "ESPOL","Matmaticas");
        //GUARDADO EN EL ARCHIVO "Usuarios.txt"
        Usuario.EscribirUsuario("Usuarios.txt",u1);
        Usuario.EscribirUsuario("Usuarios.txt",u2);
        Usuario.EscribirUsuario("Usuarios.txt",u3);
        Usuario.EscribirUsuario("Usuarios.txt",u4);
        Usuario.EscribirUsuario("Usuarios.txt",u5);
        //System.out.println(ManejoArchivos.LeeFichero("Usuarios.txt"));
        ArrayList<Usuario> ListaUsuarios=new ArrayList<Usuario>(Usuario.obtenerListaUsuarios("Usuarios.txt")) ;
        //System.out.println(ListaUsuarios);
        inicio(ListaUsuarios);
    }
    
}
