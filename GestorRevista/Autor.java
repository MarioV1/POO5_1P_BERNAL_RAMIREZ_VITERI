package GestorRevista;

public class Autor extends Usuario {
    private String codigoID;
    private String institucion;
    private String campo;
    public Autor(String codigoID, String institucion,String campo){
        this.codigoID=codigoID;
        this.institucion=institucion;
        this.campo=campo;
    }
}
