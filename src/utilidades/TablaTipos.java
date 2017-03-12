package utilidades;
import java.util.Vector;

public class TablaTipos{
    private String nombrePrograma;
    private Vector tabla;
    
    
    public TablaTipos(){
        tabla = new Vector();
        nombrePrograma = "no name";
    }

    public void setNombrePrograma(String nombrePrograma){
        this.nombrePrograma = nombrePrograma;
    }

    public String getNombrePrograma(){
        return this.nombrePrograma;
    }
    
    public void agregaTipo(String nombre, String tipoBase, Float valor){
        tabla.addElement(new Tipo(nombre, tipoBase, valor));
    }
    
    public boolean eliminaTipo(String nombreTipo){
        return tabla.removeElement(getTipo(nombreTipo));
    }
    
    public boolean existeTipo(String nombreTipo){
        for(Object i: tabla){
            Tipo t = (Tipo) i;
            if(t.getNombre().equals(nombreTipo))
                return true;
        }        
        return false;
    }
    
    public Tipo getTipo(String nombreTipo){
        for(Object i: tabla){
            Tipo t = (Tipo) i;
            if(t.getNombre().equals(t.getNombre()))
                return t;
        }
        return null;
    }
    
    public void borrarFinal(){
        tabla.removeElementAt(tabla.size() - 1);
    }
    
    public void print(){
        System.out.println(this.nombrePrograma + " TABLA DE SIMBOLOS: ");
        for(Object i: tabla){
            Tipo t = (Tipo)i;
            t.print();
        }
    }

}