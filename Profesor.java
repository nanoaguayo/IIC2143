

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Profesor {
	String Facultad;
	String Nombre;
	
public Profesor(String nombre,String facultad){
	Facultad=facultad;
	Nombre=nombre;
}
public boolean CalificarAlumno(String num_alumno,String ramo,double nota){
	 try {
             System.out.println(num_alumno);
             System.out.println(ramo);
             System.out.println(nota);
 	    Document document = null;
 	    Document document2=null;
 	    Element root2=null;
 	    Element root = null;
 	    File xmlFile = new File("data/Alumnos/"+num_alumno +"/Historial.txt");
 	    File xmlFile2 = new File("data/Status.txt");
 	    if(xmlFile.exists()) {
 	        // try to load document from xml file if it exist
 	        // create a file input stream
 	        FileInputStream fis = new FileInputStream(xmlFile);
 	        FileInputStream fis2 = new FileInputStream(xmlFile2);
 	        // create a sax builder to parse the document
 	        SAXBuilder sb = new SAXBuilder();
 	        // parse the xml content provided by the file input stream and create a Document object
 	        document = sb.build(fis);
 	        document2 = sb.build(fis2);
 	        
 	        // get the root element of the document
 	        
 	        root = document.getRootElement();
 	        root2=document2.getRootElement();
 	        fis.close();
                fis2.close();
 	    } else{return false;}
   boolean cambio=false;
   String periodo = root2.getChildText("periodo");
   List<Element> semestres = root.getChildren();
             for (Element aux : semestres) {
                 if (aux.getAttributeValue("periodo").equals(periodo)) {
                     //Vemos los ramos del periodo.
                     List<Element> ramos = aux.getChildren();
                     for (int j = 0; j<ramos.size(); j++) {
                         Element aux2 = ramos.get(j);
                         String notax=aux2.getAttributeValue("nota");
                         if (aux2.getText().equals(ramo) && notax.equals("0.0")) {
                             //El ramo no ha sido calificado y corresponde a la sigla.
                             aux.getChildren().get(j).setAttribute("nota", String.valueOf(nota));
                             cambio=true;
                         }
                     }
                 }
             }
  
   
   FileWriter writer = new FileWriter("data/Alumnos/"+num_alumno+"/Historial.txt");
   XMLOutputter outputter = new XMLOutputter();
   outputter.setFormat(Format.getPrettyFormat());
   outputter.output(document, writer);
   //outputter.output(document, System.out);
   writer.close(); // close writer
   if(cambio){
     return true;
      }
   else{
	   return false;
   }
       
 }
 catch (IOException e) {
   System.err.println(e);
   return false;
 } catch (JDOMException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	return false;
}
	
}

ArrayList<String> getRamos(){
	ArrayList<String> ramos = new ArrayList<String>();
	try {
 	    Document document = null;
 	    Element root = null;
 	    File xmlFile = new File("data/Profesores.txt");
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
 	    } else{return null;}
 	   List<Element> profesores = root.getChildren();
 	   for(int i=0;i<profesores.size();i++){
 		   Element aux = profesores.get(i);
 		   if(this.Nombre.equals(aux.getAttributeValue("Nombre"))){
 			   //Este es el profesor. Sacamos los ramos
 			   List<Element> Ramos = aux.getChildren();
 			   for(int j=0;j<Ramos.size();j++){
 				   String aux2 = Ramos.get(j).getText();
 				   ramos.add(aux2);
 			   }
 		   }
 	   }
	}
	catch(Exception e){e.printStackTrace();}
	
	
	
	return ramos;
}
ArrayList<String> getAlumnos(String ramo){
	ArrayList<String> alumnos = new ArrayList<String>();
	try {
 	    Document document = null;
 	    Element root = null;
 	    File xmlFile = new File("data/Listas/"+ramo+".txt");
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
 	    } else{return null;}
 	   List<Element> alumno = root.getChildren();
 	   for(int i=0;i<alumno.size();i++){
 		   Element aux = alumno.get(i);
 		   alumnos.add(aux.getAttributeValue("num_alumno"));
 	   }
	}
	catch(Exception e){e.printStackTrace();}
	
	
	
	return alumnos;
}
}
