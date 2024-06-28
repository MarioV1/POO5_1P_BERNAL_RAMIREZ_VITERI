package gestorrevista;

public class Autor extends Usuario {
    //Atributos
    private String codigoID;
    private String institucion;
    private String campo;
    //Constructor
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
}
