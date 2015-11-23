package mainproyectosoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Foro {

public ArrayList<ForoFX> PrintForo(String Ramo){
	ArrayList<ForoFX> res = new ArrayList<ForoFX>();
		 try {
	    	    Document document = null;
	    	    Element root = null;
	    	    	    	    
	    	    File xmlFile = new File("data/Foro/"+Ramo+".txt");
	    	    
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
	    	    }
	    	    
	    	    List<Element> mensajes = root.getChildren();
	    	    for(int i=0; i<mensajes.size();i++){
	    	    	String alumno = mensajes.get(i).getChildText("alumno");
	    	    	String texto = mensajes.get(i).getChildText("texto");
	    	    	String archivo = mensajes.get(i).getChildText("archivo");
	    	    	ForoFX aux = new ForoFX(alumno,texto,archivo);
	    	    	res.add(aux);
	    	    }
		 }catch(Exception e){
			 return null;
		 } 
		 
		 return res;
	}
	void AddMessage(String Ramo,String Alumno,String Texto,String FilePath){
		try {
    	    Document document = null;
    	    Element root = null;
    	    	    	    
    	    File xmlFile = new File("data/Foro/"+Ramo+".txt");
    	    
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
    	    }
    	    
    	    Element element = new Element("mensaje");
    	    Element element1 = new Element("alumno");
    	    Element element2 = new Element("texto");
    	    Element element3 = new Element("archivo");
    	    element1.setText(Alumno);
    	    element2.setText(Texto);
    	    element3.setText(FilePath);
    	    element.addContent(element1);
    	    element.addContent(element2);
    	    element.addContent(element3);
    	    root.addContent(element);
    	    
    	    FileWriter writer = new FileWriter("data/Foro/"+Ramo+".txt");
    	 
    	      XMLOutputter outputter = new XMLOutputter();
    	      outputter.setFormat(Format.getPrettyFormat());
    	      outputter.output(document, writer);
    	      //outputter.output(document, System.out);
    	      writer.close(); // close writer
    	   
	 }catch(Exception e){} 
	}
}
