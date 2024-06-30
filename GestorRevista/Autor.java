package gestorrevista;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Autor extends Usuario {
    //Atributos
    private String codigoID;
    private String institucion;
    private String campo;
    
    //Constructores
    public Autor(String nombre, String apellido, String correo, String institucion,String campo){
        super(nombre,apellido,correo);
        this.codigoID=new Random().ints(48, 122).filter(i -> (i < 58 || i > 64) && (i < 91 || i > 96)).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().replace("-", "a");
        this.institucion=institucion;
        this.campo=campo;
    }
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
     */
    public static void someterArt(String archivoArticulos,String archivoUsuarios){
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
        Articulo articulo1=new Articulo(autor1, resumen, contenido, palabrasClave, "", EstadoAriculo.REVISION);
        System.out.println("Desea iniciar el proceso de revision?");
        String respuesta=sc.nextLine();
        if(respuesta.equalsIgnoreCase("si")){
            Articulo.escribirArticulo(archivoArticulos, articulo1);
            Usuario.EscribirUsuario(archivoUsuarios, u);
        }
        else{
            System.out.println("EL ARTICULO NO FUE INGRESADO");
        }
        sc.close();
    }
}
