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
    
    String carreratemp;
public static void main(String[] args) {
    	
    	Administrador admin = new Administrador("AA",22,"M","12","aa","bb");
    	admin.EscribirRequisitos1("Ingenieria", "IIC2143", "ICS3413");
    	
    	/*SistemaRead sr = new SistemaRead();
    	ArrayList<String> aux=sr.getMallas("Arquitectura");
    	Iterator<String> it = aux.iterator();
    	while(it.hasNext()){
    		System.out.println(it.next());
    	}*/
 }


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
public ArrayList<String> getRamosMalla(String carrera,String malla){
	ArrayList<String> ramos2 = new ArrayList<>();
	
	 try {
 	    Document document = null;
 	    Document document2 = null;
 	    Element root = null;
 	    Element root2=null;
 	    File xmlFile = new File("data/Carreras/"+carrera+"/Malla_Curricular.txt");
 	    File xmlFile2 = new File("data/Carreras/"+carrera+"/Ramos.txt");
 	    
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
 	        root2 = document2.getRootElement();
 	        fis.close();
 	        fis2.close();
 	        
 	    } else{return null;}
 	   List<Element> Ramos = root2.getChildren();
	   for(int i=0;i<Ramos.size();i++){
		   String name=Ramos.get(i).getChildText("sigla");
		   ramos2.add(name);
		   System.out.println(name);
		   
	   }
 	   List<Element> mallas = root.getChildren();
 	   
 	   for(int i=0;i<mallas.size();i++){
 		   String name=mallas.get(i).getAttributeValue("nombre");
 		   if(name.equals(malla)){
 			   System.out.println(name+"---"+malla);
 			   //esta es la carrera
 			   List<Element> ramosmalla = mallas.get(i).getChildren();
 			   for(int j=0;j<ramosmalla.size();j++){
 				   String aux = ramosmalla.get(j).getText();
 				   ramos2.remove(aux);
 			   }
 		   }
 	   }
 	   
 	 
 	   
	 }
 	 catch(Exception e){}
	 
	
	
	return ramos2;
}

public ArrayList<String> getRamosMalla2(String carrera,String malla){
	ArrayList<String> ramos2 = new ArrayList<>();
	
	 try {
 	    Document document = null;
 	    Document document2 = null;
 	    Element root = null;
 	    Element root2=null;
 	    File xmlFile = new File("data/Carreras/"+carrera+"/Malla_Curricular.txt");
 	    File xmlFile2 = new File("data/Carreras/"+carrera+"/Ramos.txt");
 	    
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
 	        root2 = document2.getRootElement();
 	        fis.close();
 	        fis2.close();
 	        
 	    } else{return null;}
 	   List<Element> Ramos = root2.getChildren();
	   for(int i=0;i<Ramos.size();i++){
		   String name=Ramos.get(i).getChildText("sigla");
		   ramos2.add(name);
		   System.out.println(name);
		   
	   }
 	   
 	 
 	   
	 }
 	 catch(Exception e){}
	 
	
	
	return ramos2;
}

public void CambiarPeriodo(){
	try {
	    Document document = null;
	    Element root = null;
	    
	    File xmlFile = new File("data/Status.txt");
	    
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
	    } else{}
  String anofin="";
  String semfin="";
  String periodo=root.getChild("periodo").getText();
  String ano = periodo.substring(0, 4);
  int anox = Integer.valueOf(ano);
  String semestre = periodo.substring(5,6);
  if(semestre.equals("1")){
	  semfin="-2";
	  anofin=ano;
	  anofin=anofin.concat(semfin);
  } 
  else{
	  anox++;
	  anofin=String.valueOf(anox);
	  anofin=anofin.concat("-1");
  }
  root.getChild("periodo").setText(anofin);
  root.getChild("tomaramos").setText("true");
  FileWriter writer = new FileWriter("data/Status.txt");
  XMLOutputter outputter = new XMLOutputter();
  outputter.setFormat(Format.getPrettyFormat());
  outputter.output(document, writer);
  //outputter.output(document, System.out);
  writer.close(); // close writer
      
}
	catch(Exception e){e.printStackTrace();}

}

public boolean getEstado(){
	boolean estado=true;
	try {
	    Document document = null;
	    Element root = null;
	    
	    File xmlFile = new File("data/Status.txt");
	    
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
	    } else{}
	    
  String periodo=root.getChild("tomaramos").getText();
  if(periodo.equals("true")){
	  estado=true;
  }
  else{
	  estado=false;
  }
	}catch(Exception e){
		
	}
	
	return estado;
}

void setFalseTomaRamos(){
	
	try {
	    Document document = null;
	    Element root = null;
	    
	    File xmlFile = new File("data/Status.txt");
	    
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
	    } else{}
	    
  root.getChild("tomaramos").setText("false");
  FileWriter writer = new FileWriter("data/Status.txt");
  XMLOutputter outputter = new XMLOutputter();
  outputter.setFormat(Format.getPrettyFormat());
  outputter.output(document, writer);
  //outputter.output(document, System.out);
  writer.close(); // close writer
  
 
	}catch(Exception e){
		
	}
}

public boolean CheckNumeroAlumno(String numero){
	
	File file = new File("data/Alumnos/");
	String[] names = file.list();

	for(String name : names)
	{
	    System.out.println(name);
		if (new File("data/Alumnos/" + name).isDirectory() && name.equals(numero))
	    {
	       return false;
	    }
	}
	
	return true;
}

public ArrayList<String> getAlumnos(String num){
	ArrayList<String> lista = new ArrayList<>();
	
	 try {
 	    Document document = null;
 	    
 	    Element root = null;
 	    
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
 	       
 	        
 	    } else{return null;}
 	   List<Element> alumnos = root.getChildren();
	   for(int i=0;i<alumnos.size();i++){
		   String name=alumnos.get(i).getChildText("nombre");
		   String numero = alumnos.get(i).getChildText("numero_alumno");
		   //System.out.println(name+"--"+numero);
		   if(!numero.equals(num))
		   {	
			   //System.out.println("entro");
			   String put = name.concat("-").concat(numero);
			   lista.add(put);
			   System.out.println(put);
		   }
	   }
 	   
 	 
 	   
	 }
 	 catch(Exception e){}
	 
	
	
	return lista;
}

}
	

