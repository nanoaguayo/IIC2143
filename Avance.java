import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Avance {

	Ramo[] GetAvance (Alumno a, Malla_Curricular m)
	{
		Historial hist_aux = new Historial();
		
		
		
		Semestre[] aux = hist_aux.GetHistorial(a); // aux es un array con los semestres cursados
		int largo_aux = aux.length;
		Ramo [] aux_ramo = new Ramo[largo_aux];
		//Ahora se revisaran todos los ramos cursados para hacer una lista de todos los ramos aprobados.
		
		ArrayList<Ramo> ramos_aprobados = new ArrayList<Ramo>();
		for(int i=0; i<largo_aux; i++)
		{
			int largo = aux[i].Ramos.length;
			for(int j=0; j<largo ; j++)
			{
				if(aux[i].Ramos[j].Nota >= 4) // si ramo esta con nota mayor a 4, esta aprobado
				{
					ramos_aprobados.add(aux[i].Ramos[j]); // se agrega el ramo aprobado a la lista
				}
			}
		}
		
		//Ahora ver que ramos de estos estan dentro de la malla seleccionado por el alumno, y mostrar todos los que esten.
		
		String path = "data/";
		String num=String.valueOf(a.numero_alumno);
		path+=num;
		path+="/Ingenieria/Malla_Curricular.txt";
		
		try{
		File Registro = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList ramos;
		
		NodeList nList = doc.getElementsByTagName("Malla");
		
		ArrayList  <String> siglas_ramos = new ArrayList <String>(); // lista con las siglas q son de la maya
		
		for(int temp = 0; temp < nList.getLength(); temp++)
		{
			Node nNode = nList.item(temp);
			Element e = (Element)nNode;
			if (m.equals(e.getAttribute("nombre")))  // si es que la malla ingresada por alumno es igual a la de este nodo, si no es, no hace nada
			{
				ramos = e.getElementsByTagName("Ramo");  // se hace una lista de todos los ramos
				
				
				for(int k=0 ; k < ramos.getLength(); k++)
				{
					Element e2 = (Element) ramos.item(k);
					siglas_ramos.add (e2.getTextContent());  // copio las siglas.
				}
			}
		}
		
		//Ahora se mostraran todos los ramos aprobados de la maya.
		

		ArrayList  <Ramo> ramos_aprobados_malla = new ArrayList <Ramo>(); 
		for(int p=0; p < ramos_aprobados.size() ; p++)
		{

			for(int q=0; q < siglas_ramos.size() ; q++)
			{
				if (ramos_aprobados.get(p).Sigla.equals(siglas_ramos.get(q)))  // si ramo aprobado esta en la malla
				{
					ramos_aprobados_malla.add(ramos_aprobados.get(p)); // se agrega ramo aprobado a avance curricular
				}
			}
		}
	
		
		Ramo[] avance = new Ramo[ramos_aprobados_malla.size()];
		for(int p=0; p < ramos_aprobados_malla.size() ; p++)
		{
			avance[p] = ramos_aprobados_malla.get(p);
		}
		return avance;
		}
		catch(Exception e){e.printStackTrace();}
		
		
		return aux_ramo;
	}
	
}
