package mainproyectosoft;

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

	

public boolean RegistrarAlumno(String id,String nombre,String carrera,String malla, String edad, String sexo, String rut,String num_alumno) throws JDOMException{
	
	
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
      Attribute malla_att = new Attribute("malla", malla);
      rootHist.setAttribute(carrera_att);
      rootHist.setAttribute(malla_att);
    
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
	

