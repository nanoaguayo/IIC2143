
public class Semestre {
	String Periodo;
	Ramo[] Ramos;
	public Semestre(String x, Ramo[] y){
		Periodo=x;
		Ramos=y;
	}
	
	void print(){
			System.out.println(Periodo);
		for(int i=0;i<Ramos.length;i++){
			if(Ramos[i]!=null){
			System.out.println(Ramos[i].Sigla);
			}
		}
	}
}
