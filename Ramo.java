
public class Ramo {
	String Sigla;
	String Horario;
	String Sala;
	String Facultad;
	int Creditos;
	double Nota;
	boolean Retirable;
	int Seccion;
	
	public Ramo(String sigla,String horario,String sala,String facultad,int creditos,boolean retirable, int seccion){
		
		Sigla=sigla;
		Horario=horario;
		Sala=sala;
		Facultad=facultad;
		Creditos=creditos;
		Nota=0.0;
		Retirable=retirable;
		Seccion=seccion;
	}
}
