package gestorrevista;

public class Editor extends Usuario {
    private String journal;
    private String usuario;
    private String contraseña;
    
    //constructor
    public Editor(String nombre, String apellido, String correo, String journal, String usuario, String contraseña){
        super(nombre,apellido,correo);
        this.journal = journal;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    public Editor(String journal, String usuario, String contraseña){
        this.journal = journal;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    //getters y setters
    public String getJournal(){
        return journal;
    }

    public void setJornal(String journal){
        this.journal = journal;
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
    //Métodos
    /*@Override
    public boolean equals(Editor e){
        if(e!=null && getClass()==e.getClass()){
            
        }
    }*/

}
