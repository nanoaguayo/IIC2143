
public class Usuario {
	String Nombre;
	int Edad;
	String Sexo;
	String Rut;
	String id_usuario;
	String Password;
	boolean Admin=false;
	
	public Usuario(String nombre,int edad, String sexo, String rut,String id, String pass){
		Nombre=nombre;
		Edad=edad;
		Sexo=sexo;
		Rut=rut;
		id_usuario=id;
		Password=pass;
	}
}
