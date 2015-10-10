
public class Alumno extends Usuario{
	int numero_alumno;
	
	public Alumno(String nombre,int edad, String sexo, String rut,String id, String pass,int num){
		super(nombre,edad,sexo,rut,id,pass);
		numero_alumno=num;
	}
	
	void PrintData(){
		System.out.println(Nombre);
		System.out.println(Edad);
		System.out.println(Sexo);
		System.out.println(Rut);
		System.out.println(id_usuario);
		System.out.println(Password);
		System.out.println(numero_alumno);
	}

}
