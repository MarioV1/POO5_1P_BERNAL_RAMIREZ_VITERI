
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class GestionarArticulo {
    //Variables de instancia
    public Articulo articulo;
    public ArrayList<Revisor> revisores;
    public Editor editor;
    public int id;
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
        this.aceptaciones = 0;
        this.rechazos = 0;
    }
    public GestionarArticulo(Articulo articulo, ArrayList<Revisor> revisor, Editor editor,int id, String fecha, int aceptaciones, int rechazados){
        this.articulo = articulo;
        this.revisores = revisor;
        this.editor = editor;
        this.id=id;
        this.fecha = fecha;
        this.aceptaciones = aceptaciones;
        this.rechazos = rechazados;
    }
    /**
     * Método para que un revisor acepte el artículo.
     *
     * @param revisor el revisor que acepta el artículo
     */
    public void incrementarAceptaciones() {
        aceptaciones++;
        if (aceptaciones >= 2) {
            articulo.setEstadoAriculo(EstadoAriculo.ACEPTADO);
        }
    }

    /**
     * Método para que un revisor rechaza el artículo.
     *
     * @param revisor el revisor que rechaza el artículo
     */
    
     public void incrementarRechazos() {
        rechazos++;
        if (rechazos >= 2) {
            articulo.setEstadoAriculo(EstadoAriculo.RECHAZADO);
        }
    }

    /**
     * Método para que un editor acepte o rechace el artículo.
     * 
     * @param editor el editor que tiene la desición final
     */

    public void decisionFinal(){
        
        Random rd = new Random();
        int decision = rd.nextInt(2);

        if (decision == 0){
            articulo.setEstadoAriculo(EstadoAriculo.RECHAZADO);
        }else{
            articulo.setEstadoAriculo(EstadoAriculo.ACEPTADO);
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

    /*public static int getId() {
        return id;
    }

    public static void setId(int id) {
        GestionarArticulo.id = id;
    }*/

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

    /**
     * Método para escribir el proceso de revision
     */
    public static void escribirProceso(String nombreArchivo, GestionarArticulo proceso){
        FileWriter fichero = null;
        BufferedWriter bw = null;
        try {
            //Se escriben los atributos del Artículo separados por '-'
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(proceso.articulo.codigo+"-");
            bw.write(proceso.articulo.datosAutor.nombre+"-");
            bw.write(proceso.articulo.datosAutor.apellido+"-");
            bw.write(proceso.articulo.datosAutor.correo+"-");
            bw.write(proceso.articulo.datosAutor.getCodigoID()+"-");
            bw.write(proceso.articulo.datosAutor.getInstitucion()+"-");
            bw.write(proceso.articulo.datosAutor.getCampo()+"-");
            bw.write(proceso.articulo.resumen+"-");
            bw.write(proceso.articulo.contenido+"-");
            bw.write(proceso.articulo.palabrasClave+"-");
            bw.write(proceso.articulo.comentarios+"-");
            bw.write(proceso.articulo.estadoAriculo.toString()+"-");
            for (Revisor ra :proceso.revisores) {
                bw.write(ra.nombre+"-");
                bw.write(ra.apellido+"-");
                bw.write(ra.correo+"-");
                bw.write(ra.getEspecialidad()+"-");
                bw.write(ra.getUsuario()+"-");
                bw.write(ra.getContraseña()+"-");
                bw.write(ra.getNumArticulos()+"-");
            }
            bw.write(proceso.editor.nombre+"-");
            bw.write(proceso.editor.apellido+"-");
            bw.write(proceso.editor.correo+"-");
            bw.write(proceso.editor.getJournal()+"-");
            bw.write(proceso.editor.getUsuario()+"-");
            bw.write(proceso.editor.getContraseña()+"-");
            bw.write(proceso.id+"-");
            bw.write(proceso.fecha+"-");
            bw.write(proceso.getAceptaciones()+"-");
            bw.write(proceso.getRechazos()+"\n");   
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
     * Método para obtener una lista con procesos de revision    
     */
    public static ArrayList<GestionarArticulo> obtenerListaProcesos(String nombreArchivo){
        ArrayList<String> lineas=ManejoArchivos.LeeFichero(nombreArchivo); 
        ArrayList<GestionarArticulo> listaProcesos=new ArrayList<>();
        for(String s:lineas){
            ArrayList<Revisor> revisores=new ArrayList<>();
            String [] datos=s.split("-");
            String codigoArt= datos[0];
            String nombreAutor= datos[1];
            String apellidoAutor=datos[2];
            String correoAutor=datos[3];
            String idAutor=datos[4];
            String instiAutor=datos[5];
            String campoAutor=datos[6];
            Autor autor=new Autor(nombreAutor,apellidoAutor,correoAutor,instiAutor,campoAutor);
            String resumenArt=datos[7];
            String contenidoArt=datos[8];
            String palabrasClaveArt=datos[9];
            String comentariosArt=datos[10];
            EstadoAriculo estadoAriculo=EstadoAriculo.valueOf(datos[11]);
            Articulo articulo=new Articulo(codigoArt, autor, resumenArt, contenidoArt, palabrasClaveArt, comentariosArt, estadoAriculo);
            String nombreR1=datos[12];
            String apellidoR1=datos[13];
            String correoR1=datos[14];
            String especialidadR1=datos[15];
            String usuarioR1=datos[16];
            String contraR1=datos[17];
            int numArtR1=Integer.valueOf(datos[18]);
            revisores.add(new Revisor(nombreR1, apellidoR1, correoR1, especialidadR1, usuarioR1, contraR1, numArtR1));
            String nombreR2=datos[19];
            String apellidoR2=datos[20];
            String correoR2=datos[21];
            String especialidadR2=datos[22];
            String usuarioR2=datos[23];
            String contraR2=datos[24];
            int numArtR2=Integer.valueOf(datos[25]);
            revisores.add(new Revisor(nombreR2, apellidoR2, correoR2, especialidadR2, usuarioR2, contraR2, numArtR2));
            String nombreEditor=datos[26];
            String apellidoEditor=datos[27];
            String correoEditor=datos[28];
            String journal=datos[29];
            String usuarioEditor=datos[30];
            String contraEditor=datos[31];
            Editor editor=new Editor(nombreEditor, apellidoEditor, correoEditor, journal, usuarioEditor, contraEditor);
            int idProceso=Integer.valueOf(datos[32]);
            String fecha=datos[33];
            int aceptaciones=Integer.valueOf(datos[34]);
            int rechazos=Integer.valueOf(datos[35]);
            GestionarArticulo proceso= new  GestionarArticulo(articulo, revisores, editor, idProceso, fecha, aceptaciones, rechazos);
            listaProcesos.add(proceso);
        }
        return listaProcesos;
    }
    }


