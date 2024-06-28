package gestorrevista;

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
    public void agregarUsuario(Usuario usuario){
        ManejoArchivos.EscribirUsuario("Usuarios.txt", usuario);
    }
    public static ArrayList<Usuario> obtenerUsuarios(){
        ArrayList<ArrayList<String>> datos = ManejoArchivos.obtenerDatos("Usuarios.txt");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (ArrayList<String> u : datos) {
            String nombre = u.get(0);
            String apellido=u.get(1);
            String correo=u.get(3);
            Usuario usuario=new Usuario(nombre,apellido,correo);    
            usuarios.add(usuario);
        }
        return usuarios;

    }

    
}
