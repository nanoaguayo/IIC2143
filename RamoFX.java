package mainproyectosoft;

import javafx.beans.property.SimpleStringProperty;

public class RamoFX {
	
	
	SimpleStringProperty Sigla;
	SimpleStringProperty Retirable;
	SimpleStringProperty Seccion;
	SimpleStringProperty Horario;
	SimpleStringProperty Nombre;
        SimpleStringProperty Profesor;
	SimpleStringProperty Facultad;
	SimpleStringProperty Creditos;
	SimpleStringProperty Comentario;
	
	public RamoFX(String sigla,String retirable,String seccion,String horario,String nombre,String creditos,String comentario,String facultad){
		
		this.Nombre= new SimpleStringProperty(nombre);
                if (retirable.equals("true")){
                    this.Retirable = new SimpleStringProperty("Si");
                } else {
                    this.Retirable = new SimpleStringProperty("No");
                }
		this.Sigla= new SimpleStringProperty(sigla);
		this.Seccion = new SimpleStringProperty(seccion);
		this.Horario= new SimpleStringProperty(horario);
		this.Facultad= new SimpleStringProperty(facultad);
		this.Creditos= new SimpleStringProperty(creditos);
		this.Comentario = new SimpleStringProperty(comentario);
                this.Profesor = new SimpleStringProperty("");
	}
	
        public String getNombre(){
            return this.Nombre.get();
        }
        void setNombre(String nombre){
            Nombre.set(nombre);
        }
        public String getSigla(){
            return this.Sigla.get();
        }
        public String getSeccion(){
            return this.Seccion.get();
        }
        
        void setSeccion(String nota){
            Seccion.set(nota);
        }
        void setSigla(String sigla){
            Sigla.set(sigla);
        }
        void setRetirable(String retirable){
        	Retirable.set(retirable);
        }
        public String getRetirable(){
        	return Retirable.get();
        }
        void setHorario(String horario){
        	Horario.set(horario);
        }
        public String getHorario(){
        	return Horario.get();
        }
        void setFacultad(String facultad){
        	Facultad.set(facultad);
        }
        public String getFacultad(){
        	return Facultad.get();
        }
        void setCreditos(String creditos){
        	Creditos.set(creditos);
        }
        public String getCreditos(){
        	return Creditos.get();
        }
        void setComentario(String comentario){
        	Comentario.set(comentario);
        }
        public String getComentario(){
        	return Comentario.get();
        }
        void setProfesor(String profesor){
        	Profesor.set(profesor);
        }
        public String getProfesor(){
        	return Profesor.get();
        }
	
}
