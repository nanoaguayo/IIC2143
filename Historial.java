package mainproyectosoft;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.w3c.dom.*;

public class Historial {
	
	Semestre[] GetHistorial(Alumno a){
		String path = "data/Alumnos/";
		String num=String.valueOf(a.numero_alumno);
		path+=num;
		path+="/Historial.txt";
		
		try{
		File Registro = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		Node Datos = doc.getFirstChild();
		Element aux = (Element)Datos;
		//Obtenemos los datos de carrera y malla
		String Carrera= aux.getAttribute("carrera");
		String malla = aux.getAttribute("malla");
		//Sacamos los semestres.
		NodeList Semestres = aux.getElementsByTagName("Semestre");
		int x = Semestres.getLength();
		//Inicializamos el Historial
		Semestre[] resultado = new Semestre[x];
		//Buscamos los ramos en la carrera
		String path2= "data/Carreras/"+Carrera+"/Ramos.txt";
		Registro = new File(path2);
		doc = dBuilder.parse(Registro);
		
		for(int i=0;i<x;i++){
			Node aux2 = Semestres.item(i);
			Element E2 = (Element)aux2;
			String periodo = E2.getAttribute("periodo");
			NodeList ramos = E2.getElementsByTagName("Ramo");
			
			Ramo[] contenido = new Ramo[ramos.getLength()];
			Ramo aux3=null;
			for(int j=0; j<ramos.getLength(); j++){
				
				NodeList lista = doc.getElementsByTagName("Ramo");
				
				for(int y=0;y<lista.getLength();y++){
				Element nodo = (Element)lista.item(y);
				Element nodo1 = (Element)ramos.item(j);
				
				String name1= nodo.getElementsByTagName("sigla").item(0).getTextContent();
				String name2= nodo1.getTextContent();

				
					if(name1.equals(name2)){
							String Nombre=nodo.getElementsByTagName("nombre").item(0).getTextContent();
							String Sigla=nodo.getElementsByTagName("sigla").item(0).getTextContent();
							String Horario=nodo.getElementsByTagName("horario").item(0).getTextContent();
							String Sala=nodo.getElementsByTagName("sala").item(0).getTextContent();
							String Facultad=nodo.getElementsByTagName("facultad").item(0).getTextContent();
							int Creditos=Integer.parseInt(nodo.getElementsByTagName("creditos").item(0).getTextContent());
							double Nota= Double.valueOf(nodo1.getAttribute("nota"));
							boolean Retirable=Boolean.parseBoolean(nodo.getElementsByTagName("retirable").item(0).getTextContent());
							int Seccion=Integer.parseInt(nodo.getElementsByTagName("seccion").item(0).getTextContent());
							String Comentario = nodo.getElementsByTagName("comentario").item(0).getTextContent();
							
							aux3 = new Ramo(Nombre,Sigla,Horario,Sala,Facultad,Creditos,Nota,Retirable,Seccion,Comentario);
							contenido[j]=aux3;
								}
				}
			}
			resultado[i]=new Semestre(periodo,contenido);
		}
		
		return resultado;
		
		}
		catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	void print_int(int x){
		System.out.println(Integer.toString(x));
	}
	void print(String x){
		System.out.println(x);
	}

}