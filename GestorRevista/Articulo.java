package gestorrevista;

import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Articulo {
    //Atributos
    public String codigo;
    public Autor datosAutor;
    public String resumen;
    public String contenido;
    public String palabrasClave;
    public String comentarios;
    public EstadoAriculo estadoAriculo;
    private ArrayList<Revisor> revisoresAprobados;

    //Constructores
    //Con este constructor se genera  un código alfanumérico aleatorio de 10 caracteres
    public Articulo(Autor datosAutor,String resumen,String contenido,String palabrasClave,String comentarios,EstadoAriculo estadoAriculo, ArrayList<Revisor> revisoresAprobados){
        this.codigo=new Random().ints(48, 122).filter(i -> (i < 58 || i > 64) && (i < 91 || i > 96)).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().replace("-", "a");;
        this.datosAutor=datosAutor;
        this.resumen=resumen;
        this.contenido=contenido;
        this.comentarios=comentarios;
        this.estadoAriculo=estadoAriculo;
        this.revisoresAprobados=revisoresAprobados;

    }
    //Este constructor se utiliza en la extracción de Autores de la lista de usuarios para mantener el código generado con la creación del Artículo
    public Articulo(String codigo,Autor datosAutor,String resumen,String contenido,String palabrasClave,String comentarios,EstadoAriculo estadoAriculo,ArrayList<Revisor> revisoresAprobados){
        this.codigo=codigo;
        this.datosAutor=datosAutor;
        this.resumen=resumen;
        this.contenido=contenido;
        this.comentarios=comentarios;
        this.estadoAriculo=estadoAriculo;
        this.revisoresAprobados=revisoresAprobados;
        

    }
    //Métodos
    /**
     * Escribe un articulo en un archivo de texto con determinado formato
     * @param nombreArchivo Archivo en donde se escribirá el artículo
     * @param articulo Artículo a escribirse
     */
    public static void escribirArticulo(String nombreArchivo, Articulo articulo){
        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            //Se escriben los atributos del Artículo separados por '-'
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(articulo.codigo+"-");
            bw.write(articulo.datosAutor.nombre+"-");
            bw.write(articulo.datosAutor.apellido+"-");
            bw.write(articulo.datosAutor.correo+"-");
            bw.write(articulo.datosAutor.getCodigoID()+"-");
            bw.write(articulo.datosAutor.getInstitucion()+"-");
            bw.write(articulo.datosAutor.getCampo()+"-");
            bw.write(articulo.resumen+"-");
            bw.write(articulo.contenido+"-");
            bw.write(articulo.palabrasClave+"-");
            bw.write(articulo.comentarios+"-");
            bw.write(articulo.estadoAriculo.toString()+"\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    /**
     * Lee un archivo con los artículos guardados
     * @param archivo Archivo a leer
     * @return Un ArrayList con todos los artículos guardados
     */
    public static ArrayList<Articulo> obtenerListaArticulos (String archivo){
        ArrayList<String> lineas=ManejoArchivos.LeeFichero(archivo);  
        ArrayList<Articulo>  listaArticulos= new ArrayList<>();
        for(String s:lineas){
            String [] datos=s.split("-");
            String codigo=datos[0];
            String nombreAutor=datos[1];
            String apellidoAutor=datos[2];
            String correoAutor=datos[3];
            String codigoAutor=datos[4];
            String institucionAutor=datos[5];
            String campoAutor=datos[6];
            String resumen=datos[7];
            String contenido=datos[8];
            String palabrasClave=datos[9];
            String comentarios=datos[10];
            EstadoAriculo estadoAriculo=EstadoAriculo.valueOf(datos[11]);
            Autor autor=new Autor(nombreAutor,apellidoAutor,correoAutor,codigoAutor,institucionAutor,campoAutor);
            Articulo articulo=new Articulo(codigo, autor, resumen, contenido, palabrasClave, comentarios, estadoAriculo, new ArrayList<>());
            listaArticulos.add(articulo);    
        }
        return listaArticulos;
    }    
    
    /**
     * Getters y setters respectivos de cada atributo
     *
     */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Autor getDatosAutor() {
        return datosAutor;
    }

    public void setDatosAutor(Autor datosAutor) {
        this.datosAutor = datosAutor;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public EstadoAriculo getEstadoArticulo() {
        return estadoAriculo;
    }

    public void setEstadoAriculo(EstadoAriculo estadoAriculo) {
        this.estadoAriculo = estadoAriculo;
    }
    public ArrayList<Revisor> getRevisoresAprobados() {
        return revisoresAprobados;
    }

    public void addRevisorAprobado(Revisor revisor) {
        if (!revisoresAprobados.contains(revisor)) {
            revisoresAprobados.add(revisor);
        }
    }



    @Override
    public String toString() {
        return "Articulo{" +
                "codigo='" + codigo + '\'' +
                ", datosAutor=" + datosAutor +
                ", resumen='" + resumen + '\'' +
                ", contenido='" + contenido + '\'' +
                ", palabrasClave='" + palabrasClave + '\'' +
                ", comentarios='" + comentarios + '\'' +
                ", estadoAriculo=" + estadoAriculo +
                '}';
    }      
}
