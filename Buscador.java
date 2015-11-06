package mainproyectosoft;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Buscador {

	ArrayList <RamoFX> Buscar(ArrayList <String> Carreras, String Nombre, String Sigla, String Facultad, String Profesor, ArrayList <String> Horario){
		
		ArrayList <RamoFX> resultado = new ArrayList <RamoFX> ();
		
		

			//recorre las distintas carreras y asi se ira rellenando una lista de ramos con todos los ramos existentes
			for(int i=0; i< Carreras.size();i++)
			{
				String path = "data/Carreras/"+Carreras.get(i)+"/Ramos.txt";
				try
				{		
					File Registro = new File(path);
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(Registro);
					
					Node Datos = doc.getFirstChild();
					Element aux = (Element)Datos;
					
					NodeList Ramos = aux.getElementsByTagName("Ramo");
					
					RamoFX aux2 = null;
					for(int j=0; j<Ramos.getLength(); j++)
					{
						Element nodo = (Element)Ramos.item(j);
						
						String NombreAux=nodo.getElementsByTagName("nombre").item(0).getTextContent();
						String SiglaAux=nodo.getElementsByTagName("sigla").item(0).getTextContent();
						String HorarioAux=nodo.getElementsByTagName("horario").item(0).getTextContent();
						//String Sala=nodo.getElementsByTagName("sala").item(0).getTextContent();
						String FacultadAux=nodo.getElementsByTagName("facultad").item(0).getTextContent();
						int Creditos=Integer.parseInt(nodo.getElementsByTagName("creditos").item(0).getTextContent());
						boolean Retirable=Boolean.parseBoolean(nodo.getElementsByTagName("retirable").item(0).getTextContent());
						int Seccion=Integer.parseInt(nodo.getElementsByTagName("seccion").item(0).getTextContent());
						String Comentario = nodo.getElementsByTagName("comentario").item(0).getTextContent();
						String boo="";
						if(Retirable){boo="true";}else{boo="false";}
						aux2 = new RamoFX(SiglaAux,boo,String.valueOf(Seccion),HorarioAux,NombreAux,String.valueOf(Creditos),Comentario,FacultadAux);
						resultado.add(aux2);
					}
				}
				catch(Exception e)
				{
					System.out.println("Problemas con la ruta.");
				}
			}
		

		//Si esta filtrando por nombre, por lo que de la lista resultado se eliminaran los que no tienen este nombre el ramo
		if(!Nombre.equals(""))
		{
			for(int i=0; i<resultado.size(); i++)
			{
				if(!resultado.get(i).getNombre().toLowerCase().contains(Nombre.toLowerCase()))
				{
					resultado.remove(i);
					i--;
				}
			}
		}
		
		//revisa si se filtra por sigla, si es si, elimina de la lista los que no concuerdan
		if(!Sigla.equals(""))
		{
			for(int i=0; i<resultado.size(); i++)
			{
				if(!resultado.get(i).getSigla().toLowerCase().contains(Sigla.toLowerCase()))
				{
					resultado.remove(i);
					i--;
				}
			}
		}
		
		//revisa por facultad
		if(!Facultad.equals(""))
		{
			for(int i=0; i<resultado.size(); i++)
			{
				if(!resultado.get(i).getFacultad().toLowerCase().contains(Facultad.toLowerCase()))
				{
					resultado.remove(i);
					i--;
				}
			}
		}
		
		System.out.println("entro");
		
		//Revisa por profesor
		if(!Profesor.equals(""))
		{
			ArrayList <String> ramosProfesores = new ArrayList <String>();
			String path = "data/Profesores.txt";
			try
			{		
				File Registro = new File(path);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(Registro);
				
				Node Datos = doc.getFirstChild();
				Element aux = (Element)Datos;
				
				NodeList ProfesorAux = aux.getElementsByTagName("Profesor");
				
				for(int i=0; i<ProfesorAux.getLength(); i++)
				{
					Node aux2 = ProfesorAux.item(i);
					Element E2 = (Element)aux2;
					String nombre = E2.getAttribute("Nombre");
					
					if(nombre.toLowerCase().contains(Profesor.toLowerCase()))
					{
						NodeList ramos = E2.getElementsByTagName("ramo");
						
						for(int j=0; j<ramos.getLength(); j++)
						{
							Node nodo = ramos.item(j);
							Element elem = (Element)nodo;
							String sigla= elem.getTextContent();
							ramosProfesores.add(sigla);
														
						}
						
					}
					
				}
			}
			catch(Exception e)
			{
				System.out.println("Problemas con la ruta.");
			}
			
			
			for(int i=0; i<resultado.size(); i++)
			{
				boolean confirmacion=false;
				for(int j=0; j<ramosProfesores.size();j++)
				{
					if(resultado.get(i).getSigla().equals(ramosProfesores.get(j)))
					{
						confirmacion=true;
					}
				}
				if(!confirmacion)
				{
					resultado.remove(i);
					i--;					
				}
			}
		}
		
		
		
		if(Horario.size()!=0)
		{
			for(int i=0; i<resultado.size(); i++)
			{
				if(!contieneHorarios2(Horario,resultado.get(i).getHorario()))
				{
					resultado.remove(i);
					i--;
				}
			}
		}
		
		ArrayList <String> ramosProfesores = new ArrayList <String>();
		String path = "data/Profesores.txt";
		try
		{		
			File Registro = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(Registro);
			
			Node Datos = doc.getFirstChild();
			Element aux = (Element)Datos;
			
			NodeList ProfesorAux = aux.getElementsByTagName("Profesor");
			
			for(int i=0; i<ProfesorAux.getLength(); i++)
			{
				Node aux2 = ProfesorAux.item(i);
				Element E2 = (Element)aux2;
				String nombre = E2.getAttribute("Nombre");
				
				
					NodeList ramos = E2.getElementsByTagName("ramo");
					
					for(int j=0; j<ramos.getLength(); j++)
					{
						Node nodo = ramos.item(j);
						Element elem = (Element)nodo;
						String sigla= elem.getTextContent();
						for(int k=0;k<resultado.size();k++){
						 if(resultado.get(k).getSigla().equals(sigla)){
							 resultado.get(k).setProfesor(nombre);
						 }
						}
						
													
					}
					
				
				
			}
		}
		catch(Exception e)
		{
			System.out.println("Problemas con la ruta.");
		}
		
		
		for(int i=0; i<resultado.size();i++)
		{
			System.out.println(resultado.get(i).getSigla());
		}
		
		return resultado;
		
	}



	boolean contieneHorarios (String chico,String grande)

	
	{
		String[] aux1 = chico.split("-");
		String[] aux2 = grande.split("-");
		
		for(int i=0; i<aux1.length;i++)
		{
			boolean validador = false;
			for (int j=0; j<aux2.length;j++)
			{
				if(aux1[i].equals(aux2[j]))
				{
					validador=true;
				}
			}
			if(!validador)
			{
				return false;
			}
		}
		
		return true;
	}


	boolean contieneHorarios2 (ArrayList <String> chico, String grande)
	{
		String[] aux2 = grande.split("-");
		
		for(int i=0; i<chico.size();i++)
		{
			boolean validador = false;
			for (int j=0; j<aux2.length;j++)
			{
				if(chico.get(i).equals(aux2[j]))
				{
					validador=true;
				}
			}
			if(!validador)
			{
				return false;
			}
		}
		return true;
	}
}