import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    
    /**
     * Este metodo es el que ejecuta el programa 
     * @param archivoUsuarios archivo .txt donde se guardan los usuarios
     * @param archivoArticulos archivo .txt donde se guardan los articulos
     * @param listaProcesos lista de procesos donde se validan y modifican los datos necesarios, debe ser una lista para poder guardar cambios y escribir un txt nuevo con cada ejecucion
     */
    public static void inicio(String archivoUsuarios, String archivoArticulos, ArrayList<GestionarArticulo> listaProcesos) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> usuarios = Usuario.obtenerListaUsuarios(archivoUsuarios);
        System.out.println("**************ARTÍCULOS CIENTÍFICOS**************");
        System.out.println("Escoja entre las opciones:\nS-Someter Articulo\nI-Iniciar Sesión");
        String opcion = sc.nextLine();
        System.out.println("-------------------------------------------------");

        if (opcion.equalsIgnoreCase("s")) {
            System.out.println("**************GESTOR DE PUBLICACIONES**************");
            System.out.println("SE DA PASO A LAS OPCIONES DE AUTOR");
            Autor.someterArt(archivoArticulos, archivoUsuarios, listaProcesos);
            System.out.println("-------------------------------------------------");

        } else if (opcion.equalsIgnoreCase("i")) {
            System.out.println("**************Iniciar Sesión**************");
            System.out.println("Ingrese su rol:\nR-Revisor\nE-Editor");
            String rol = sc.nextLine();
            System.out.println("-------------------------------------------------");
            System.out.println("Ingrese usuario:");
            String usuario = sc.next();
            System.out.println("Ingrese su contraseña:");
            String contraseña = sc.next();
            System.out.println("-------------------------------------------------");

            if (rol.equalsIgnoreCase("e")) {
                if (iniciarSesion(usuario, contraseña, usuarios, "Editor")) {
                    System.out.println("SE DA PASO A LAS OPCIONES DE EDITOR");
                    for (Usuario u: usuarios){
                        if (u instanceof Editor){
                            Editor editor= (Editor) u;
                            if (editor.getUsuario().equals(usuario)){
                                Editor.manejarOpcionesEditor(listaProcesos, editor);
                            }
                        }
                    }
                }
            } else if (rol.equalsIgnoreCase("r")) {
                if (iniciarSesion(usuario, contraseña, usuarios, "Revisor")) {
                    System.out.println("SE DA PASO A LAS OPCIONES DE Revisor");
                    for (Usuario u : usuarios) {
                        if (u instanceof Revisor) {
                            Revisor revisor = (Revisor) u;
                            if (revisor.getUsuario().equals(usuario)) {
                                Revisor.manejarOpcionesRevisor(listaProcesos, revisor);
                                break;
                            }
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


    public static void main(String[] args) {
        /* Sección de pruebas Mario Viteri */
        ManejoArchivos.limpiarArchivo("Usuarios.txt");
        // CREACIÓN DE USUARIOS
        Usuario u1 = new Editor("Mario", "Viteri", "mhviteri@espol.edu.ec", "ESPOL", "mhviteri", "1726419417");
        Usuario u2 = new Editor("Andres", "Perez", "aperez@hotmail.com", "UG", "aperez", "12345");
        Usuario u3 = new Editor("Alejandra", "Castro", "acastro@hotmail.com", "UEES", "acastro", "acastro12");
        Usuario u4 = new Revisor("Juan", "Holguin", "jholguin@hotmail.com", "Matematicas", "holguin", "JH95", 4);
        Usuario u6 = new Revisor("Jose", "Holguin", "jholguin@hotmail.com", "Matematicas", "jolguin", "JH94", 4);
        Usuario u7 = new Revisor("Julia", "Holguin", "jholguin@hotmail.com", "Matematicas", "jhlguin", "JH54", 4);
        Usuario u8 = new Revisor("Juan Esteban", "Holguin", "jholguin@hotmail.com", "Matematicas", "jhoguin", "J954", 4);
        Usuario u9 = new Revisor("Julieta", "Holguin", "jholguin@hotmail.com", "Matematicas", "jholuin", "H954", 4);
        Usuario u10 = new Autor("Hector", "Morales", "hmorales@espol.edu.ec", "ESPOL", "Matematicas");
        // GUARDADO EN EL ARCHIVO "Usuarios.txt"
        Usuario.EscribirUsuario("Usuarios.txt", u1);
        Usuario.EscribirUsuario("Usuarios.txt", u2);
        Usuario.EscribirUsuario("Usuarios.txt", u3);
        Usuario.EscribirUsuario("Usuarios.txt", u4);
        Usuario.EscribirUsuario("Usuarios.txt", u6);
        Usuario.EscribirUsuario("Usuarios.txt", u7);
        Usuario.EscribirUsuario("Usuarios.txt", u8);
        Usuario.EscribirUsuario("Usuarios.txt", u9);
        Usuario.EscribirUsuario("Usuarios.txt", u10);
        // PROGRAMA
        ArrayList<GestionarArticulo> listaProcesos=new ArrayList<>(GestionarArticulo.obtenerListaProcesos("Revisiones.txt"));
        inicio("Usuarios.txt", "Articulos.txt", listaProcesos);
        
        ManejoArchivos.limpiarArchivo("Revisiones.txt");

        for(GestionarArticulo proceso : listaProcesos){
            GestionarArticulo.escribirProceso("Revisiones.txt", proceso);
        }
    }
}
