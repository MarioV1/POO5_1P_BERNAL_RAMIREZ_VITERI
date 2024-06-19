package GestorRevista;

public class Revisor extends Usuario{
    //Variables de instancia
    private String especialidad;
    private String usuario;
    private String contraseña;
    private int numArticulos;

    //Constructor
    public Revisor(String especialidad, String usuario, String contraseña, int numArticulos){
        this.especialidad = especialidad;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.numArticulos = numArticulos;
    }


    //getters y setters
    
    public String getEspecialidad(){
        return especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getContraseña(){
        return contraseña;
    }

    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }

    public int getNumArticulos(){
        return numArticulos;
    }

    public void setNumArticulos(int numArticulos){
        this.numArticulos = numArticulos;
    }


}
