package gestorrevista;

import java.util.ArrayList;

public class GestionarArticulo {
    //Variables de instancia
    public Articulo articulo;
    public ArrayList<Revisor> revisor;
    public Editor editor;
    public static int id;
    public String fecha; 

    //constructor
    public GestionarArticulo(){
        
    }

    public GestionarArticulo(Articulo articulo, ArrayList<Revisor> revisor, Editor editor, String fecha){
        this.articulo = articulo;
        this.revisor = revisor;
        this.editor = editor;
        id++;
        this.fecha = fecha;
    }


}
