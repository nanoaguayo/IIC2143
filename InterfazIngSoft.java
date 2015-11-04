/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz.ingsoft;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class InterfazIngSoft extends Application {

    Stage window;
    Scene scene0; // Menu Principal
    Scene scene1; // Menu Registro
    Scene scene2; // Menu Logueado

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Sistema Academico");

        //GridPane
        GridPane grid0 = new GridPane();
        grid0.setPadding(new Insets(20, 20, 20, 20));
        grid0.setVgap(8);
        grid0.setHgap(10);

        //Rut Label
        Label nameLabel = new Label("Rut:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Rut Input
        TextField nameInput = new TextField();
        nameInput.setPromptText("XX.XXX.XXX-X");
        GridPane.setConstraints(nameInput, 1, 0);

        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        //Password Input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);

        //Login
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> validarUsuario(nameInput.getText(), passInput.getText()));
        
        //Registrarse
        Button registerButton = new Button("Registrarse");
        GridPane.setConstraints(registerButton, 1, 3);
        registerButton.setOnAction(e -> window.setScene(scene1));

        //Agregando a grid
        grid0.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, registerButton);

        // scene0, grid0 = Menu Principal
        scene0 = new Scene(grid0, 280, 180);

        //fin menu principal
        
        // Menu Registro
        try{
            //GridPane
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(20, 20, 20, 20));
        grid1.setVgap(8);
        grid1.setHgap(10);
        
        
        //NombreLabel
        Label NombreRegistro = new Label("Nombre Completo:");
        GridPane.setConstraints(NombreRegistro, 0, 0);

        //Nombre Input
        TextField NombreInput = new TextField();
        GridPane.setConstraints(NombreInput, 1, 0);
        
        //Rut Label
        Label RegistroRut = new Label("Rut:");
        GridPane.setConstraints(RegistroRut, 0, 1);

        //Rut Input
        TextField RutRegistroInput = new TextField();
        RutRegistroInput.setPromptText("XX.XXX.XXX-X");
        GridPane.setConstraints(RutRegistroInput, 1, 1);

        //EdadLabel
        Label EdadRegistro = new Label("Edad:");
        GridPane.setConstraints(EdadRegistro, 0, 2);
        
        //EdadInput
        TextField EdadInput = new TextField();
        GridPane.setConstraints(EdadInput, 1, 2);
        
        //SexoLabel
        Label sexoLabel = new Label("Sexo:");
        GridPane.setConstraints(sexoLabel, 0, 3);
        
        //Sexo
        ChoiceBox<String> sexo = new ChoiceBox<>();
        sexo.getItems().addAll("Masculino", "Femenino");
        sexo.setValue("Masculino");
        GridPane.setConstraints(sexo, 1, 3);
        
        //CarreraLabel
        Label carreraLabel = new Label("Carrera:");
        GridPane.setConstraints(carreraLabel, 0, 4);
        
        //Carrera
        ChoiceBox<String> carrera = new ChoiceBox<>();
        carrera.getItems().add("Ingenieria");
        carrera.setValue("Ingenieria");
        GridPane.setConstraints(carrera, 1, 4);
        
        //Registrarse
        Button registrarmeButton = new Button("Registrarme");
        GridPane.setConstraints(registrarmeButton, 1, 5);
        registrarmeButton.setOnAction(e -> validarRegistro(
                RutRegistroInput.getText(), NombreInput.getText(), 
                Integer.parseInt(EdadInput.getText()), sexo.getValue(), 
                carrera.getValue()));
        

        //Agregando a grid
        grid1.getChildren().addAll(RegistroRut, RutRegistroInput, 
                NombreRegistro, NombreInput, EdadRegistro, EdadInput, sexoLabel, sexo, 
                carreraLabel, carrera, registrarmeButton);

        // scene1, grid1 = Menu Registro
        scene1 = new Scene(grid1, 330, 230);
        
        // Fin menu registro
        
        // Scene2 Menu Logueado
        
        //GridPane
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(20, 20, 20, 20));
        grid2.setVgap(8);
        grid2.setHgap(10);
        
        //ButtonSemestres
        Button SemestresButton = new Button("Crear Semestres");
        GridPane.setConstraints(SemestresButton, 0, 0);
        
        //HistorialAcademico
        Button HistorialButton = new Button("Historial");
        GridPane.setConstraints(HistorialButton, 1, 0);
        
        //AvanceCurricular
        Button ACButton = new Button("Avance Curricular");
        GridPane.setConstraints(ACButton, 2, 0);
        
        //BuscadorCursos
        Button BuscadorButton = new Button("Buscador de Cursos");
        GridPane.setConstraints(BuscadorButton, 3, 0);
   
        //Agregando a grid
        grid2.getChildren().addAll(SemestresButton, HistorialButton, ACButton, 
                BuscadorButton);

        // scene2, grid2 = Menu Logueado
        scene2 = new Scene(grid2, 520, 100);

        window.setScene(scene0);
        window.show();
        }catch(Throwable e){
            System.out.println(e);
        }
        
    }
    private void validarRegistro(String RutInput, String NombreInput, 
            Integer EdadInput, String sexoInput, String CarreraInput){ //Falta Completar
        try{
            String rut = RutInput;
        String Nombre = NombreInput;
        Integer Edad = EdadInput;
        String Sexo = sexoInput;
        String Carrera = CarreraInput;
        // Hacer las modificaciones
        window.setScene(scene0);
        }catch(Throwable e){
            System.out.println(e); 
        }
    }
    private void validarUsuario(String rut, String pass){
        // verifica rut y pass con la base de datos
        window.setScene(scene2);
    }

}
