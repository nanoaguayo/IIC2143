

public class Administrador extends Usuario{
	
	
	public Administrador(String nombre,int edad, String sexo, String rut,String id, String pass,int num){
		super(nombre,edad,sexo,rut,id,pass);
		Admin=true;
		
	}

}

