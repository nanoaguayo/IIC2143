package mainproyectosoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Alumno extends Usuario{
	int numero_alumno;
        String carreratemp;
	
	public Alumno(String nombre,int edad, String sexo, String rut,String id, String pass,int num){
		super(nombre,edad,sexo,rut,id,pass);
		numero_alumno=num;
	}
	
	void PrintData(){
		System.out.println(Nombre);
		System.out.println(Edad);
		System.out.println(Sexo);
		System.out.println(Rut);
		System.out.println(id_usuario);
		System.out.println(Password);
		System.out.println(numero_alumno);
	}
	boolean Tomar_Semestre(ArrayList<String> ramo){
		Iterator<String> RI = ramo.iterator();
		int largo = ramo.size();
		String[] ramos = new String[largo];
		int c=0;
		while(RI.hasNext()){
			ramos[c]=RI.next();
			c++;
		}
		String periodo="";
		Document document = null;
		Document documentx = null;
		File xmlFile = new File("data/Alumnos/"+numero_alumno+"/Historial.txt");
		File xmlFilex = new File("data/Status.txt");
	    Element root = null;
	    Element rootx=null;
	    try{
	    if(xmlFile.exists()) {
	        // try to load document from xml file if it exist
	        // create a file input stream
	        FileInputStream fis = new FileInputStream(xmlFile);
	        FileInputStream fis2 = new FileInputStream(xmlFilex);
	        // create a sax builder to parse the document
	        SAXBuilder sb = new SAXBuilder();
	        // parse the xml content provided by the file input stream and create a Document object
	        document = sb.build(fis);
	        documentx = sb.build(fis2);
	        
	        // get the root element of the document
	        
	        root = document.getRootElement();
	        rootx=documentx.getRootElement();
	        fis.close();
	        fis2.close();
	    } 
	    else{
	    	return false;}
	    }
	    catch(Exception e){e.printStackTrace();}
	    Element Aux = rootx.getChild("periodo");
	    periodo=Aux.getText();
	    String carrera= root.getAttributeValue("carrera");
	    String maximo = rootx.getChildText("credxsemestre");
	    int maximoxsemestre = Integer.valueOf(maximo);
	    int creditosdesemestre=0;
	    Document document2 = null;
		File xmlFile2 = new File("data/Carreras/"+carrera+"/Ramos.txt");
	    Element root2 = null;
	    try{
	    if(xmlFile2.exists()) {
	        // try to load document from xml file if it exist
	        // create a file input stream
	        FileInputStream fis = new FileInputStream(xmlFile2);
	 
	        // create a sax builder to parse the document
	        SAXBuilder sb = new SAXBuilder();
	        // parse the xml content provided by the file input stream and create a Document object
	        document2 = sb.build(fis);
	        
	        // get the root element of the document
	        
	        root2 = document2.getRootElement();
	        fis.close();
	    } 
	    else{
	    	return false;}
	    }
	    catch(Exception e){e.printStackTrace();}
	    try{
		Ramo[] semestre = new Ramo[largo];
		List<Element> ramos_txt =root2.getChildren();
		System.out.println(ramos_txt.size()+"---"+largo);
		Requisitos test = new Requisitos();
		for(int i=0;i<largo;i++){
			String aux = ramos[i];
			for(int j=0;j<ramos_txt.size();j++){
			   Element aux2=ramos_txt.get(j);
			   System.out.println(aux2.getChild("sigla").getText()+"---"+aux);
			   if(aux2.getChild("sigla").getText().equals(aux)){
				   System.out.println(i+"-+-+-"+j);
				   String Nombre = aux2.getChild("nombre").getText();
				   String Horario = aux2.getChild("horario").getText();
				   String Sala= aux2.getChild("sala").getText();
				   String Facultad=aux2.getChild("facultad").getText();
				   String Creditos= aux2.getChild("creditos").getText();
				   String nota= aux2.getChild("nota").getText();
				   String retirable= aux2.getChild("retirable").getText();
				   String seccion= aux2.getChild("seccion").getText();
				   String comentario= aux2.getChild("comentario").getText();
				   creditosdesemestre+=(Integer.valueOf(Creditos));
				   semestre[i]= new Ramo(Nombre,aux,Horario,Sala,Facultad,Integer.valueOf(Creditos),Double.valueOf(nota),Boolean.valueOf(retirable),Integer.valueOf(seccion),comentario);	   
				   
			   }
			 }
			//Ver requisitos, si no se sale
			if(test.checkRequisitos(this, semestre[i])){}
			else{return false;}
		}
		//Reviso que no exceda los creditos maximos
		if(creditosdesemestre>maximoxsemestre){
			//Excede
			return false;
			
		}
		//Paso el chequeo y solo agrego los ramos y al alumno a la lista.
		Element alumno = new Element("alumno");
		alumno.setText(this.id_usuario);
		alumno.setAttribute("num_alumno",String.valueOf(this.numero_alumno));
		
		Element element = new Element("Semestre");
		element.setAttribute("periodo",periodo);
		xmlFile = new File("data/Carreras/"+carrera+"/Ramos.txt");
		for(int i=0;i<semestre.length;i++){
		
	    
	      Element element2 = new Element("Ramo");
	      element2.setAttribute("nota", "0.0");
	      element2.setText(semestre[i].Sigla);
	      element.addContent(element2);
	      
	      //anotamos en lista
	      Document document3 = null;
			File xmlFile3 = new File("data/Listas/"+semestre[i].Sigla+".txt");
		    Element root3 = null;
		    try{
		    if(xmlFile3.exists()) {
		        // try to load document from xml file if it exist
		        // create a file input stream
		        FileInputStream fis = new FileInputStream(xmlFile3);
		 
		        // create a sax builder to parse the document
		        SAXBuilder sb = new SAXBuilder();
		        // parse the xml content provided by the file input stream and create a Document object
		        document3 = sb.build(fis);
		        
		        // get the root element of the document
		        
		        root3 = document3.getRootElement();
		        fis.close();
		    }
		      root3.addContent(alumno);
		      FileWriter writer3 = new FileWriter("data/Listas/"+semestre[i].Sigla+".txt");
		      
		      XMLOutputter outputter = new XMLOutputter();
		      outputter.setFormat(Format.getPrettyFormat());
		      outputter.output(document3, writer3);
		     
		      //outputter.output(document, System.out);
		      writer3.close(); // close writer
		    }
		    catch(Exception e){e.printStackTrace();}
	      
	     
		}
		root.addContent(element);
	    }catch(Exception e){return false;}
		try{
			 FileWriter writer = new FileWriter("data/Alumnos/"+this.numero_alumno+"/Historial.txt");
		      
		      XMLOutputter outputter = new XMLOutputter();
		      outputter.setFormat(Format.getPrettyFormat());
		      outputter.output(document, writer);
		     
		      //outputter.output(document, System.out);
		      writer.close(); // close writer
			
		}catch(Exception e){e.printStackTrace();}
		
		return true;
	}
        public String[] getCarrera(){
		String[] carreras = new String[2];
		Document document2 = null;
		File xmlFile2 = new File("data/Alumnos/"+numero_alumno+"/Historial.txt");
	    Element root2 = null;
	    try{
	    if(xmlFile2.exists()) {
	        // try to load document from xml file if it exist
	        // create a file input stream
	        FileInputStream fis = new FileInputStream(xmlFile2);
	 
	        // create a sax builder to parse the document
	        SAXBuilder sb = new SAXBuilder();
	        // parse the xml content provided by the file input stream and create a Document object
	        document2 = sb.build(fis);
	        
	        // get the root element of the document
	        
	        root2 = document2.getRootElement();
	        fis.close();
	    } 
	    }
	    catch(Exception e){e.printStackTrace();}
		carreras[0]=root2.getAttributeValue("carrera");
		carreras[1]="";
		
		return carreras;
	}
	@SuppressWarnings("null")
	public ArrayList<Malla_Curricular> getMallas(String carrera){
		ArrayList<Malla_Curricular> carreras = new ArrayList<Malla_Curricular>();
		Document document2 = null;
		File xmlFile2 = new File("data/Carreras/"+carrera+"/Malla_Curricular.txt");
	    Element root2 = null;
	    try{
	    if(xmlFile2.exists()) {
	        // try to load document from xml file if it exist
	        // create a file input stream
	        FileInputStream fis = new FileInputStream(xmlFile2);
	 
	        // create a sax builder to parse the document
	        SAXBuilder sb = new SAXBuilder();
	        // parse the xml content provided by the file input stream and create a Document object
	        document2 = sb.build(fis);
	        
	        // get the root element of the document
	        
	        root2 = document2.getRootElement();
	        fis.close();
	    } 
	    }
	    catch(Exception e){e.printStackTrace();}
		List<Element> mallas = root2.getChildren();
		for(int i=0;i<mallas.size();i++){
			String aux = mallas.get(i).getAttributeValue("nombre");
			Malla_Curricular Aux = new Malla_Curricular(carrera,aux);
			carreras.add(Aux);
		}
		
		return carreras;
	}
//Revisa si semestre esta calificado
public boolean CheckEstadoSemestre(){
	
		try {
		    Document document = null;
		    Element root = null;
		    
		    File xmlFile = new File("data/Alumnos/"+this.numero_alumno+"/Historial.txt");
		    
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
		    
		    List<Element> semestres = root.getChildren();
		    for(int i=0;i<semestres.size();i++){
		    	List<Element> aux = semestres.get(i).getChildren();
		    	for(int j=0;j<aux.size();j++){
		    		Element aux2 = aux.get(j);
		    		if(aux2.getAttributeValue("nota").equals("0.0")){
		    			return false;
		    		}
		    	}
		    }
		}
		catch(Exception e){
			return false;
		}
		
	
	
	return true;
}

public boolean MandarMensaje(String nalumno, String mensaje){
	String file1 = this.numero_alumno+"-"+nalumno+".txt";
	String file2 = nalumno+"-"+this.numero_alumno+".txt";
	int existe=0;
	try {
	    Document document = null;
	    Element root = null;
	    
	    File xmlFile = new File(file1);
	    File xmlFile2 = new File(file2);
	    
	    if(xmlFile.exists()) {
	    	existe=1;
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
	    } else if(xmlFile2.exists())
	    {
	    	existe=2;
	    	// try to load document from xml file if it exist
	        // create a file input stream
	        FileInputStream fis = new FileInputStream(xmlFile2);
	 
	        // create a sax builder to parse the document
	        SAXBuilder sb = new SAXBuilder();
	        // parse the xml content provided by the file input stream and create a Document object
	        document = sb.build(fis);
	        
	        // get the root element of the document
	        
	        root = document.getRootElement();
	        fis.close();
	    	
	    }
	    else{
	    	
	    }
	   
	    if(existe>0){
	    	//Existe algun archivo y se leyo root
	    	Element message = new Element("Mensaje");
	    	Attribute emisor = new Attribute("emisor",this.Nombre);
	    	message.setAttribute(emisor);
	    	Element content = new Element("Contenido");
	    	content.setText(mensaje);
	    	message.addContent(content);
	    	root.addContent(message);
	    	
	    	FileWriter writer3;
	    	if(existe==1){
	    		writer3=new FileWriter(file1);
	    	}else{
	    		writer3= new FileWriter(file2);
	    	}
		      
		    XMLOutputter outputter = new XMLOutputter();
		    outputter.setFormat(Format.getPrettyFormat());
		    outputter.output(document, writer3);
		     
		      //outputter.output(document, System.out);
		    writer3.close(); // close writer
	    	
	    	
	    }
	    else{
	    	
	    	Element base = new Element("Chat");
	    	Element message = new Element("Mensaje");
	    	Attribute emisor = new Attribute("emisor",this.Nombre);
	    	message.setAttribute(emisor);
	    	Element content = new Element("Contenido");
	    	content.setText(mensaje);
	    	message.addContent(content);
	    	base.addContent(message);
	    	
	    	Document document0 = new Document();
	    	document0.addContent(base);
	    	FileWriter writer3=new FileWriter(file1);;
		      
		    XMLOutputter outputter = new XMLOutputter();
		    outputter.setFormat(Format.getPrettyFormat());
		    outputter.output(document0, writer3);
		     
		    //outputter.output(document, System.out);
		    writer3.close(); // close writer
	    	
	    }
	 
	   
	}
	catch(Exception e){
		return false;
	}
	
	return true;
}

public ArrayList<InboxFX> GetMensajes(String nalumno){
	String file1 = this.numero_alumno+"-"+nalumno+".txt";
	String file2 = nalumno+"-"+this.numero_alumno+".txt";
	ArrayList<InboxFX> res;

	try {
	    Document document = null;
	    Element root = null;
	    
	    File xmlFile = new File(file1);
	    File xmlFile2 = new File(file2);
	    
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
	    } else if(xmlFile2.exists())
	    {
	    	
	    	// try to load document from xml file if it exist
	        // create a file input stream
	        FileInputStream fis = new FileInputStream(xmlFile2);
	 
	        // create a sax builder to parse the document
	        SAXBuilder sb = new SAXBuilder();
	        // parse the xml content provided by the file input stream and create a Document object
	        document = sb.build(fis);
	        
	        // get the root element of the document
	        
	        root = document.getRootElement();
	        fis.close();
	    	
	    }
	    else{
	    	return null;
	    }
	    
	   List<Element> lista = root.getChildren();
	   res= new ArrayList<InboxFX>();
	   
	   for(int i=0;i<lista.size();i++){
		   String emi = lista.get(i).getAttributeValue("emisor");
		   String mensaje = lista.get(i).getChildText("Contenido");
		   InboxFX aux = new InboxFX(emi,mensaje);
		   res.add(aux);
	   }
	   
	    
	   
	}
	catch(Exception e){
		return null;
	}
	
	return res;
}

}
