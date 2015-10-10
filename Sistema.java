import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.w3c.dom.*;



public class Sistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Academic Manager main
		
		//XML:http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
		Alumno Active_student= null;
		Administrador Active_admin=null;
		boolean administrador=false;
		
		//Almacenaran los id y password de login o registro.
		String id="prueba";
		String pass="testpass";
		
		if(Login(id,pass)==0){
			System.out.println("Login Succesful");
			System.out.println("Administrator Mode");
			administrador=true;
			Active_admin=SetAdmin(id,pass);
			
			//Debug
			if(Active_admin!=null){
				Active_admin.PrintData();
			}
			
		}
		else if(Login(id,pass)==1){
			System.out.println("Login Succesful");
			System.out.println("Student Mode");
			Active_student=SetStudent(id,pass);
			
			//Debug
			if(Active_student!=null){
				Active_student.PrintData();
			}
		}
		else{
			System.out.println("Login failed); wrong credentials");
		}
		Historial HA = new Historial();
		Semestre[] aux=HA.GetHistorial(Active_student);
		aux[0].print();
		aux[1].print();
		
		
		
		
	}
	static int Login(String id, String pass){
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
	
	static Alumno SetStudent(String id, String pass){
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
			return aux;

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	static Administrador SetAdmin(String id, String pass){
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
	
	
}
