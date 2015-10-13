
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Administrador extends Usuario{
	
	
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
        
        Boolean VerificarRamo(Profesor p, Ramo r){
            // Profesor no puede dictar dos ramos a la misma hora
    try {
      File inputFile = new File("data/Profesores.txt"); 	    	
      DocumentBuilderFactory docFactory =
      DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = 
      docFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(inputFile);
      String[] ListaRamos = null;
      NodeList nList = doc.getElementsByTagName("Profesor");
      for(int i=0; i<nList.getLength() ;i++){
          Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
                if (eElement.getAttribute("Nombre").equals(p.Nombre)){
                    NodeList nListaRamos = nNode.getChildNodes();
                    ListaRamos = new String[nListaRamos.getLength()];
                    for (int j = 0; j<ListaRamos.length; j++){
                        ListaRamos[j] = nListaRamos.item(j).getTextContent(); //ListaRamos contiene ramos que dicta profesor
                    }
                }
            }
        }
      for (int i = 0; i<ListaRamos.length ; i++){
          String ramo = ListaRamos[i];
          NodeList nList2 = doc.getElementsByTagName("sigla");
          for (int j = 0; j<nList2.getLength() ; j++){
              Node nRamo = nList2.item(i);
              String horario = nRamo.getNextSibling().getTextContent();
              if (horario == r.Horario){
                  return false;
              }
          }
      }
            // dos ramos no pueden dictarse en la misma sala y a la misma hora
      NodeList nList3 = doc.getElementsByTagName("sigla");
      for (int i = 0; i<nList3.getLength(); i++){
          Node nRamo2 = nList3.item(i);
          String horario = nRamo2.getNextSibling().getTextContent();
          String sala = nRamo2.getNextSibling().getNextSibling().getTextContent();
          if (horario == r.Horario && sala == r.Sala){
              return false;
          }
      }
      return true;
    } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
        

        }
        
        Boolean CrearRamo(String Sigla, String Horario, String Sala, String Facultad, int Creditos, double Nota, boolean Retirable, int Seccion, Semestre s, Profesor p){
            try {
                Ramo r = new Ramo(Sigla, Horario, Sala, Facultad, Creditos, Nota, Retirable, Seccion); //se crea instancia ramo
                if (!VerificarRamo(p, r)){ //se verifica la validez del ramo
                    return false;
                }
                // se agrega al xml
                String filepath = "data/"+Facultad+"Ramos.txt";
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(filepath);
                Node ramos = doc.getFirstChild();
                Element ramo = doc.createElement("ramo");
                ramos.appendChild(ramo);
                Node ramoactual = ramos.getLastChild();
                Element sigla = doc.createElement("sigla");
                Element horario = doc.createElement("horario");
                Element sala = doc.createElement("sala");
                Element facultad = doc.createElement("facultad");
                Element creditos = doc.createElement("creditos");
                Element nota = doc.createElement("nota");
                Element retirable = doc.createElement("retirable");
                Element seccion = doc.createElement("seccion");
                sigla.appendChild(doc.createTextNode(Sigla));
                horario.appendChild(doc.createTextNode(Horario));
                sala.appendChild(doc.createTextNode(Sala));
                facultad.appendChild(doc.createTextNode(Facultad));
                creditos.appendChild(doc.createTextNode(Integer.toString(Creditos)));
                nota.appendChild(doc.createTextNode(Double.toString(Nota)));
                retirable.appendChild(doc.createTextNode(String.valueOf(Retirable)));
                seccion.appendChild(doc.createTextNode(Integer.toString(Seccion)));
                ramoactual.appendChild(sigla);
                ramoactual.appendChild(horario);
                ramoactual.appendChild(sala);
                ramoactual.appendChild(facultad);
                ramoactual.appendChild(creditos);
                ramoactual.appendChild(nota);
                ramoactual.appendChild(retirable);
                ramoactual.appendChild(seccion);
                // Se agrega ramo a semestre
                s.agregarRamo(r);
                return true;
                
            }catch (Exception e) {
             e.printStackTrace();
             return false;
      }
            
                
        }
}

