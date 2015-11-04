import java.io.*;
//import org.jdom2.*;
import javax.xml.parsers.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Attribute;


public class Sistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Academic Manager main
		
		//XML:http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
		Alumno Active_student= null;
		Administrador Active_admin=null;
		boolean administrador=false;
		
		//Almacenaran los id y password de login o registro.
		String id="prueba2";
		String pass="testpass2";
		SistemaRead sr = new SistemaRead();
		if(sr.Login(id,pass)==0){
			System.out.println("Login Succesful");
			System.out.println("Administrator Mode");
			administrador=true;
			Active_admin=sr.SetAdmin(id,pass);
			
			//Debug
			if(Active_admin!=null){
				Active_admin.PrintData();
			}
			
		}
		else if(sr.Login(id,pass)==1){
			System.out.println("Login Succesful");
			System.out.println("Student Mode");
			Active_student=sr.SetStudent(id,pass);
			
			//Debug
			if(Active_student!=null){
				Active_student.PrintData();
			}
		}
		else{
			System.out.println("Login failed); wrong credentials");
			
		}
		/*String[] ramos = {"IIC2143","ICS3413","IIC2133","IIC2733","IIC2764"};
		Active_student.Tomar_Semestre(ramos, "2016-1");*/
		/*
		try {
			RegistrarAlumno("a","b","c","g","d","e","f","00000000");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		boolean a=Active_admin.CrearRamo("Sigla3","Nombre", "M-J:3", "A93", "Ingenieria", 5 , 5.8 , true, 2,"Comentaro" ,"Profesor4");
		System.out.println(a);
		//Active_admin.SetCreditosMaximos(10);
		/*Foro fr = new Foro();
		fr.AddMessage("Sigla", "TestStudent", "Este es un texto de prueba", "Archivo test");
		fr.PrintForo("Sigla");*/
		
	}

static boolean RegistrarAlumno(String id,String nombre,String carrera,String malla, String edad, String sexo, String rut,String num_alumno) throws JDOMException{
	
	
    try {
    	    Document document = null;
    	    Document document2 = new Document();
    	    Element root = null;
    	    boolean a = new File("data/Alumnos/"+num_alumno).mkdir();
    	    if(!a){return false;}
    	    File xmlFile = new File("data/Alumnos.txt");
    	    
    	    if(xmlFile.exists()) {
    	        // try to load document from xml file if it exist
    	        // create a file input stream
    	        FileInputStream fis = new FileInputStream(xmlFile);
    	 
    	        // create a sax builder to parse the document
    	        SAXBuilder sb = new SAXBuilder();
    	        // parse the xml content provided by the file input stream and create a Document object
    	        document = sb.build(fis);
    	        
    	        // get the root element of the document
    	        
    	        root = document.getRootElement();
    	        fis.close();
    	    } else{return false;}
     
      Element element = new Element(id);
      Element element2 = new Element("nombre");
      element2.setText(nombre);
      Element element3 = new Element("edad");
      element3.setText(edad);
      Element element4 = new Element("sexo");
      element4.setText(sexo);
      Element element5 = new Element("rut");
      element5.setText(rut);
      Element element6 = new Element("numero_alumno");
      element6.setText(num_alumno);
      element.addContent(element2);
      element.addContent(element3);
      element.addContent(element4);
      element.addContent(element5);
      element.addContent(element6);
      root.addContent(element);
      
     
      Element rootHist = new Element("Historial");
      Attribute carrera_att = new Attribute("carrera",carrera);
      rootHist.setAttribute(carrera_att);
    
      document2.setContent(rootHist);
      FileWriter writer = new FileWriter("data/Alumnos.txt");
      FileWriter writer2 = new FileWriter("data/Alumnos/"+num_alumno+"/Historial.txt");
      XMLOutputter outputter = new XMLOutputter();
      outputter.setFormat(Format.getPrettyFormat());
      outputter.output(document, writer);
      outputter.output(document2,writer2);
      //outputter.output(document, System.out);
      writer.close(); // close writer
      writer2.close();
          
    }
    catch (IOException e) {
      System.err.println(e);
      return false;
    }
	
    
	return true;
}

}
	

