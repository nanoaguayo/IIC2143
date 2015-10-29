import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.w3c.dom.*;

import org.w3c.dom.NodeList;
import java.util.ArrayList;


public class Requisitos {

	Boolean checkRequisitos (Alumno a, Ramo r)
	{
		//La idea de este metodo es que se utilice al momento de que el alumno tome un ramo, por lo que, el  
		// alumno al momento de tomar un semestre  y elegir ramos, sera una condicion que este metodo se
		// cumpla para poder tomar el ramo.
		
		// Este metodo simplemente revisara los requisitos del ramo r que estan almacenados en un un xml que
		// tienen que estar antes armados y luego revisar si los alumnos los cumple.
		
		//La idea sera, revisar el xml de requisitos si es que esta el ramo pedido, si no esta, return true,
		//si es que esta, ir revisando los requisitos y revisar si es que el alumno tiene aprobado ese ramo.
		//En el caso para los creditos, sera necesario contar los creditos del alumno y ver si cumple.
		//en cualquier caso q no cumpla, se devolvera false. 
		
		String path = "data/Requisitos.txt";
		// num=String.valueOf(a.numero_alumno);
		//path+=num;
		//path+="/Ingenieria/Requisitos.txt";
		ArrayList <String> siglas_requisitos = new ArrayList <String>();
		
		try{
		File Registro = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList nList = doc.getElementsByTagName("RamoBase");
		
		for(int temp = 0; temp < nList.getLength(); temp++)
		{
				
			Node nNode = nList.item(temp);
			Element e = (Element)nNode;
			if(r.Sigla.equals(e.getAttribute("nombre")))  // el ramo tiene requisitos
			{
				NodeList nList2 = doc.getElementsByTagName("RamoReq");

				for(int i = 0; i < nList.getLength(); i++)
				{

					Node nNode2 = nList2.item(i);
					Element e2 = (Element)nNode2;
					siglas_requisitos.add(e2.getTextContent()); //agrego a la lista los cursos de requisitos
					
				}
			}
		}
		
		}
		catch(Exception e){e.printStackTrace();}
		
		//Ya teniendo la lista de cursos de requisitos, falta ver si estan aprobados.
		//Para esto utilizara el historial del alumno para ver que ramos tiene aprobados,
		// esto se saca directamente de avance curricular para no repetir codigo ya armado
		
		Historial HA = new Historial();
		HA.GetHistorial(a);
		
		Semestre[] aux = HA.GetHistorial(a); // aux es un array con los semestres cursados
		int largo_aux = HA.GetHistorial(a).length;
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
		
		//Hay una lista con las siglas de los ramos de requisitos y otra lista con ramos aprobados, falta
		//compararlos y ver si se cumplen.
		
		for(int k=0; k < siglas_requisitos.size() ; k++)
		{
			boolean bool_aux = false; // si el ramo no esta, esta variable seguira false

			for(int j=0; j < ramos_aprobados.size() ; j++)
			{
				if(siglas_requisitos.get(k).equals(ramos_aprobados.get(j).Sigla))
				{
					bool_aux=true;
				}
			}
			if(!bool_aux) // como el ramo requisito no esta en los aprobados, retorna falso.
			{
				return false;
			}
		}
		
		return true;
	}
	
}

