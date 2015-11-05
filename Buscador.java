package mainproyectosoft;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.w3c.dom.*;

public class Buscador {

	Ramo Buscar(String sigla){
		Ramo resultado=null;
		try{
		File Registro = new File("data/Ramos.txt");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList Datos = doc.getElementsByTagName("Ramo");
		for(int i=0; i<Datos.getLength();i++){
			Node Nodo = Datos.item(i);
			Element info = (Element)Nodo;
			if(info.getElementsByTagName("sigla").item(0).getTextContent().equals(sigla)){
				//Es el ramo buscado. Lo retornamos
				String Sigla=sigla;
				String Nombre = info.getElementsByTagName("nombre").item(0).getTextContent();
				String Horario=info.getElementsByTagName("horario").item(0).getTextContent();
				String Sala=info.getElementsByTagName("sala").item(0).getTextContent();
				String Facultad=info.getElementsByTagName("facultad").item(0).getTextContent();
				int Creditos=Integer.parseInt(info.getElementsByTagName("creditos").item(0).getTextContent());
				double Nota=Double.parseDouble(info.getElementsByTagName("nota").item(0).getTextContent());
				boolean Retirable=Boolean.parseBoolean(info.getElementsByTagName("retirable").item(0).getTextContent());;
				int Seccion=Integer.parseInt(info.getElementsByTagName("seccion").item(0).getTextContent());
				String Comentario=info.getElementsByTagName("comentario").item(0).getTextContent();
				resultado=new Ramo(Nombre,Sigla,Horario,Sala,Facultad,Creditos,Nota,Retirable,Seccion,Comentario);
				return resultado;
			}
		}
		}
		catch(Exception e){
			return null;
		}
		
		return resultado;
		
	}
}