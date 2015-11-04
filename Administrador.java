import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
//import org.jdom2.*;
import javax.xml.parsers.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Attribute;





public class Administrador extends Usuario{
    
        int MaxCr=50;
	
	
	public Administrador(String nombre,int edad, String sexo, String rut,String id, String pass){
		super(nombre,edad,sexo,rut,id,pass);
		Admin=true;
		
	}
	
	void PrintData(){
		System.out.println(Nombre);
		System.out.println(Edad);
		System.out.println(Sexo);
		System.out.println(Rut);
		System.out.println(id_usuario);
		System.out.println(Password);
	}
        
Boolean VerificarRamo(String profesor, Ramo r){
            // Profesor no puede dictar dos ramos a la misma hora y no puede haber 2 salas a la misma hora.
		
    
        
return true;
        }
        
Boolean CrearRamo(String Sigla,String Nombre, String Horario, String Sala, String Facultad, int Creditos, double Nota, boolean Retirable, int Seccion, String profesor){
    //Verificar si cumple todo   
	Ramo r = new Ramo(Sigla,Horario,Sala,Facultad,Creditos,Nota,Retirable,Seccion);
	String retirablex="false";
	if(Retirable==true){retirablex="true";}
	try {
	    Document document = null;
	    
	    Element root = null;
	 
	    File xmlFile = new File("data/Carreras/"+Facultad+"/Ramos.txt");
	   
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
	    } else{System.out.println("No existe");return false;}
 
	    Element element = new Element("Ramo");
	    Element elementx = new Element("nombre");
	    elementx.setText(Nombre);
	    element.addContent(elementx);
	    Element element2 = new Element("sigla");
	    element2.setText(Sigla);
	    Element element3 = new Element("horario");
	    element3.setText(Horario);
	    Element element4 = new Element("sala");
	    element4.setText(Sala);
	    Element element5 = new Element("facultad");
	    element5.setText(Facultad);
	    Element element6 = new Element("creditos");
	    String aux = String.valueOf(Creditos);
	 
	    element6.setText(aux);
	    Element element7 = new Element("nota");
	    String aux2 = String.valueOf(Nota);
	    
	    element7.setText(aux2);
	    Element element8 = new Element("retirable");
	    element8.setText(retirablex);
	    Element element9 = new Element("seccion");
	    String aux3 = String.valueOf(Creditos);
	  
	    element9.setText(aux3);
	    element.addContent(element2);
	    element.addContent(element3);
	    element.addContent(element4);
	    element.addContent(element5);
	    element.addContent(element6);
	    element.addContent(element7);
	    element.addContent(element8);
	    element.addContent(element9);
	    root.addContent(element);
  
 
  FileWriter writer = new FileWriter("data/Carreras/"+Facultad+"/Ramos.txt");
  XMLOutputter outputter = new XMLOutputter();
  outputter.setFormat(Format.getPrettyFormat());
  outputter.output(document, writer);

  //outputter.output(document, System.out);
  writer.close(); // close writer
      
}
catch (Exception e) {
  System.err.println(e);
  return false;
}
return true;         
}
        
void ModCantMaxCr(int cant){
            MaxCr = cant;
        }
        
boolean verificarcantMaxCr(Ramo[] listaramos, String Facultad){
 return true;   
}      
}
