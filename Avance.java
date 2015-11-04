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


		//Ahora ver que ramos de estos estan dentro de la malla seleccionado por el alumno, y mostrar todos los que esten.
		
		String path = "data/Carreras";
		//String num=String.valueOf(a.numero_alumno);
		//path+=num;
		path+="/"+m.Nombre+"/Malla_Curricular.txt";
		
		try{
		File Registro = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		Node Datos = doc.getFirstChild();
		Element aux = (Element)Datos;
		/*/Obtenemos los datos de carrera y malla
		String Carrera= aux.getAttribute("carrera");
		String malla = aux.getAttribute("malla");*/
		
		//Sacamos las mallas
		NodeList Mallas = aux.getElementsByTagName("Malla");
		int x = Mallas.getLength();
		
		int largo = 0;
		
		for(int i=0;i<x;i++)
		{
			Node aux2 = Mallas.item(i);
			Element E2 = (Element)aux2;
			String carrera = E2.getAttribute("carrera");
			String nombre = E2.getAttribute("nombre");
			
			if (nombre.equals(m.Especialidad))
			{
				print("entro a avance");
				NodeList ramos = E2.getElementsByTagName("Ramo");
				largo = ramos.getLength();

			}
		}
		
		String[] contenido = new String[largo];
		
		
		
		NodeList Mallas2 = aux.getElementsByTagName("Malla");
		
		print("hola");
		for(int i=0;i<x;i++)
		{
			Node aux2 = Mallas2.item(i);
			print("1");
			Element E2 = (Element)aux2;
			String carrera = E2.getAttribute("carrera");
			String nombre = E2.getAttribute("nombre");
			
			print(nombre);
			if (nombre.equals(m.Especialidad))
			{	
				NodeList ramos = E2.getElementsByTagName("Ramo");

			
				for(int j=0; j<largo; j++)
				{
					Node nodo1 = ramos.item(j);
					Element elem = (Element)nodo1;
					String sigla= elem.getTextContent();
					contenido[j]= sigla;
				}
			}
		}
		
		//ahora recorrere el xml de ramos para hacer un arreglo de ramos del avance de la malla
		Ramo [] ramosMalla = new Ramo [largo];
		String path2= "data/Carreras/"+m.Nombre+"/Ramos.txt";
		Registro = new File(path2);
		doc = dBuilder.parse(Registro);
		for(int i=0; i<largo; i++)
		{
			NodeList listaRamos = doc.getElementsByTagName("Ramo");
			for(int y=0;y<listaRamos.getLength();y++)
			{
				Element nodo = (Element)listaRamos.item(y);
				String nameRamo = nodo.getElementsByTagName("sigla").item(0).getTextContent();
				
				if(nameRamo.equals(contenido[i]))
				{
					String Nombre=nodo.getElementsByTagName("nombre").item(0).getTextContent();
					String Sigla=nodo.getElementsByTagName("sigla").item(0).getTextContent();
					print(Sigla);
					String Horario=nodo.getElementsByTagName("horario").item(0).getTextContent();
					String Sala=nodo.getElementsByTagName("sala").item(0).getTextContent();
					String Facultad=nodo.getElementsByTagName("facultad").item(0).getTextContent();
					int Creditos=Integer.parseInt(nodo.getElementsByTagName("creditos").item(0).getTextContent());
					double Nota= 0;
					boolean Retirable=Boolean.parseBoolean(nodo.getElementsByTagName("retirable").item(0).getTextContent());
					int Seccion=Integer.parseInt(nodo.getElementsByTagName("seccion").item(0).getTextContent());
					
					Ramo aux3 = new Ramo(Nombre,Sigla,Horario,Sala,Facultad,Creditos,Nota,Retirable,Seccion);
					ramosMalla[i]=aux3;
				}
			}
		}
		
		for (int i=0; i<largo; i++)
		{
			print(ramosMalla[i].Sigla);
		}
		
		
		// En ramosMalla estan los ramos correspondientes a la malla elegida (m.especialidad)
		
		//Ahora comparare con historial para ver los ramos aprobados de la malla y estos
		// ponerlos con nota y aprobado, y el resto como pendiente.
		
		//y se imprimira un arreglo que es SIGLA , NOMBRE , A o P, Nota

		Historial hist_aux = new Historial();
		
		Semestre [] sem = hist_aux.GetHistorial(a);
		
		String [][] avanceCurricular = new String [largo][4];
		
		for(int i=0; i<largo;i++)
		{
			avanceCurricular[i][0] = ramosMalla[i].Sigla;
			avanceCurricular[i][1] = ramosMalla[i].Nombre;
			avanceCurricular[i][2] = "P";
			avanceCurricular[i][3] = "0";
			for(int j=0; j<sem.length;j++)
			{
				for(int k=0; k<sem[j].Ramos.length;k++)
				{
					if(ramosMalla[i].Sigla.equals(sem[j].Ramos[k].Sigla))
					{
						avanceCurricular[i][2] = "A";
						avanceCurricular[i][3] = Double.toString(sem[j].Ramos[k].Nota);
					}
				}
			}
		}
		
		for(int i=0 ; i <largo; i++)
		{
			System.out.print(avanceCurricular[i][0]);
			System.out.print(",");
			System.out.print(avanceCurricular[i][1]);
			System.out.print(",");
			System.out.print(avanceCurricular[i][2]);
			System.out.print(",");
			System.out.print(avanceCurricular[i][3]);
			System.out.println();
			
		}
		}

		catch(Exception e){e.printStackTrace();}
		return null;
	}


	void print(String x){
		System.out.println(x);
	}	

}
		
	

