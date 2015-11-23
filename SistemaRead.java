package mainproyectosoft;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SistemaRead {

public int Login(String id, String pass){
		//Buscar en la base de datos de usuario los datos, entrega 0,1,2,3,4 dependiendo si es usuario(1 vez o registrado), admin, profesor o no se encuentra.
		try{
			File Registro = new File("data/Registro.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(Registro);
			
			NodeList Datos = doc.getElementsByTagName("data");
			
			for(int i=0; i<Datos.getLength();i++){
				Node nodo = Datos.item(i);
				Element info = (Element)nodo;
				String ID = info.getElementsByTagName("id").item(0).getTextContent();
				String PASS = info.getElementsByTagName("password").item(0).getTextContent();
				
				if(ID.equals(id) && PASS.equals(pass)){
					//Existen los datos
					String ADMIN = info.getElementsByTagName("admin").item(0).getTextContent();
					String PROF = info.getElementsByTagName("profesor").item(0).getTextContent();
					if(ADMIN.equals("true")){
					//Es admin
					return 2;
					}
					else if(PROF.equals("true")){
						//Es profesor
						return 3;
					}
					else{
					//Es alumno, chequeamos si existe el archivo Historial.txt
                                            try{
						File Registro2 = new File("data/Alumnos.txt");
						DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
						Document doc2 = dBuilder2.parse(Registro2);
						
						NodeList Datos2 = doc2.getElementsByTagName(id);
						Element nodo2 = (Element)Datos2.item(0);
						String num_alumno = nodo2.getElementsByTagName("numero_alumno").item(0).getTextContent();
						
						File Historial= new File("data/Alumnos/"+num_alumno+"/Historial.txt");
						DocumentBuilderFactory dbFactory3 = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder3 = dbFactory3.newDocumentBuilder();
						Document doc3 = dBuilder3.parse(Historial);
						
						NodeList Datos3 = doc3.getElementsByTagName("Historial");
						
						}catch(Exception e){
							return 0;
						}	
						
					return 1;
					}
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 4;
	}
	
public Alumno SetStudent(String id, String pass){
		try{
			File Registro = new File("data/Alumnos.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(Registro);
			
			NodeList Datos = doc.getElementsByTagName(id);
			
			
			Node nodo = Datos.item(0);
			Element info = (Element)nodo;
			String NAME = info.getElementsByTagName("nombre").item(0).getTextContent();
			int AGE = Integer.parseInt(info.getElementsByTagName("edad").item(0).getTextContent());
			String SEX = info.getElementsByTagName("sexo").item(0).getTextContent();
			String RUT = info.getElementsByTagName("rut").item(0).getTextContent();	
			int NA = Integer.parseInt(info.getElementsByTagName("numero_alumno").item(0).getTextContent());	
			
			Alumno aux= new Alumno(NAME,AGE,SEX,RUT,id,pass,NA);
			if(CheckRamosReprobados(aux)){
			return aux;
			}
			else{
				System.out.println("Ramos reprobados mayores a lo permitido");
				return null;}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
public Administrador SetAdmin(String id, String pass){
		try{
			File Registro = new File("data/Administradores.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(Registro);
			
			NodeList Datos = doc.getElementsByTagName(id);
			
			
			Node nodo = Datos.item(0);
			Element info = (Element)nodo;
			String NAME = info.getElementsByTagName("nombre").item(0).getTextContent();
			int AGE = Integer.parseInt(info.getElementsByTagName("edad").item(0).getTextContent());
			String SEX = info.getElementsByTagName("sexo").item(0).getTextContent();
			String RUT = info.getElementsByTagName("rut").item(0).getTextContent();	
			
			Administrador aux= new Administrador(NAME,AGE,SEX,RUT,id,pass);
			return aux;

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
public Profesor SetProfesor(String id, String pass){
	try{
		File Registro = new File("data/Profesores.txt");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList Datos = doc.getElementsByTagName("Profesor");
		
		for(int i=0;i<Datos.getLength();i++){
		Node nodo = Datos.item(i);
		Element info = (Element)nodo;
		String NAME = info.getAttribute("Nombre");
		String FACULTAD = info.getAttribute("facultad");
		String ID = info.getAttribute("id");	
		if(ID.equals(id)){
			Profesor aux= new Profesor(NAME,FACULTAD);
		
			return aux;
		}
		}

	}
	catch(Exception e){
		e.printStackTrace();
		return null;
	}
	return null;
	
}

public boolean CheckRamosReprobados(Alumno a){
	Historial hist = new Historial();
	Semestre[] ramos=hist.GetHistorial(a);
	int reprobados=0;
	for(int i=0;i<ramos.length;i++){
		reprobados+=ramos[i].Creditos_Reprobados();
	}
	System.out.println(reprobados);
	//Obtenemos la cantidad maxima.
	try{
		File Registro = new File("data/Status.txt");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList Datos = doc.getElementsByTagName("reprobados");
		
		Node nodo = Datos.item(0);
		Element info = (Element)nodo;
		String Data = info.getTextContent();
		int max = Integer.valueOf(Data);
		System.out.println(Data);
		
		if(reprobados>max){
			return false;}
		
		

	}
	catch(Exception e){
		e.printStackTrace();
		
	}
	
	
	return true;
}
ArrayList<String> getCarreras(){
	ArrayList<String> Carreras = new ArrayList<String>();
	File file = new File("data/Carreras");
	String[] names = file.list();

	for(String name : names)
	{
	    if (new File("data/Carreras/" + name).isDirectory())
	    {
	        Carreras.add(name);
	    }
	}
	return Carreras;
}
ArrayList<String> getMallas(String carrera){
	ArrayList<String> mallas = new ArrayList<String>();
	try{
		File Registro = new File("data/Carreras/"+carrera +"/Malla_Curricular.txt");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList Datos = doc.getElementsByTagName("Malla");
		for(int i=0;i<Datos.getLength();i++){
			Node nodo = Datos.item(i);
			Element info = (Element)nodo;
			String aux = info.getAttribute("nombre");
			mallas.add(aux);
		}

		

	}
	catch(Exception e){
		e.printStackTrace();
		return null;
		
	}
	 
	return mallas;
}

ArrayList<String> getRamosMalla(String carrera,String malla){
	ArrayList<String> mallas = new ArrayList<String>();
	try{
		File Registro = new File("data/Carreras/"+carrera +"/Malla_Curricular.txt");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList Datos = doc.getElementsByTagName("Malla");
		for(int i=0;i<Datos.getLength();i++){
			Node nodo = Datos.item(i);
			Element info = (Element)nodo;
			String aux = info.getAttribute("nombre");
			mallas.add(aux);
		}

		

	}
	catch(Exception e){
		e.printStackTrace();
		return null;
		
	}
	 
	return mallas;
	
}

ArrayList<String> getProfesores(){
	ArrayList<String> profesores = new ArrayList<String>();
	try{
		File Registro = new File("data/Profesores.txt");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(Registro);
		
		NodeList Datos = doc.getElementsByTagName("Profesor");
		for(int i=0;i<Datos.getLength();i++){
			Node nodo = Datos.item(i);
			Element info = (Element)nodo;
			String aux = info.getAttribute("Nombre");
			profesores.add(aux);
		}

		

	}
	catch(Exception e){
		e.printStackTrace();
		return null;
		
	}
	
	return profesores;
}
}
