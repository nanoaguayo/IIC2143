package mainproyectosoft;
import javafx.beans.property.SimpleStringProperty;

public class ForoFX {
	
	
	SimpleStringProperty Alumno;
	SimpleStringProperty Mensaje;
	SimpleStringProperty Archivo;

	
	public ForoFX(String nombre,String mensaje,String archivo){
		
		this.Alumno= new SimpleStringProperty(nombre);
        this.Mensaje = new SimpleStringProperty(mensaje);
        this.Archivo = new SimpleStringProperty(archivo);
	}
	
	public String getAlumno(){
		return this.Alumno.get();
	}
	public String getMensaje(){
		return this.Mensaje.get();
	}
	public String getArchivo(){
		return this.Archivo.get();
	}
	
}