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
	    Document document2 = null;
		File xmlFile2 = new File("data/Carreras/"+carrera+"/Ramos.txt");
	    Element root2 = null;
	    try{
	    if(xmlFile.exists()) {
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
				   
				   semestre[i]= new Ramo(Nombre,aux,Horario,Sala,Facultad,Integer.valueOf(Creditos),Double.valueOf(nota),Boolean.valueOf(retirable),Integer.valueOf(seccion),comentario);	   
				   
			   }
			 }
			//Ver requisitos, si no se sale
			if(test.checkRequisitos(this, semestre[i])){}
			else{return false;}
		}
		//Paso el chequeo y solo agrego los ramos.
		Element element = new Element("Semestre");
		element.setAttribute("periodo",periodo);
		xmlFile = new File("data/Carreras/"+carrera+"/Ramos.txt");
		for(int i=0;i<semestre.length;i++){
		
	    
	      Element element2 = new Element("Ramo");
	      element2.setAttribute("nota", "0.0");
	      element2.setText(semestre[i].Sigla);
	      element.addContent(element2);
	      
	     
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

}
