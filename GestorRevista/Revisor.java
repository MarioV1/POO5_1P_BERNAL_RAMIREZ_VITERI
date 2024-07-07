package gestorrevista;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Revisor extends Usuario{
    //Variables de instancia
    private String especialidad;
    private String usuario;
    private String contraseña;
    private int numArticulos;
    
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
    public void aceptarArticulo(Articulo articulo) {
        if (articulo.getEstadoArticulo() == EstadoAriculo.REVISION) {
            articulo.addRevisorAprobado(this);
            if (articulo.getRevisoresAprobados().size() >= 2) {
                articulo.setEstadoAriculo(EstadoAriculo.ACEPTADO);
            }
        }
    }

    /**
     * Rechaza un artículo para revisión y actualiza el estado del artículo.
     *
     * @param articulo el artículo a rechazar
     */
    public void rechazarArticulo(Articulo articulo) {
        if (articulo.getEstadoArticulo() == EstadoAriculo.REVISION) {
            articulo.setEstadoAriculo(EstadoAriculo.RECHAZADO);
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
