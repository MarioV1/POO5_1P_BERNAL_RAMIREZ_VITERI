package gestorrevista;

public class Articulo {
    public String codigo;
    public Autor datosAutor;
    public String resumen;
    public String contenido;
    public String palabrasClave;
    public String comentarios;
    public EstadoAriculo estadoAriculo;
    public Articulo(String codigo, Autor datosAutor,String resumen,String contenido,String palabrasClave,String comentarios,EstadoAriculo estadoAriculo){
        this.codigo=codigo;
        this.datosAutor=datosAutor;
        this.resumen=resumen;
        this.contenido=contenido;
        this.comentarios=comentarios;
        this.estadoAriculo=estadoAriculo;

    }

}
