package mainproyectosoft;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.beans.property.SimpleStringProperty;

public class RamoAvanceFX {
	SimpleStringProperty Nombre;
	SimpleStringProperty Sigla;
	SimpleStringProperty Nota;
        SimpleStringProperty Periodo;
	
	public RamoAvanceFX(String nombre,String sigla,double nota){
		
		this.Nombre= new SimpleStringProperty(nombre);
		this.Sigla= new SimpleStringProperty(sigla);
                if (nota ==0.0){
                    this.Nota = new SimpleStringProperty("P");
                }else{
                    this.Nota = new SimpleStringProperty(String.valueOf(nota));
                }
		           
                this.Periodo = new SimpleStringProperty("");
	}
        public String getNombre(){
            return this.Nombre.get();
        }
        public String getSigla(){
            return this.Sigla.get();
        }
        public String getNota(){
            return this.Nota.get();
        }
        public String getPeriodo(){
            return this.Periodo.get();
        }
        void setNombre(String nombre){
            Nombre.set(nombre);
        }
        void setNota(String nota){
            Nota.set(nota);
        }
        void setSigla(String sigla){
            Sigla.set(sigla);
        }
        void setPeriodo(String periodo){
            Periodo.set(periodo);
        }
}
