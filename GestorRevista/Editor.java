import java.util.ArrayList;
import java.util.Scanner;
public class Editor extends Usuario {
    private String journal;
    private String usuario;
    private String contraseña;
    
    //constructor
    public Editor(String nombre, String apellido, String correo, String journal, String usuario, String contraseña){
        super(nombre,apellido,correo);
        this.journal = journal;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    public Editor(String journal, String usuario, String contraseña){
        this.journal = journal;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    //getters y setters
    public String getJournal(){
        return journal;
    }

    public void setJornal(String journal){
        this.journal = journal;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getContraseña(){
        return contraseña;
    }

    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }

    //Métodos    
    /**
     * Método para revisar los comentarios de los revisores
     * @param articulo El artículo que se está revisando
     */
    public static void desicionRevisores(Articulo articulo) {
        
        EstadoAriculo estado = articulo.getEstadoArticulo();

        switch (estado) {
            case ACEPTADO:
                System.out.println("Artículo aceptado para publicación.");
                break;

            case RECHAZADO:
                System.out.println("Artículo rechazado para publicación.");
                break;

            case REVISION:
                System.out.println("Decisión pendiente. Más revisión requerida.");
                break;

            default:
                System.out.println("Estado desconocido.");
                break;
        }
    }

        /**
     * Método para revisar los comentarios de los articulos dejados por un revisor
     * @param revisor Revisor asignado al articulo
     * @param articulo Articulo con sus comentarios
     */
    public static void revisarComentarios(Articulo articulo){
        System.out.println("Dejó el siguiente comentario\n" + articulo.getComentarios());
    }


    /**
     * Método para notificar al investigador del resultado final por correo electrónico.
     * @param articulo El artículo revisado
     * @param comentarios Los comentarios de los revisores
     */
    public static void notificarInvestigador(Articulo articulo, Editor editor) {
        String mensaje = "Estimado autor," + articulo.getDatosAutor().getNombre() + "\n\n";
        mensaje += "Queremos informarle que el artículo con código " + articulo.getCodigo() + " ha sido revisado.\n";
        mensaje += "Comentarios de los revisores:\n";
        mensaje += articulo.getComentarios() + "\n";
        mensaje += "Atentamente,\n";
        mensaje += "Equipo editorial " + editor.journal;

        // Lógica para enviar correo electrónico al autor
        System.out.println("**************Generando Notificación**************");
        System.out.println(articulo.getDatosAutor().getCorreo() + "\nResultado de revisión de artículo\n" + mensaje);
    }


        /**
     * Busca en la lista de procesos el articulo mediante el codigo
     * @param listaProcesos Lista de procesos
     * @param codigo Codigo del articulo
     */

     private static GestionarArticulo buscarProcesoPorCodigo(ArrayList<GestionarArticulo> listaProcesos, String codigo) {
        for (GestionarArticulo proceso : listaProcesos) {
            if (proceso.articulo.getCodigo().equals(codigo)) {
                return proceso;
            }
        }
        return null;
    }


    /**
     * Método para guardar los detalles de la revisión en un archivo `revisiones.txt`.
     * @param articulo El artículo revisado
     */
    public static void guardarRevision(Articulo articulo) {
        String detallesRevision = "Código del artículo: " + articulo.getCodigo() + "\n" + "Comentarios del revisor:\n" + 
        articulo.getComentarios() + "\n";

        System.out.println("Guardando Revisión...\n" + detallesRevision);

    }
   
    /**
     * Método para manejar las Opciones del sistema del Editor
     * @param listaProcesos La lista de procesos
     * @param editor Editor asignado
     */
    public static void manejarOpcionesEditor(ArrayList<GestionarArticulo> listaProcesos, Editor editor) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("**************OPCIONES DE EDITOR**************");
    
        boolean codigoValido = false;

        System.out.println("Ingrese el código del Artículo deseado");
        String codigoIngresado = sc.nextLine();

        while (!codigoValido) {
            GestionarArticulo validarProceso = buscarProcesoPorCodigo(listaProcesos, codigoIngresado);
            if (validarProceso != null) {
                System.out.println("**************OPCIONES DEL ARTÍCULO**************");
                System.out.println("1. Ver Comentarios");
                System.out.println("2. Ver decisiones de los Revisores");
                System.out.println("3. Salir");
                System.out.println("Ingrese una opción:");
                
                int opcion = sc.nextInt();
                sc.nextLine(); // Consume newline
                System.out.println("-------------------------------------------------");
                
                switch (opcion) {
                    case 1:
                        System.out.println("Los comentarios son los siguientes:");
                        for (Revisor revisor : validarProceso.revisores) {
                            System.out.println("El revisor " + revisor.getNombre());
                            revisarComentarios(validarProceso.articulo);
                            System.out.println("****************************************");
                        }
                        break;
    
                    case 2:
                        System.out.println("Las decisiones de cada Revisor son las siguientes:");
                        for (Revisor revisor : validarProceso.revisores) {
                            System.out.println("El revisor " + revisor.getNombre());
                            desicionRevisores(validarProceso.articulo);
                            System.out.println("****************************************");
                        }
                        validarProceso.decisionFinal();
                        guardarRevision(validarProceso.articulo);
                        notificarInvestigador(validarProceso.articulo, editor);
                        codigoValido = true;
                        System.out.println("Saliendo...");
                        break;
    
                    case 3:
                        codigoValido = true;
                        System.out.println("Saliendo...");
                        break;
    
                    default:
                        System.out.println("Opción no válida");
                        break;
                } 
            } else {
                System.out.println("Artículo no encontrado.\nIntente más tarde.\n**********************************");
                break;
            }
            
        }
        sc.close();
    }
}
    



