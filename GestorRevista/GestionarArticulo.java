package GestorRevista;

import java.util.ArrayList;

public class GestionarArticulo {
    //Variables de instancia
    public Articulo articulo;
    public ArrayList<Revisor> revisor;
    public Editor editor;
    public int id;
    public String fecha; 

    //constructor

    public GestionarArticulo(Articulo articulo, ArrayList<Revisor> revisor, Editor editor, int id, String fecha){
        this.articulo = articulo;
        this.revisor = revisor;
        this.editor = editor;
        this.id = id;
        this.fecha = fecha;
    }


}
