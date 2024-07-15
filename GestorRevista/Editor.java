import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Editor representa a un usuario que tiene permisos para gestionar artículos en una revista.
 * Extiende la clase Usuario y añade funcionalidades específicas para los editores.
 */
public class Editor extends Usuario {
    private String journal;  // Revista asociada al editor
    private String usuario;  // Nombre de usuario del editor
    private String contraseña;  // Contraseña del editor

    /**
     * Constructor para crear un Editor con información completa.
     * @param nombre El nombre del editor.
     * @param apellido El apellido del editor.
     * @param correo El correo del editor.
     * @param journal La revista asociada al editor.
     * @param usuario El nombre de usuario del editor.
     * @param contraseña La contraseña del editor.
     */
    public Editor(String nombre, String apellido, String correo, String journal, String usuario, String contraseña) {
        super(nombre, apellido, correo);
        this.journal = journal;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    /**
     * Constructor para crear un Editor con información básica.
     * @param journal La revista asociada al editor.
     * @param usuario El nombre de usuario del editor.
     * @param contraseña La contraseña del editor.
     */
    public Editor(String journal, String usuario, String contraseña) {
        this.journal = journal;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    // Getters y setters

    /**
     * Obtiene la revista asociada al editor.
     * @return La revista del editor.
     */
    public String getJournal() {
        return journal;
    }

    /**
     * Establece la revista asociada al editor.
     * @param journal La nueva revista del editor.
     */
    public void setJournal(String journal) {
        this.journal = journal;
    }

    /**
     * Obtiene el nombre de usuario del editor.
     * @return El nombre de usuario del editor.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario del editor.
     * @param usuario El nuevo nombre de usuario del editor.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del editor.
     * @return La contraseña del editor.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del editor.
     * @param contraseña La nueva contraseña del editor.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Métodos

    /**
     * Método para revisar los comentarios de los revisores.
     * @param articulo El artículo que se está revisando.
     */
    public static void desicionRevisores(Articulo articulo) {
        // Obtiene el estado del artículo
        EstadoAriculo estado = articulo.getEstadoArticulo();

        // Realiza una acción basada en el estado del artículo
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
     * Método para revisar los comentarios de los artículos dejados por un revisor.
     * @param articulo Articulo con sus comentarios.
     */
    public static void revisarComentarios(Articulo articulo) {
        // Imprime los comentarios del artículo
        System.out.println("Dejó el siguiente comentario\n" + articulo.getComentarios());
    }

    /**
     * Método para notificar al investigador del resultado final por correo electrónico.
     * @param articulo El artículo revisado.
     * @param editor El editor que notifica.
     */
    public static void notificarInvestigador(Articulo articulo, Editor editor) {
        // Genera el mensaje de notificación
        String mensaje = "Estimado autor, " + articulo.getDatosAutor().getNombre() + "\n\n";
        mensaje += "Queremos informarle que el artículo con código " + articulo.getCodigo() + " ha sido revisado.\n";
        mensaje += "Su articulo ha sido: " + articulo.getEstadoArticulo() + "\n";
        mensaje += "Comentarios de los revisores:\n";
        mensaje += articulo.getComentarios() + "\n";
        mensaje += "Atentamente,\n";
        mensaje += "Equipo editorial " + editor.journal;

        // Simula el envío de correo electrónico al autor
        System.out.println("**************Generando Notificación**************");
        System.out.println("Para " + articulo.getDatosAutor().getCorreo() + "\nResultado de revisión de artículo\n" + mensaje);
    }

    /**
     * Busca en la lista de procesos el artículo mediante el código.
     * @param listaProcesos Lista de procesos.
     * @param codigo Código del artículo.
     * @return El proceso de gestión de artículo correspondiente al código, o null si no se encuentra.
     */
    private static GestionarArticulo buscarProcesoPorCodigo(ArrayList<GestionarArticulo> listaProcesos, String codigo) {
        // Recorre la lista de procesos para encontrar el artículo con el código dado
        for (GestionarArticulo proceso : listaProcesos) {
            if (proceso.articulo.getCodigo().equals(codigo)) {
                return proceso;
            }
        }
        return null;
    }

    /**
     * Método para guardar los detalles de la revisión en un archivo `revisiones.txt`.
     * @param articulo El artículo revisado.
     */
    public static void guardarRevision(Articulo articulo) {
        // Detalles de la revisión
        String detallesRevision = "Código del artículo: " + articulo.getCodigo() + "\nEstado del artículo: " + articulo.getEstadoArticulo() + "\n" + "Comentarios del revisor:\n" +
                articulo.getComentarios() + "\n";

        // Simula el guardado de la revisión
        System.out.println("Guardando Revisión...\n" + detallesRevision);
    }

    /**
     * Método para manejar las opciones del sistema del editor.
     * @param listaProcesos La lista de procesos.
     * @param editor Editor asignado.
     */
    public static void manejarOpcionesEditor(ArrayList<GestionarArticulo> listaProcesos, Editor editor) {
        Scanner sc = new Scanner(System.in);

        System.out.println("**************OPCIONES DE EDITOR**************");

        boolean codigoValido = false;

        // Solicita el código del artículo
        System.out.println("Ingrese el código del Artículo deseado");
        String codigoIngresado = sc.nextLine();

        // Verifica si el código ingresado es válido
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

                // Maneja la opción ingresada
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
