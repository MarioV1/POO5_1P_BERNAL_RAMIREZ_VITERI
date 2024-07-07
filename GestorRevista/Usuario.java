package gestorrevista;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Usuario {
    //Atributos
    protected String nombre;
    protected String apellido;
    protected String correo;
    //Constructor 
    public Usuario(){
        this.nombre="";
        this.apellido="";
        this.correo="";
    }
    public Usuario(String nombre, String apellido, String correo){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
    }
    //Métodos 
    /**
     * @param nombreArchivo Nombre.extension del archivo que se desea leer
     * @param usuario Usuario que se va escribir en el archivo (Debe ser obligatoriamente generado con downcasting)
     */
    public static void EscribirUsuario(String nombreArchivo, Usuario usuario) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            if(usuario.getClass()==Autor.class){
                Autor a= (Autor) usuario;//UPCASTING
                bw.write(a.nombre+"-");
                bw.write(a.apellido+"-");
                bw.write(a.correo+"-");
                bw.write(a.getCodigoID()+"-");
                bw.write(a.getInstitucion()+"-");
                bw.write(a.getCampo()+"-");
                bw.write("Autor"+"\n");
            }
            if(usuario.getClass()==Editor.class){
                Editor e=(Editor) usuario;//UPCASTING
                bw.write(e.nombre+"-");
                bw.write(e.apellido+"-");
                bw.write(e.correo+"-");
                bw.write(e.getJournal()+"-");
                bw.write(e.getUsuario()+"-");
                bw.write(e.getContraseña()+"\n");
                
            }
            if(usuario.getClass()==Revisor.class){
                Revisor r=(Revisor) usuario;//UPCASTING
                bw.write(r.nombre+"-");
                bw.write(r.apellido+"-");
                bw.write(r.correo+"-");
                bw.write(r.getEspecialidad()+"-");
                bw.write(r.getUsuario()+"-");
                bw.write(r.getContraseña()+"-");
                bw.write(String.valueOf(r.getNumArticulos())+"-");
                bw.write("Revisor"+"\n");
            }

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
     * Este método detecta el tipo de usuario en cada línea, lo genera con downcasting y lo agrega a una ArrayList
     * @param archivo Archivo.extension que se va leer.
     * @return ArrayList<Usuario> Una ArrayList con todos los usuarios que pertenecen a ese documento.
     */
     public static ArrayList<Usuario> obtenerListaUsuarios (String archivo){
        ArrayList<String> lineas=ManejoArchivos.LeeFichero(archivo);  
        ArrayList<Usuario>  listaUsuarios= new ArrayList<>();
        for(String s:lineas){
            String [] datos=s.split("-");
            if(datos.length==7){
                String nombre=datos[datos.length-7];
                String apellido=datos[datos.length-7];
                String correo=datos[datos.length-5];
                String codigoID=datos[datos.length-4];
                String institucion=datos[datos.length-3];
                String campo=datos[datos.length-2];
                Usuario u=new Autor(nombre,apellido,correo,codigoID,institucion,campo);//DOWNCASTING
                listaUsuarios.add(u);
            }
            if (datos.length==6) {
                String nombre=datos[datos.length-6];
                String apellido=datos[datos.length-4];
                String correo=datos[datos.length-4];
                String journal=datos[datos.length-3];
                String usuario=datos[datos.length-2];
                String contraseña=datos[datos.length-1];
                Usuario u=new Editor(nombre, apellido, correo, journal, usuario, contraseña);//DOWNCASTING
                listaUsuarios.add(u);
            }
            if (datos.length==8) {
                String nombre=datos[datos.length-8];
                String apellido=datos[datos.length-7];
                String correo=datos[datos.length-6];
                String especialidad=datos[datos.length-5];
                String usuario=datos[datos.length-4];
                String contraseña=datos[datos.length-3];
                int numArticulos=Integer.valueOf(datos[datos.length-2]);
                Usuario u=new Revisor(nombre, apellido, correo,especialidad, usuario, contraseña, numArticulos);//DOWNCASTING
                listaUsuarios.add(u);
            }    
        }
        return listaUsuarios;
        }

    /**
     * Respectivos getters y setters
     *
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
