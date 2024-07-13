
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

        /**
     * Inicio del programa, si se quiere gestionar un articulo accede automáticamente, sino se deriva a iniciarSesion()
     * @param archivoUsuarios Archivo de donde se leerán y extraerán los usuarios
     * @param archivoArticulos Archivo de donde se leerán y extraerán los usuarios
     * @param listaGestion Lista donde se guardará el proceso en caso de revisión en caso de someter artículo
     */
    public static void inicio(String archivoUsuarios,String archivoArticulos, ArrayList<GestionarArticulo> listaGestion){
        
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> usuarios=Usuario.obtenerListaUsuarios(archivoUsuarios);
        System.out.println("**************ARTÍCULOS CIENTÍFICOS**************");
        System.out.println("Escoja entre las opciones:\nS-Someter Articulo\nI-Iniciar Sesión");
        String opcion = sc.nextLine();
        System.out.println("-------------------------------------------------");

        if(opcion.equalsIgnoreCase("s")){
            System.out.println("**************GESTOR DE PUBLICACIONES**************");
            System.out.println("SE DA PASO A LAS OPCIONES DE AUTOR");
            Autor.someterArt(archivoArticulos,archivoUsuarios,listaGestion);
            System.out.println("-------------------------------------------------");

        }if(opcion.equalsIgnoreCase("i")){
            System.out.println("**************Iniciar Sesión**************");
            System.out.println("Ingrese su rol:\nR-Revisor\nE-Editor");
            String rol=sc.nextLine();

            
            if(rol.equalsIgnoreCase("e")){
                System.out.println("Ingrese usuario:");
                String usuario = sc.next();
                System.out.println("Ingrese su contraseña:");
                String contraseña = sc.next();
                if(iniciarSesion(usuario, contraseña, usuarios,"Editor")==true){
                    System.out.println("SE DA PASO A LAS OPCIONES DE EDITOR");
                }
            }else if (rol.equalsIgnoreCase("r")) {
                System.out.println("Ingrese usuario:");
                String usuario = sc.next();
                System.out.println("Ingrese su contraseña:");
                String contraseña = sc.next();
                if (iniciarSesion(usuario, contraseña, usuarios, "Revisor")==true) {
                    System.out.println("SE DA PASO A LAS OPCIONES DE Revisor");
                    for (Usuario u : usuarios) {
                        if (u instanceof Revisor) {
                            System.out.println("SE DA PASO A LAS OPCIONES DE REVISOR");
                            Revisor revisor = (Revisor) u;
                            Revisor.manejarOpcionesRevisor(archivoUsuarios, archivoArticulos, revisor, listaGestion);
                            break;
                        }
                    }
                }
            }
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
        ManejoArchivos.limpiarArchivo("Usuarios.txt");
        //CREACIÓN DE USUARIOS
        Usuario u1=new Editor("Mario","Viteri","mhviteri@espol.edu.ec","ESPOL","mhviteri","1726419417");//DOWNCASTING
        Usuario u2=new Editor("Andres","Perez","aperez@hotmail.com","UG","aperez","12345");//DOWNCASTING
        Usuario u3=new Editor("Alejandra","Castro", "acastro@hotmail.com", "UEES","acastro","acastro12");//DOWNCASTING
        Usuario u4=new Revisor("Juan","Holguin", "jholguin@hotmail.com", "Matematicas","jholguin","JH954",4);//DOWNCASTING
        Usuario u6=new Revisor("Jose","Holguin", "jholguin@hotmail.com", "Matematicas","jholguin","JH954",4);//DOWNCASTING
        Usuario u7=new Revisor("Julia","Holguin", "jholguin@hotmail.com", "Matematicas","jholguin","JH954",4);//DOWNCASTING
        Usuario u8=new Revisor("Juan Esteban","Holguin", "jholguin@hotmail.com", "Matematicas","jholguin","JH954",4);//DOWNCASTING
        Usuario u9=new Revisor("Julieta","Holguin", "jholguin@hotmail.com", "Matematicas","jholguin","JH954",4);//DOWNCASTING
        Usuario u10=new Autor("Hector","Morales", "hmorales@espol.edu.ec", "ESPOL","Matmaticas");//DOWNCASTING
        //GUARDADO EN EL ARCHIVO "Usuarios.txt"
        Usuario.EscribirUsuario("Usuarios.txt",u1);
        Usuario.EscribirUsuario("Usuarios.txt",u2);
        Usuario.EscribirUsuario("Usuarios.txt",u3);
        Usuario.EscribirUsuario("Usuarios.txt",u4);
        Usuario.EscribirUsuario("Usuarios.txt",u6);
        Usuario.EscribirUsuario("Usuarios.txt",u7);
        Usuario.EscribirUsuario("Usuarios.txt",u8);
        Usuario.EscribirUsuario("Usuarios.txt",u9);
        Usuario.EscribirUsuario("Usuarios.txt",u10);
        //PROGRAMA
        ArrayList<Usuario> ListaUsuarios=new ArrayList<Usuario>(Usuario.obtenerListaUsuarios("Usuarios.txt")) ;
        ArrayList<Articulo> listaArt=new ArrayList<Articulo>(Articulo.obtenerListaArticulos("Articulos.txt"));
        ArrayList<GestionarArticulo> listaGestion=new ArrayList<GestionarArticulo>();
        System.out.println(ListaUsuarios);

        

        inicio("Usuarios.txt","Articulos.txt",listaGestion);

      
    }
    
}
