package mainproyectosoft;

public class Ramo {
	String Nombre;
	String Sigla;
	String Horario;
	String Sala;
	String Facultad;
	int Creditos;
	double Nota;
	boolean Retirable;
	int Seccion;
	String Comentario;
	
	public Ramo(String nombre,String sigla,String horario,String sala,String facultad,int creditos,double nota,boolean retirable, int seccion,String comentario){
		
		Nombre=nombre;
		Sigla=sigla;
		Horario=horario;
		Sala=sala;
		Facultad=facultad;
		Creditos=creditos;
		Nota=nota;
		Retirable=retirable;
		Seccion=seccion;
		Comentario=comentario;
	}
}
