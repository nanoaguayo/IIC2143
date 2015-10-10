

public class Administrador extends Usuario{
	
	
	public Administrador(String nombre,int edad, String sexo, String rut,String id, String pass){
		super(nombre,edad,sexo,rut,id,pass);
		Admin=true;
		
	}
	
	void PrintData(){
		System.out.println(Nombre);
		System.out.println(Edad);
		System.out.println(Sexo);
		System.out.println(Rut);
		System.out.println(id_usuario);
		System.out.println(Password);
		
	}

}

