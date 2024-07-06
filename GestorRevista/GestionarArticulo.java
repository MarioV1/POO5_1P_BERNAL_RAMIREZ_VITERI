package gestorrevista;

import java.util.ArrayList;

public class GestionarArticulo {
    //Variables de instancia
    public Articulo articulo;
    public ArrayList<Revisor> revisores;
    public Editor editor;
    public static int id;
    public String fecha; 
    private int aceptaciones;
    private int rechazos;

    //constructor
    public GestionarArticulo(){
        
    }

    public GestionarArticulo(Articulo articulo, ArrayList<Revisor> revisor, Editor editor, String fecha){
        this.articulo = articulo;
        this.revisores = revisor;
        this.editor = editor;
        id++;
        this.fecha = fecha;
    }
    /**
     * Método para que un revisor acepte el artículo.
     *
     * @param revisor el revisor que acepta el artículo
     */
    public void aceptarArticulo(Revisor revisor) {
        if (revisores.contains(revisor)) {
            aceptaciones++;
            revisarEstadoArticulo();
        }

    /**
     * Método para que un revisor rechaza el artículo.
     *
     * @param revisor el revisor que rechaza el artículo
     */
    }
    public void rechazarArticulo(Revisor revisor) {
        if (revisores.contains(revisor)) {
            rechazos++;
            revisarEstadoArticulo();
        }
    }
     /**
     * Revisa el estado del artículo y lo actualiza si ha sido aceptado o rechazado por al menos dos revisores.
     */
    private void revisarEstadoArticulo() {
        if (aceptaciones >= 2) {
            articulo.setEstadoAriculo(EstadoAriculo.ACEPTADO);
        } else if (rechazos >= 2) {
            articulo.setEstadoAriculo(EstadoAriculo.RECHAZADO);
        }
        
    }
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public ArrayList<Revisor> getRevisores() {
        return revisores;
    }

    public void setRevisores(ArrayList<Revisor> revisores) {
        this.revisores = revisores;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        GestionarArticulo.id = id;
    }

    public int getAceptaciones() {
        return aceptaciones;
    }

    public void setAceptaciones(int aceptaciones) {
        this.aceptaciones = aceptaciones;
    }

    public int getRechazos() {
        return rechazos;
    }

    public void setRechazos(int rechazos) {
        this.rechazos = rechazos;
    }




}
