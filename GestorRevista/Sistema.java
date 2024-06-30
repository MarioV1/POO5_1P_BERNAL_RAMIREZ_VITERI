package gestorrevista;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    /**
     * Inicio del programa, si es autor accede automáticamente, sino se deriva a iniciarSesion()
     * @param usuarios ArrayList con todos los usuarios de la base
     */
    public static void inicio(ArrayList<Usuario> usuarios, ArrayList<Articulo> articulos){
        Scanner sc = new Scanner(System.in);
        System.out.println("**************GESTOR DE PUBLICACIONES**************");
        System.out.println("Ingrese su rol:\nR-Revisor\nE-Editor\nA-Autor");
        String rol=sc.nextLine();
        if(rol.equalsIgnoreCase("e")){
            System.out.println("Ingrese usuario:");
            String usuario = sc.next();
            System.out.println("Ingrese su contraseña:");
            String contraseña = sc.next();
            if(iniciarSesion(usuario, contraseña, usuarios,"Editor")==true){
                System.out.println("SE DA PASO A LAS OPCIONES DE EDITOR");
            }
        }
        if(rol.equalsIgnoreCase("r")){
            System.out.println("Ingrese usuario:");
            String usuario = sc.next();
            System.out.println("Ingrese su contraseña:");
            String contraseña = sc.next();
            if(iniciarSesion(usuario, contraseña, usuarios,"Revisor")==true){
                System.out.println("SE DA PASO A LAS OPCIONES DE REVISOR");
        }    
    }
        if(rol.equalsIgnoreCase("a")){
            System.out.println("SE DA PASO A LAS OPCIONES DE AUTOR");
            Autor.someterArt(articulos, usuarios);
        }
        else{
        }
        sc.close();
    }
    /**
     * Método para validar las credenciales de Editores y Revisores
     * @param usuario Ingresado por el usuario
     * @param contraseña Ingresado por el usuario
     * @param usuarios ArrayList con todos los usuarios de la base 
     * @return Booleano en función de: El usuario existe ^ La contraseña es correcta
     */
    public static boolean iniciarSesion(String usuario, String contraseña,ArrayList<Usuario> usuarios, String rol){
        for (Usuario u: usuarios) {
            if(u.correo.contains(usuario)){
                if (u.getClass()==Editor.class && rol=="Editor") {
                    Editor editor=(Editor) u;
                        if(editor.getUsuario().equals(usuario)){
                            if(editor.getContraseña().equals(contraseña)){
                                System.out.println("INGRESO EXITOSO");
                                return true;
                            }
                            if(editor.getContraseña().equals(contraseña)==false){
                                System.out.println("CONTRASEÑA INCORRECTA");
                                return false;
                            }
                        }
                        if(editor.getUsuario().equals(usuario)==false){
                            System.out.println("USUARIO INEXISTENTE");
                            return false;
                        }
                }
                if(u.getClass()==Revisor.class && rol=="Revisor"){
                    Revisor revisor=(Revisor) u;
                    if(revisor.getUsuario().equals(usuario)){
                        if(revisor.getContraseña().equals(contraseña)){
                            System.out.println("INGRESO EXITOSO");
                            return true;
                        }
                        if(revisor.getContraseña().equals(contraseña)==false){
                            System.out.println("CONTRASEÑA INCORRECTA");
                            return false;
                        }
                    }
                    if(revisor.getUsuario().equals(usuario)==false){
                        System.out.println("USUARIO INEXISTENTE");
                        return false;
                    }
                } 
                System.out.println("ROL EQUIVOCADO");
                return false;
            }
    }
    System.out.println("USUARIO INEXISTENTE");
    return false;
}
    public static void main(String[] args){
        /*Sección de pruebas Mario Viteri */
        ManejoArchivos.limpiarArchivo();
        //CREACIÓN DE USUARIOS
        Usuario u1=new Editor("Mario","Viteri","mhviteri@espol.edu.ec","ESPOL","mhviteri","1726419417");
        Usuario u2=new Editor("Andres","Perez","aperez@hotmail.com","UG","aperez","12345");
        Usuario u3=new Editor("Alejandra","Castro", "acastro@hotmail.com", "UEES","acastro","acastro12");
        Usuario u4=new Revisor("Juan","Holguin", "jholguin@hotmail.com", "Matematicas","jholguin","JH954",4);
        Usuario u5=new Autor("Hector","Morales", "hmorales@espol.edu.ec", "ESPOL","Matmaticas");
        //GUARDADO EN EL ARCHIVO "Usuarios.txt"
        Usuario.EscribirUsuario("Usuarios.txt",u1);
        Usuario.EscribirUsuario("Usuarios.txt",u2);
        Usuario.EscribirUsuario("Usuarios.txt",u3);
        Usuario.EscribirUsuario("Usuarios.txt",u4);
        Usuario.EscribirUsuario("Usuarios.txt",u5);
        //System.out.println(ManejoArchivos.LeeFichero("Usuarios.txt"));
        ArrayList<Usuario> ListaUsuarios=new ArrayList<Usuario>(Usuario.obtenerListaUsuarios("Usuarios.txt")) ;
        ArrayList<Articulo> listaArt=new ArrayList<Articulo>();
        //System.out.println(ListaUsuarios);
        inicio(ListaUsuarios, listaArt);
        System.out.println(listaArt);
        
    }
    
}
