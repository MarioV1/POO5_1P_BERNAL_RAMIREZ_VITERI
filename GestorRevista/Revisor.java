package gestorrevista;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    }
    public Revisor(String especialidad, String usuario, String contraseña, int numArticulos){
        this.especialidad = especialidad;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.numArticulos = numArticulos;
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
     * Muestra los detalles de un artículo seleccionado.
     * @param indice El índice del artículo en la lista de artículos asignados.
     */
    public void verDetalleArticulo(int indice) {
        if (indice >= 0 && indice < articulosAsignados.size()) {
            Articulo articulo = articulosAsignados.get(indice);
            System.out.println("Código: " + articulo.getCodigo());
            System.out.println("Autor: " + articulo.getDatosAutor().getNombre() + " " + articulo.getDatosAutor().getApellido());
            System.out.println("Resumen: " + articulo.getResumen());
            System.out.println("Contenido: " + articulo.getContenido());
            System.out.println("Comentarios: " + articulo.getComentarios());
            System.out.println("Estado: " + articulo.getEstadoArticulo());
        } else {
            System.out.println("Índice de artículo inválido.");
        }
    }
    /**
     * Comenta un artículo.
     * @param indice El índice del artículo en la lista de artículos asignados.
     * @param comentario El comentario a añadir.
     */
    public void comentarArticulo(int indice, String comentario) {
        if (indice >= 0 && indice < articulosAsignados.size()) {
            Articulo articulo = articulosAsignados.get(indice);
            articulo.setComentarios(comentario);
            System.out.println("Comentario añadido al artículo.");
        } else {
            System.out.println("Índice de artículo inválido.");
        }
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
    public void aceptarArticulo(int indice) {
    if (indice >= 0 && indice < articulosAsignados.size()) {
        Articulo articulo = articulosAsignados.get(indice);
        articulo.addRevisorAprobado(this);
        if (articulo.getRevisoresAprobados().size() >= 2) {
            articulo.setEstadoAriculo(EstadoAriculo.ACEPTADO);
            System.out.println("Artículo aceptado.");
        } else {
            System.out.println("Artículo aprobado por un revisor. Se necesita una aprobación más.");
        }
    } else {
        System.out.println("Índice de artículo inválido.");
    }
}

    /**
     * Rechaza un artículo para revisión y actualiza el estado del artículo.
     *
     * @param articulo el artículo a rechazar
     */
    public void rechazarArticulo(int indice) {
        if (indice >= 0 && indice < articulosAsignados.size()) {
            Articulo articulo = articulosAsignados.get(indice);
            articulo.setEstadoAriculo(EstadoAriculo.RECHAZADO);
            System.out.println("Artículo rechazado.");
        } else {
            System.out.println("Índice de artículo inválido.");
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
