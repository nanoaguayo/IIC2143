import javafx.beans.property.SimpleStringProperty;

public class InboxFX {
	
	
	SimpleStringProperty Emisor;
	SimpleStringProperty Mensaje;

	
	public InboxFX(String nombre,String mensaje){
		
		this.Emisor= new SimpleStringProperty(nombre);
        this.Mensaje = new SimpleStringProperty(mensaje);
	}
	
	public String getEmisor(){
		return this.Emisor.get();
	}
	public String getMensaje(){
		return this.Mensaje.get();
	}
	
}