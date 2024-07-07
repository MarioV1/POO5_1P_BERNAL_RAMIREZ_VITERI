package gestorrevista;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;

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
    /*@Override
    public boolean equals(Editor e){
        if(e!=null && getClass()==e.getClass()){
            
        }
    }*/

    /**
     * Método para asignar un artículo a revisores.
     *
     * @param articulo El artículo a asignar
     * @param revisores Lista de revisores a asignar
     * @param listaGestion Lista donde se guardará el proceso de gestión del artículo
     */


    


    public void asignarArticulo(Articulo articulo, ArrayList<Revisor> revisores, ArrayList<GestionarArticulo> listaGestion) {
        GestionarArticulo gestionArticulo = new GestionarArticulo(articulo, revisores, this, "Fecha de asignación");
        listaGestion.add(gestionArticulo);
        System.out.println("Artículo asignado correctamente a revisores.");
    }

    /**
     * Método para enviar un artículo a publicación una vez revisado y aceptado.
     *
     * @param articulo El artículo a enviar a publicación
     */
    public void enviarArticuloAPublicacion(Articulo articulo) {
        if (articulo.getEstadoArticulo() == EstadoAriculo.ACEPTADO) {
            // Lógica para enviar el artículo a la sección de publicación
            System.out.println("Artículo enviado a publicación con éxito.");
        } else {
            System.out.println("El artículo no puede ser enviado a publicación porque no ha sido aceptado.");
        }
    }

    /**
     * Método para rechazar un artículo que no cumple con los criterios de la revista.
     *
     * @param articulo El artículo a rechazar
     */
    public void rechazarArticulo(Articulo articulo) {
        if (articulo.getEstadoArticulo() == EstadoAriculo.REVISION) {
            articulo.setEstadoAriculo(EstadoAriculo.RECHAZADO);
            System.out.println("Artículo rechazado.");
        } else {
            System.out.println("El artículo no puede ser rechazado en este momento.");
        }
    }

    /**
     * Método para notificar a los autores sobre el estado de sus artículos.
     *
     * @param listaGestion Lista de artículos gestionados
     */
    public void notificarAutores(ArrayList<GestionarArticulo> listaGestion) {
        for (GestionarArticulo gestion : listaGestion) {
            System.out.println("Notificando al autor: " + gestion.getArticulo().getDatosAutor().getCorreo());
            // Lógica para enviar notificación por correo u otro medio
        }
        System.out.println("Notificaciones enviadas a todos los autores.");
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



