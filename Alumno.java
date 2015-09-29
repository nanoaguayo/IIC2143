
public class Alumno extends Usuario{
	int numero_alumno;
	
	public Alumno(String nombre,int edad, String sexo, String rut,String id, String pass,int num){
		super(nombre,edad,sexo,rut,id,pass);
		numero_alumno=num;
	}

}
