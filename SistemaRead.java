import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SistemaRead {

int Login(String id, String pass){
		//Buscar en la base de datos de usuario los datos, entrega 0,1,2 dependiendo si es usuario, admin o no se encuentra.
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
					if(ADMIN.equals("true")){
					return 0;
					}
					return 1;
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 2;
	}
	
Alumno SetStudent(String id, String pass){
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
	
Administrador SetAdmin(String id, String pass){
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
boolean CheckRamosReprobados(Alumno a){
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
}
