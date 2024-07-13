

import java.util.ArrayList;
import java.util.Scanner;

public class Revisor extends Usuario{
    //Variables de instancia
    private String especialidad;
    private String usuario;
    private String contraseña;
    private int numArticulos;
    private ArrayList<Articulo> articulosAsignados;
    
    //Constructor
    public Revisor(String nombre, String apellido, String correo,String especialidad, String usuario, String contraseña, int numArticulos){
        super(nombre,apellido,correo);
        this.especialidad = especialidad;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.numArticulos = numArticulos;
        this.articulosAsignados= new ArrayList<>();
    }
    public Revisor(String especialidad, String usuario, String contraseña, int numArticulos){
        this.especialidad = especialidad;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.numArticulos = numArticulos;
        this.articulosAsignados= new ArrayList<>();
    }

    


    //getters y setters
    public String getEspecialidad(){
        return especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
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

    public int getNumArticulos(){
        return numArticulos;
    }

    public void setNumArticulos(int numArticulos){
        this.numArticulos = numArticulos;
    }
    public ArrayList<Articulo> getArticulosAsignados() {
        return articulosAsignados;
    }
    /**
     * Añade un artículo a la lista de artículos asignados al revisor.
     * @param articulo El artículo a añadir.
     */
    
    public void asignarArticulo(Articulo articulo) {
        articulosAsignados.add(articulo);
    }
    /**
     * Muestra la lista de artículos asignados al revisor.
     */
    public void verArticulosAsignados() {
        System.out.println("Artículos asignados:");
        for (int i = 0; i < articulosAsignados.size(); i++) {
            System.out.println((i + 1) + ". " + articulosAsignados.get(i).getCodigo() + " - " + articulosAsignados.get(i).getResumen());
        }
    }
    /**
     * Comenta un artículo.
     * @param indice El índice del artículo en la lista de artículos asignados.
     * @param comentario El comentario a añadir.
     */
    public void comentarArticulo(Articulo articulo, String comentario) {
        articulo.setComentarios(comentario);
    }



    /**
     * Metodo que incrementa el número de artículos que ha revisado el revisor en uno.
     */
    public void incrementarArticulosRevisados() {
        this.numArticulos++;
    }

    /**
     * Acepta un artículo para revisión y actualiza el estado del artículo.
     *
     * @param articulo el artículo a aceptar
     */
    public void aceptarArticulo(GestionarArticulo gestion) {
        gestion.incrementarAceptaciones();
        System.out.println("Articulo Aceptado");
    }



    /**
     * Rechaza un artículo para revisión y actualiza el estado del artículo.
     *
     * @param articulo el artículo a rechazar
     */
    public void rechazarArticulo(GestionarArticulo gestion) {
        gestion.incrementarRechazos();
        System.out.println("Articulo Rechazado");
    }


    // Método para manejar opciones del revisor

    public static void manejarOpcionesRevisor(String archivoUsuarios, String archivoArticulos, Revisor revisor, ArrayList<GestionarArticulo> listaGestion) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Articulo> articulos = Articulo.obtenerListaArticulos(archivoArticulos);

        while (true) {
            System.out.println("************** OPCIONES DE REVISOR **************");
            System.out.println("1. Ver artículos asignados");
            System.out.println("2. Comentar artículo");
            System.out.println("3. Aceptar artículo");
            System.out.println("4. Rechazar artículo");
            System.out.println("5. Salir");

            int opcion = sc.nextInt();
            sc.nextLine(); // consumir la nueva línea

            switch (opcion) {
                case 1:
                    for (Articulo articulo : revisor.getArticulosAsignados()) {
                        System.out.println("Código: " + articulo.getCodigo());
                        System.out.println("Resumen: " + articulo.getResumen());
                        System.out.println("Estado: " + articulo.getEstadoArticulo());
                        System.out.println("------------------------------");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el código del artículo:");
                    String codigo = sc.nextLine();
                    for (Articulo articulo : revisor.getArticulosAsignados()) {
                        if (articulo.getCodigo().equals(codigo)) {
                            System.out.println("Ingrese su comentario:");
                            String comentario = sc.nextLine();
                            revisor.comentarArticulo(articulo, comentario);
                            Articulo.escribirArticulo(archivoArticulos, articulo);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el código del artículo:");
                    codigo = sc.nextLine();
                    for (GestionarArticulo gestion : listaGestion) {
                        if (gestion.getArticulo().getCodigo().equals(codigo) && revisor.getArticulosAsignados().contains(gestion.getArticulo())) {
                            revisor.aceptarArticulo(gestion);
                            Articulo.escribirArticulo(archivoArticulos, gestion.getArticulo());
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el código del artículo:");
                    codigo = sc.nextLine();
                    for (GestionarArticulo gestion : listaGestion) {
                        if (gestion.getArticulo().getCodigo().equals(codigo) && revisor.getArticulosAsignados().contains(gestion.getArticulo())) {
                            revisor.rechazarArticulo(gestion);
                            Articulo.escribirArticulo(archivoArticulos, gestion.getArticulo());
                            break;
                        }
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    


    @Override
    public String toString() {
        return "Revisor{" +
                "nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", correoElectronico='" + getCorreo() + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", numArticulos=" + numArticulos +
                '}';
    }



}
