

import java.util.Random;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;

public class Autor extends Usuario {
    //Atributos
    private String codigoID;
    private String institucion;
    private String campo;
    
    //Constructores
    //Con este constructor se genera  un código alfanumérico aleatorio de 10 caracteres
    public Autor(String nombre, String apellido, String correo, String institucion,String campo){
        super(nombre,apellido,correo);
        this.codigoID=new Random().ints(48, 122).filter(i -> (i < 58 || i > 64) && (i < 91 || i > 96)).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().replace("-", "a");
        this.institucion=institucion;
        this.campo=campo;
    }
    //Este constructor se utiliza en la extracción de Autores de la lista de usuarios para mantener el código generado con la creación del Autor
    public Autor(String nombre, String apellido, String correo,String codigoID, String institucion,String campo){
        super(nombre,apellido,correo);
        this.codigoID=codigoID;
        this.institucion=institucion;
        this.campo=campo;
    }
    public Autor(String codigoID, String institucion,String campo){
        this.codigoID=codigoID;
        this.institucion=institucion;
        this.campo=campo;
    }
    //Getters y Setters
    public String getCodigoID(){
        return this.codigoID;
    }
    public void setCodigoID(String codigoID){
        this.codigoID=codigoID; 
    }
    public String getInstitucion(){
        return this.institucion;
    }
    public void setInstitucion(String institucion){
        this.institucion=institucion;
    }
    public String getCampo(){
        return this.campo;
    }
    public void setCampo(String campo){
        this.campo=campo;
    }
    //Métodos
    /**
     * Recopila los datos del Autor y su artículo para agregarlos al sistema
     * @param archivoArticulos Archivo donde se almacenan y escriben artículos
     * @param archivoUsuarios  Archivo donde se almacenan y escriben los usuarios
     * @param listaGestion Lista donde se agrega el proceso de revisión 
     */
    public static void someterArt(String archivoArticulos,String archivoUsuarios, ArrayList<GestionarArticulo> listaProcesos){
        Scanner sc=new Scanner(System.in);
        System.out.println("**************REGISTRO DE DATOS AUTOR**************");
        System.out.println("Ingrese su nombre:");
        String nombre=sc.nextLine();
        System.out.println("Ingrese su apellido:");
        String apellido=sc.nextLine();
        System.out.println("Ingrese su correo electrónico:");
        String correo=sc.nextLine();
        System.out.println("Ingrese su institución:");
        String institucion=sc.nextLine();
        System.out.println("Ingrese su campo:");
        String campo=sc.nextLine();
        Usuario u=new Autor(nombre, apellido, correo, institucion, campo);
        System.out.println("**************REGISTRO DE DATOS ARTICULO**************");
        System.out.println("Ingrese el resumen:");
        String resumen=sc.nextLine();
        System.out.println("Ingrese el contenido de su articulo:");
        String contenido=sc.nextLine();
        System.out.println("Ingrese las palabras clave:");
        String palabrasClave=sc.nextLine();
        Autor autor1=(Autor) u;
        Articulo articulo1=new Articulo(autor1, resumen, contenido, palabrasClave, " ", EstadoAriculo.REVISION);
        System.out.println("Desea iniciar el proceso de revision?");
        String respuesta=sc.nextLine();
        if(respuesta.equalsIgnoreCase("si")){
            Articulo.escribirArticulo(archivoArticulos, articulo1);
            Usuario.EscribirUsuario(archivoUsuarios, u);
            //Se importa la lista de usuarios para dividir en lista de revisores y editores para la asignación
            ArrayList<Usuario> listaUsuarios=new ArrayList<>(Usuario.obtenerListaUsuarios(archivoUsuarios));
            ArrayList<Revisor> listaRevisores=new ArrayList<>();
            ArrayList<Editor> listaEditores=new ArrayList<>();
            ArrayList<Revisor> revisoresAsignados=new ArrayList<>();
            Random random=new Random();
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getClass()==Revisor.class) {
                    Revisor revisor = (Revisor) usuario;//UPCASTING
                    listaRevisores.add(revisor);

                }
                if (usuario.getClass()==Editor.class) {
                    Editor editor= (Editor) usuario;//UPCASTING
                    listaEditores.add(editor);
                }  
            }
            //Asignación Revisores
            int num1= random.nextInt(listaRevisores.size()-1)+1;
            int num2= random.nextInt(num1);
            Revisor r1=listaRevisores.get(num1);
            Revisor r2=listaRevisores.get(num2);
            revisoresAsignados.add(r1);
            revisoresAsignados.add(r2);
            System.out.println();
            System.out.println("Revisores asignados:");
            System.out.println(r1.nombre+" "+r1.apellido+" - Correo electrónico de notificación enviado a: "+r1.correo);
            System.out.println(r2.nombre+" "+r2.apellido+" - Correo electrónico de notificación enviado a: "+r2.correo);
            //Asignación Editor
            int num=random.nextInt(listaEditores.size());
            Editor e=listaEditores.get(num);
            System.out.println("Editor asignado ");
            System.out.println(e.nombre+" "+e.apellido+" - Correo electrónico de notificación enviado a: "+e.correo);
            //Inicio del proceso
            String fecha=new SimpleDateFormat("dd/MM/yyyy").format(new Date());//Se genera fecha de inicio del proceso en forma de string
            GestionarArticulo gestor=new GestionarArticulo(articulo1, revisoresAsignados, e, fecha);
            listaProcesos.add(gestor);
        }
        else{
            System.out.println("EL ARTICULO NO FUE INGRESADO");
        }
        sc.close();
    }
}
