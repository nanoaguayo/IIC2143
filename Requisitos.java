package mainproyectosoft;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.w3c.dom.*;

import java.util.ArrayList;


public class Requisitos {

	Boolean checkRequisitos (Alumno a, Ramo r)
	{
		//se asume que es true en un comienzo
		boolean retorno = true;
		
		//La idea de este metodo es que se utilice al momento de que el alumno tome un ramo, por lo que, el  
		// alumno al momento de tomar un semestre  y elegir ramos, sera una condicion que este metodo se
		// cumpla para poder tomar el ramo.
		
		// Este metodo simplemente revisara los requisitos del ramo r que estan almacenados en un un xml que
		// tienen que estar antes armados y luego revisar si los alumnos los cumple.
		
		//La idea sera, revisar el xml de requisitos si es que esta el ramo pedido, si no esta, return true,
		//si es que esta, ir revisando los requisitos y revisar si es que el alumno tiene aprobado ese ramo.
		//En el caso para los creditos, sera necesario contar los creditos del alumno y ver si cumple.
		//en cualquier caso q no cumpla, se devolvera false. 
		
		String path = "data/Carreras/"+r.Facultad+"/Requisitos.txt";
		
		
		try{
		File Registro = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		Node Datos = doc.getFirstChild();
		Element aux = (Element)Datos;
		
		NodeList ramosBases = aux.getElementsByTagName("RamoBase");
		int x = ramosBases.getLength();
		
		int largo = 0;
		int creditos = 0;
		
		
		//primero veo la cantidad de ramos de requisitos que tiene
		for(int i=0;i<x;i++)
		{
			Node aux2 = ramosBases.item(i);
			Element E2 = (Element)aux2;
			String sigla = E2.getAttribute("sigla");
			
			if (sigla.equals(r.Sigla))
			{
				try
				{
					NodeList creditosAux = E2.getElementsByTagName("CredReq");
					String credito = creditosAux.item(0).getTextContent();
					creditos = Integer.valueOf(credito);
				}
				catch (Exception e){
				}

				NodeList requisitos = E2.getElementsByTagName("RamoReq");
				largo = requisitos.getLength();

			}
		}
		
		String[] contenido = new String[largo];
		
		
		
		NodeList ramosBases2 = aux.getElementsByTagName("RamoBase");
		
		for(int i=0;i<x;i++)
		{
			Node aux2 = ramosBases2.item(i);
			Element E2 = (Element)aux2;
			String sigla = E2.getAttribute("sigla");
			
			if (sigla.equals(r.Sigla))
			{	
				NodeList requisitos2 = E2.getElementsByTagName("RamoReq");
				
			
			
				for(int j=0; j<largo; j++)
				{
					Node nodo1 = requisitos2.item(j);
					Element elem = (Element)nodo1;
					String sigla2= elem.getTextContent();
					contenido[j]= sigla2;
				}
			}
		}
		
		//Ya teniendo la lista de cursos de requisitos, falta ver si estan aprobados.
		//Para esto utilizara el historial del alumno para ver que ramos tiene aprobados,
		// esto se saca directamente de historial para no repetir codigo ya armado
		
		Historial hist_aux = new Historial();
		
		Semestre [] sem = hist_aux.GetHistorial(a);
		
		int contadorCreditos = 0;

		for(int i=0; i<largo;i++)
		{
			retorno = false; //se hace false, y si encuentra que el ramo esta aprobado, se transformara en true
			for(int j=0; j<sem.length;j++)
			{
				for(int k=0; k<sem[j].Ramos.length;k++)
				{	
					if(contenido[i].equals(sem[j].Ramos[k].Sigla))
					{
						if(sem[j].Ramos[k].Nota>=3.95)
						{
							retorno=true;
						}
					}
					contadorCreditos = contadorCreditos + sem[j].Ramos[k].Creditos;
				}
			}
			if (retorno==false)
			{
				return false;
			}
		}
		
		//Ahora se chequea si tiene los creditos necesarios de requisitos
		//los creditos ya fueron contados en el for pasado cuando se recorrio el historial
		if (contadorCreditos<creditos)
		{
			return false;
		}
		
		
		
		return true;
		}
		catch(Exception e){e.printStackTrace();}
		
		return true;
	}
	
	void print(String x){
		System.out.println(x);
	}
	
}
