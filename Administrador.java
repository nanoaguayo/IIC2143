

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

import java.util.List;  




public class Administrador extends Usuario{
    
        int MaxCr=50;
        String carreratemp;
	
	
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
      
	
Boolean CrearMalla (Carrera carrera, String nombreMalla)
{
	
	
    try {
    	    Document document = null;
    	    //Document document2 = new Document();
    	    Element root = null;
    	    //boolean a = new File("data/Alumnos/"+num_alumno).mkdir();
    	    //if(!a){return false;}
    	    File xmlFile = new File("data/Carreras/"+carrera.Nombre+"/Malla_Curricular.txt");
    	    
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
     
      Element element = new Element("Malla");
      
      
     
      //Element rootHist = new Element("Historial");
      Attribute carrera_att = new Attribute ("carrera", carrera.Nombre);
      Attribute malla_att = new Attribute ("nombre", nombreMalla);
      element.setAttribute(carrera_att);
      element.setAttribute(malla_att);
      root.addContent(element);
    
      //document2.setContent(rootHist);
      FileWriter writer = new FileWriter("data/Carreras/"+carrera.Nombre+"/Malla_Curricular.txt");
      //FileWriter writer2 = new FileWriter("data/Alumnos/"+num_alumno+"/Historial.txt");
      XMLOutputter outputter = new XMLOutputter();
      outputter.setFormat(Format.getPrettyFormat());
      outputter.output(document, writer);
      //outputter.output(document2,writer2);
      //outputter.output(document, System.out);
      writer.close(); // close writer
      //writer2.close();
          
    }
    catch (IOException | JDOMException e) {
      System.err.println(e);
      return false;
    }
	
	return true;
}




Boolean AgregarRamoMalla (Malla_Curricular malla, String siglaRamo)
{
    try {
	    Document document = null;
	    
	    Element root = null;
	    //boolean a = new File("data/Alumnos/"+num_alumno).mkdir();
	    //if(!a){return false;}    	    
	    File xmlFile = new File("data/Carreras/"+malla.Nombre+"/Malla_Curricular.txt");
	    
	    
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

  List<Element> mallasList = root.getChildren("Malla");  
  
  for (int i=0 ; i < mallasList.size(); i++)
  {
     String nombreMalla = mallasList.get(i).getAttributeValue("nombre");
     
     if(nombreMalla.equals(malla.Especialidad))
     {
    	Element element = mallasList.get(i);
        Element element2 = new Element("Ramo");
        element2.setText(siglaRamo);
        element.addContent(element2);
        
        FileWriter writer = new FileWriter("data/Carreras/"+malla.Nombre+"/Malla_Curricular.txt");
        //FileWriter writer2 = new FileWriter("data/Alumnos/"+num_alumno+"/Historial.txt");
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, writer);
        //outputter.output(document2,writer2);
        //outputter.output(document, System.out);
        writer.close(); // close writer
        //writer2.close();
     }
	 
  }

}
catch (IOException | JDOMException e) {
  System.err.println(e);
  return false;
}

	
	return true;
}
	
int VerificarRamo(String profesor, Ramo r){
     // Profesor no puede dictar dos ramos a la misma hora y no puede haber 2 salas a la misma hora.
	Buscador buscar = new Buscador();
	boolean horario=false;
	boolean salas = false;
	boolean sigla=false;
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
	    } else{return -1;}
 
	    List <Element> profesores= root.getChildren();
	    Element p=null;
	    for(int i=0;i<profesores.size();i++){
	    	Element aux = profesores.get(i);
	    	if(aux.getAttributeValue("Nombre").equals(profesor)){
	    		p=aux;
	    	}
	    }
	    if(p!=null){
	    	List<Element> ramos = p.getChildren();
	    	Document doc2 = null;
	    	File xml2 = new File("data/Carreras/"+r.Facultad+"/Ramos.txt");
	    	FileInputStream fis2 = new FileInputStream(xml2);
	    	SAXBuilder sb2 = new SAXBuilder();
	    	doc2=sb2.build(fis2);
	    	Element root2 = doc2.getRootElement();
	    	fis2.close();
	    	
	    	//chequeamos si el mismo profesor tendria 2 ramos a misma hora.
	    	System.out.println("size="+root2.getChildren().size());
	    	for(int i=0;i<root2.getChildren().size();i++){
	    		Element aux = root2.getChildren().get(i);
	    		if(aux.getChildText("sigla").equals(r.Sigla)){
	    			sigla=true;
	    		}
	    		for(int j=0;j<ramos.size();j++){
	    			System.out.println(aux.getChild("sigla").getText()+"---"+ramos.get(j).getText()+"///"+aux.getChild("horario").getText()+"---"+r.Horario);
	    		if(buscar.contieneHorarios(aux.getChild("horario").getText(),(r.Horario)) && ramos.get(j).getText().equals(aux.getChild("sigla").getText()))
	    		{System.out.println("match horario");horario=true;}
	    		}
	    		if(r.Sala.equals(aux.getChild("sala").getText()) && buscar.contieneHorarios(r.Horario,aux.getChildText("horario")))
	    		{System.out.println("match sala");salas=true;}
	    	}
	    }//p!=null
}
catch (IOException | JDOMException e) {
  System.err.println(e);
  return -1;
}
if(salas)
{return 1;}
else if(sigla){
	return 2;
}
else if(horario){
	return 3;
}
else{
return 0;}
        }
        



int CrearRamo(String Sigla,String Nombre, String Horario, String Sala, String Facultad, int Creditos, double Nota, boolean Retirable, int Seccion, String Comentario,String profesor){
    //Verificar si cumple todo   
	Ramo r = new Ramo(Nombre,Sigla,Horario,Sala,Facultad,Creditos,Nota,Retirable,Seccion,Comentario);
	int test =VerificarRamo(profesor,r);
	if(test==0){
	String retirablex="false";
	if(Retirable==true){retirablex="true";}
	try {
	    Document document = null;
	    Document document3=null;
	    Element root = null;
	    Element root2 = null;
	    File xmlFile = new File("data/Carreras/"+Facultad+"/Ramos.txt");
	    File xmlFile2 = new File("data/Profesores.txt");
	    if(xmlFile.exists()) {
	        // try to load document from xml file if it exist
	        // create a file input stream
	        FileInputStream fis = new FileInputStream(xmlFile);
	        FileInputStream fis2 = new FileInputStream(xmlFile2);
	 
	        // create a sax builder to parse the document
	        SAXBuilder sb = new SAXBuilder();
	        // parse the xml content provided by the file input stream and create a Document object
	        document = sb.build(fis);
	        document3 = sb.build(fis2);
	        
	        // get the root element of the document
	        
	        root = document.getRootElement();
	        root2 = document3.getRootElement();
	        fis.close();
	    } else{System.out.println("No existe");return -1;}
 
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
	    Element element10 = new Element("comentario");
	    element10.setText(Comentario);
	    element.addContent(element2);
	    element.addContent(element3);
	    element.addContent(element4);
	    element.addContent(element5);
	    element.addContent(element6);
	    element.addContent(element7);
	    element.addContent(element8);
	    element.addContent(element9);
	    element.addContent(element10);
	    root.addContent(element);
	    //Creamos el archivo del foro y agregamos el ramo al profesor.  Ademas, debemos crear la lista del ramo
	   
	    Document document2=new Document();
	    Element rootHist = new Element("Foro");

	    document2.setContent(rootHist);
	      
	    FileWriter writer2 = new FileWriter("data/Foro/"+Sigla+".txt");
	    XMLOutputter outputter = new XMLOutputter();
	    outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document2,writer2);
        writer2.close();
        //Agregamos el ramo al profesor.
        List<Element> profesores = root2.getChildren();
        Element x=null;
        int AUX=0;
        for(int i=0;i<profesores.size();i++){
        	String profe = profesores.get(i).getAttributeValue("Nombre");
        	System.out.println(profe+"--"+profesor);
        	if(profe.equals(profesor)){
        		System.out.println("entro");
        		x = new Element("ramo");
        		x.setText(Sigla);
        		
        		AUX=i;
        	}
        }
        Element y = profesores.get(AUX);
        y.addContent(x);
        
        FileWriter writer3 = new FileWriter("data/Profesores.txt");
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document3, writer3);

        //outputter.output(document, System.out);
        writer3.close(); // close writer
 
        FileWriter writer = new FileWriter("data/Carreras/"+Facultad+"/Ramos.txt");
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, writer);

        //outputter.output(document, System.out);
        writer.close(); // close writer
        
   
        
        
        //Creamos lista del ramo
        Document document4=new Document();
	    Element rootHist2 = new Element("lista");

	    document4.setContent(rootHist2);
	      
	    FileWriter writer4 = new FileWriter("data/Listas/"+r.Sigla+".txt");
	    XMLOutputter outputter4 = new XMLOutputter();
	    outputter4.setFormat(Format.getPrettyFormat());
        outputter4.output(document4,writer4);
        writer4.close();
        
 
      
}
catch (Exception e) {
  System.err.println(e);
  return -1;
}
return 0;
}
	else{
		//No se cumple horario o sala
		return test;}
}
   




void ModCantMaxCr(int cant){
            MaxCr = cant;
        }




void SetRamosReprobados(int max){
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
 
  root.getChild("reprobados").setText(String.valueOf(max));
  FileWriter writer = new FileWriter("data/Status.txt");
  XMLOutputter outputter = new XMLOutputter();
  outputter.setFormat(Format.getPrettyFormat());
  outputter.output(document, writer);
  //outputter.output(document, System.out);
  writer.close(); // close writer
      
}
	catch(Exception e){e.printStackTrace();}
}



void SetCreditosMaximos(int max){
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
 
  root.getChild("credxsemestre").setText(String.valueOf(max));
  FileWriter writer = new FileWriter("data/Status.txt");
  XMLOutputter outputter = new XMLOutputter();
  outputter.setFormat(Format.getPrettyFormat());
  outputter.output(document, writer);
  //outputter.output(document, System.out);
  writer.close(); // close writer
      
}
	catch(Exception e){e.printStackTrace();}
}

public boolean CrearCarrera(String carrera){
	SistemaRead s = new SistemaRead();
	ArrayList<String> carreras=s.getCarreras();
	if(carreras.contains(carrera)){
		//Ya existe el nombre
		return false;
	}
	
	try {
	    Document document = new Document();
	    Document document2 = new Document();
	    Document document3 = new Document();
	  
	    boolean a = new File("data/Carreras/"+carrera).mkdir();
	    if(!a){return false;}
	    
	    //Creamos archivos bases de la base de datos
	    Element mallas = new Element("Mallas");
	    document.addContent(mallas);
	    Element ramos = new Element("Ramos");
	    document3.addContent(ramos);
	    Element requisitos = new Element("Requisitos");
	    document2.addContent(requisitos);
	   
 
	    FileWriter writer = new FileWriter("data/Carreras/"+carrera+"/Malla_Curricular");
	    FileWriter writer2 = new FileWriter("data/Carreras/"+carrera+"/Requisitos");
	    FileWriter writer3 = new FileWriter("data/Carreras/"+carrera+"/Ramos");
	    XMLOutputter outputter = new XMLOutputter();
	    outputter.setFormat(Format.getPrettyFormat());
	    outputter.output(document, writer);
	    outputter.output(document2,writer2);
	    outputter.output(document3, writer3);
	    //outputter.output(document, System.out);
	    writer.close(); // close writer
	    writer2.close();
	    writer3.close();
}
catch (IOException e) {
  System.err.println(e);
  return false;
}


	
	return true;
}
public boolean EscribirRequisitos1(String Carrera,String RamoBase,String RamoRequisito){
	//Agrega como requisito de ramobase a ramoreq 
	//Ambos ramos deben ser de la Carrera
	try 
	{
	    Document document = null;
	    
	    Element root = null;
	   
	    File xmlFile = new File("data/Carreras/"+Carrera+"/Requisitos.txt");
	    
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
	 
	  boolean existe = false;
	  List<Element> requisitos = root.getChildren();
	  for(int i=0;i<requisitos.size();i++){
		  
		  if(requisitos.get(i).getAttributeValue("sigla").equals(RamoBase)){
			  existe=true;
			  Element ramoreq = new Element("RamoReq");
			  ramoreq.setText(RamoRequisito);
			  requisitos.get(i).addContent(ramoreq);
		  }
	  }
	  if(!existe)
	  {
		  Element ramobase = new Element("RamoBase");
		  Attribute sigla = new Attribute("sigla",RamoBase);
		  Element ramoreq= new Element("RamoReq");
		  ramoreq.setText(RamoRequisito);
		  ramobase.setAttribute(sigla);
		  ramobase.setContent(ramoreq);
	  }
	
	  document.setContent(root);
	  FileWriter writer = new FileWriter("data/Carreras/"+Carrera+"/Requisitos.txt");
	 
	  XMLOutputter outputter = new XMLOutputter();
	  outputter.setFormat(Format.getPrettyFormat());
	  outputter.output(document, writer);
	 
	  //outputter.output(document, System.out);
	  writer.close(); // close writer
	  
	      
	}
	catch (IOException | JDOMException e) {
	  System.err.println(e);
	  return false;
	}
	
	return true;
}

public boolean EscribirRequisitos2(String Carrera,String RamoBase,int creditos){
	//Agrega como requisito de ramobase los creditos 
	//Ramo debe ser de la Carrera
	try 
	{
	    Document document = null;
	    
	    Element root = null;
	   
	    File xmlFile = new File("data/Carreras/"+Carrera+"/Requisitos.txt");
	    
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
	 
	  boolean existe = false;
	  List<Element> requisitos = root.getChildren();
	  for(int i=0;i<requisitos.size();i++){
		  
		  if(requisitos.get(i).getAttributeValue("sigla").equals(RamoBase)){
			  Element requisito = new Element("CredReq");
			  if(requisitos.get(i).getChildren().contains(requisito)){
				 existe=true;
				 requisitos.get(i).getChildren().get(requisitos.get(i).getChildren().indexOf(requisito)).setText(String.valueOf(creditos));
			  }
			  else{
				  
			  existe=true;
			  Element credreq = new Element("CredReq");
			  credreq.setText(String.valueOf(creditos));
			  requisitos.get(i).addContent(credreq);
			  }
		  }
	  }
	  if(!existe)
	  {
		  Element ramobase = new Element("RamoBase");
		  Attribute sigla = new Attribute("sigla",RamoBase);
		  Element credreq= new Element("CredReq");
		  credreq.setText(String.valueOf(creditos));
		  ramobase.setAttribute(sigla);
		  ramobase.setContent(credreq);
	  }
	
	  document.setContent(root);
	  FileWriter writer = new FileWriter("data/Carreras/"+Carrera+"/Requisitos.txt");
	 
	  XMLOutputter outputter = new XMLOutputter();
	  outputter.setFormat(Format.getPrettyFormat());
	  outputter.output(document, writer);
	 
	  //outputter.output(document, System.out);
	  writer.close(); // close writer
	  
	      
	}
	catch (IOException | JDOMException e) {
	  System.err.println(e);
	  return false;
	}
	
	return true;
}

public boolean EliminarRequisitos(String Carrera,String RamoBase,String RamoRequisito){
	//Agrega como requisito de ramobase a ramoreq 
	//Ambos ramos deben ser de la Carrera
	try 
	{
	    Document document = null;
	    
	    Element root = null;
	   
	    File xmlFile = new File("data/Carreras/"+Carrera+"/Requisitos.txt");
	    
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
	 
	  List<Element> requisitos = root.getChildren();
	  for(int i=0;i<requisitos.size();i++){
		  
		  if(requisitos.get(i).getAttributeValue("sigla").equals(RamoBase))
		  {
			  List<Element> ramos = requisitos.get(i).getChildren();
			  for(int j=0;j<ramos.size();j++)
			  {
				  if(ramos.get(j).getText().equals(RamoRequisito))
				  {
					  requisitos.get(i).removeContent(j);
				  }
			  }
			 
		  }
	  }
	
	
	  document.setContent(root);
	  FileWriter writer = new FileWriter("data/Carreras/"+Carrera+"/Requisitos.txt");
	 
	  XMLOutputter outputter = new XMLOutputter();
	  outputter.setFormat(Format.getPrettyFormat());
	  outputter.output(document, writer);
	 
	  //outputter.output(document, System.out);
	  writer.close(); // close writer
	  
	      
	}
	catch (IOException | JDOMException e) {
	  System.err.println(e);
	  return false;
	}
	
	return true;
}

public boolean EliminarRamo(String Carrera,String RamoBase){
	//Borra RamoBase de Carrera
	
	try 
	{
	    Document document = null;
	    
	    Element root = null;
	   
	    File xmlFile = new File("data/Carreras/"+Carrera+"/Ramos.txt");
	    
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
	 
	  List<Element> ramos = root.getChildren();
	  for(int i=0;i<ramos.size();i++){
		  List<Element> aux = ramos.get(i).getChildren();
		  Element sigla = aux.get(1);
		  System.out.println(sigla);
		  if(sigla.getText().equals(RamoBase)){
			  //eliminamos
			  ramos.remove(ramos.get(i));
		  }
	  }
	
	
	  document.setContent(root);
	  FileWriter writer = new FileWriter("data/Carreras/"+Carrera+"/Ramos.txt");
	 
	  XMLOutputter outputter = new XMLOutputter();
	  outputter.setFormat(Format.getPrettyFormat());
	  outputter.output(document, writer);
	 
	  //outputter.output(document, System.out);
	  writer.close(); // close writer
	  
	      
	}
	catch (IOException | JDOMException e) {
	  System.err.println(e);
	  return false;
	}
	
	return true;
}

}