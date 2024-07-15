
import java.util.ArrayList;
import java.util.Scanner;

public class Revisor extends Usuario {
    private String especialidad;
    private String usuario;
    private String contraseña;
    private int numArticulos;

    
    //Constructores
    public Revisor(String nombre, String apellido, String correo, String especialidad, String usuario, String contraseña, int numArticulos) {
        super(nombre, apellido, correo);
        this.especialidad = especialidad;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.numArticulos = numArticulos;
    }

    //Getters and Setters

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getNumArticulos() {
        return numArticulos;
    }

    public void setNumArticulos(int numArticulos) {
        this.numArticulos = numArticulos;
    }

    /**
     * Comenta un articulo
     * @param articulo Articulo que se comenta
     * @param comentario Comentario 
     */

    public void comentarArticulo(Articulo articulo, String comentario) {
        articulo.setComentarios(comentario);
    }

    /**
     * Acepta un articulo
     * @param gestion Articulo que se gestiona
     */

    public void aceptarArticulo(GestionarArticulo gestion) {
        gestion.incrementarAceptaciones();
        System.out.println("Articulo Aceptado");
    }

    
    /**
     * Rechaza un articulo
     * @param gestion Archivo que se gestiona
     */

    public void rechazarArticulo(GestionarArticulo gestion) {
        gestion.incrementarRechazos();
        System.out.println("Articulo Rechazado");
    }


    /**
     * Metodo para manejar las opciones del revisor
     * @param ListaProcesos Es la lista de los procesos
     * @param revisor Es el revisor que maneja las opciones
     */

    public static void manejarOpcionesRevisor(ArrayList<GestionarArticulo> listaProcesos, Revisor revisor) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("**************OPCIONES DE REVISOR**************");
            System.out.println("1. Ver artículos asignados");
            System.out.println("2. Ver detalles de un artículo");
            System.out.println("3. Comentar un artículo");
            System.out.println("4. Aceptar un artículo");
            System.out.println("5. Rechazar un artículo");
            System.out.println("6. Salir");
            System.out.println("Ingrese una opción:");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (opcion == 6) {
                break;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Artículos asignados:");
                    for (GestionarArticulo proceso : listaProcesos) {
                        if (proceso.revisores.contains(revisor)) {
                            System.out.println("Código: " + proceso.articulo.getCodigo());
                            System.out.println("Título: " + proceso.articulo.getResumen());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el código del artículo:");
                    String codigo = sc.nextLine();
                    GestionarArticulo proceso = buscarProcesoPorCodigo(listaProcesos, codigo, revisor);
                    if (proceso != null) {
                        Articulo articulo = proceso.articulo;
                        System.out.println("Resumen: " + articulo.getResumen());
                        System.out.println("Contenido: " + articulo.getContenido());
                        System.out.println("Comentarios: " + articulo.getComentarios());
                        System.out.println("Estado: " + articulo.getEstadoArticulo());
                    } else {
                        System.out.println("Artículo no encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el código del artículo:");
                    codigo = sc.nextLine();
                    proceso = buscarProcesoPorCodigo(listaProcesos, codigo, revisor);
                    if (proceso != null) {
                        System.out.println("Ingrese su comentario:");
                        String comentario = sc.nextLine();
                        revisor.comentarArticulo(proceso.articulo, comentario);
                    } else {
                        System.out.println("Artículo no encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el código del artículo:");
                    codigo = sc.nextLine();
                    proceso = buscarProcesoPorCodigo(listaProcesos, codigo, revisor);
                    if (proceso != null) {
                        revisor.aceptarArticulo(proceso);
                    } else {
                        System.out.println("Artículo no encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el código del artículo:");
                    codigo = sc.nextLine();
                    proceso = buscarProcesoPorCodigo(listaProcesos, codigo, revisor);
                    if (proceso != null) {
                        revisor.rechazarArticulo(proceso);
                    } else {
                        System.out.println("Artículo no encontrado.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        sc.close();
    }

    
    /**
     * Busca en la lista de procesos el articulo mediante el codigo
     * @param listaProcesos Lista de procesos
     * @param codigo Codigo del articulo
     */

    private static GestionarArticulo buscarProcesoPorCodigo(ArrayList<GestionarArticulo> listaProcesos, String codigo, Revisor revisor) {
        for (GestionarArticulo proceso : listaProcesos) {
            System.out.println(proceso);
            if (proceso.articulo.getCodigo().equals(codigo) && proceso.revisores.contains(revisor)) {
                return proceso;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Revisor [especialidad=" + especialidad + ", usuario=" + usuario + ", nombre=" + nombre + ", contraseña="
                + contraseña + ", apellido=" + apellido + ", numArticulos=" + numArticulos + ", correo=" + correo
                + ", getUsuario()=" + getUsuario() + "]";
    }

    
}