

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
     * Método para revisar los comentarios de los revisores y tomar una decisión sobre la publicación del artículo.
     * @param articulo El artículo que se está revisando
     * @param comentarios Comentarios adicionales a los revisores
     */
    public static void revisarComentarios(Articulo articulo, String comentarios) {
        EstadoAriculo estado = articulo.getEstadoArticulo();

        switch (estado) {
            case ACEPTADO:
                System.out.println("Artículo aceptado para publicación.");
                notificarInvestigador(articulo, comentarios + "\nArtículo aceptado para publicación.");
                guardarRevision(articulo, comentarios + "\nArtículo aceptado para publicación.");
                break;

            case RECHAZADO:
                System.out.println("Artículo rechazado para publicación.");
                notificarInvestigador(articulo, comentarios + "\nArtículo rechazado para publicación.");
                guardarRevision(articulo, comentarios + "\nArtículo rechazado para publicación.");
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
     * Método para notificar al investigador del resultado final por correo electrónico.
     * @param articulo El artículo revisado
     * @param comentarios Los comentarios de los revisores
     */
    public static void notificarInvestigador(Articulo articulo, String comentarios) {
        String mensaje = "Estimado autor,\n\n";
        mensaje += "Queremos informarle que el artículo con código " + articulo.getCodigo() + " ha sido revisado.\n";
        mensaje += "Comentarios de los revisores:\n";
        mensaje += comentarios + "\n";
        mensaje += "Atentamente,\n";
        mensaje += "Equipo editorial";

        // Lógica para enviar correo electrónico al autor
        System.out.println(articulo.getDatosAutor().getCorreo() + "Resultado de revisión de artículo" + mensaje);
    }

    /**
     * Método para guardar los detalles de la revisión en un archivo `revisiones.txt`.
     * @param articulo El artículo revisado
     * @param comentarios Los comentarios de los revisores
     */
    public static void guardarRevision(Articulo articulo, String comentarios) {
        String detallesRevision = "Código del artículo: " + articulo.getCodigo() + "\n";
        detallesRevision += "Comentarios de los revisores:\n";
        detallesRevision += comentarios + "\n";

        // Lógica para guardar los detalles de la revisión en el archivo `revisiones.txt`
        ManejoArchivos.escribirArchivo("revisiones.txt", detallesRevision);
    }
   

    // Método toString para representar el objeto como cadena
    @Override
    public String toString() {
        return "Editor{" +
                "nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", correoElectronico='" + getCorreo() + '\'' +
                ", journal='" + journal + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}



