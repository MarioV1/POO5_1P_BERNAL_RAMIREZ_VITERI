package gestorrevista;

import java.util.Random;

public class Articulo {
    //Atributos
    public String codigo;
    public Autor datosAutor;
    public String resumen;
    public String contenido;
    public String palabrasClave;
    public String comentarios;
    public EstadoAriculo estadoAriculo;
    //Constructores
    public Articulo(Autor datosAutor,String resumen,String contenido,String palabrasClave,String comentarios,EstadoAriculo estadoAriculo){
        this.codigo=new Random().ints(48, 122).filter(i -> (i < 58 || i > 64) && (i < 91 || i > 96)).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().replace("-", "a");;
        this.datosAutor=datosAutor;
        this.resumen=resumen;
        this.contenido=contenido;
        this.comentarios=comentarios;
        this.estadoAriculo=estadoAriculo;
    }
    public Articulo(String codigo,Autor datosAutor,String resumen,String contenido,String palabrasClave,String comentarios,EstadoAriculo estadoAriculo){
        this.codigo=codigo;
        this.datosAutor=datosAutor;
        this.resumen=resumen;
        this.contenido=contenido;
        this.comentarios=comentarios;
        this.estadoAriculo=estadoAriculo;
    }


}
