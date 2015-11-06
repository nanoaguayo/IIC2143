

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
        
        void agregarRamo(Ramo ramoNuevo){
            Ramo[] copia = Ramos;
            Ramos = new Ramo[copia.length + 1];
            for (int i=0; i<copia.length; i++){
                Ramos[i] = copia[i];
            }
            Ramos[Ramos.length-1] = ramoNuevo;
        }
        int Creditos_Reprobados(){
        	int aux=0;
        	for(int i=0;i<Ramos.length;i++){
        		if(Ramos[i].Nota>0.0 && Ramos[i].Nota<3.95){
        			aux++;
        		}
        	}
        	return aux;
        }
}