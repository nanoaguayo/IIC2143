package mainproyectosoft;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.Group;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.geometry.Insets; 
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainProyectoSoft extends Application {
    
    Stage window;
    Scene scene0; // Menu Principal
    Scene scene1; // Menu Registro
    Scene scene2; // Menu Logueado
    Scene scene_tomaramos; // Menu toma de ramos
    Scene scene_admin;
    Scene scene_profesor;
    
    Alumno alumno;
    Administrador admin;
    Profesor profesor;
    SistemaRead sr = new SistemaRead();
    Sistema sistema = new Sistema();
    String pre_username;
    String pre_pass;
    
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        window = primaryStage;
        window.setTitle("Sistema Académico");
        window.setResizable(false);

        
        //Modo Administrador
        
        
        //GridPane
        GridPane grid_admin = new GridPane();
        grid_admin.setPadding(new Insets(20, 50, 50, 50));
        grid_admin.setVgap(20);
        grid_admin.setHgap(10);
        
        // Modo Admin
        final Label labelbienvenida_admin = new Label("Modo Administrador");
        labelbienvenida_admin.setFont(new Font("Arial", 15));
        labelbienvenida_admin.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(labelbienvenida_admin, 0, 0);
        
        //Button Ramo
        Button RamoButton = new Button("Crear Ramo");
        GridPane.setConstraints(RamoButton, 0, 1);
        RamoButton.setOnAction(e -> Admin_crearramo());
        
        //Button Malla
        Button MallaButton = new Button("Crear Malla");
        GridPane.setConstraints(MallaButton, 0, 2);
        MallaButton.setOnAction(e -> Admin_CrearMalla());
        
        //Button Restricciones
        Button RestriccionesButton = new Button("Restricciones");
        GridPane.setConstraints(RestriccionesButton, 0, 3);
        RestriccionesButton.setOnAction(e -> Admin_restricciones());
        
        //Button Agregar Ramo a Malla
        Button RamoMallaButton = new Button("Agregar Ramo a Malla");
        GridPane.setConstraints(RamoMallaButton, 0, 4);
        RamoMallaButton.setOnAction(e -> Admin_RamoaMalla());
        
        //Button Activar/Desactivar Crear Semestre
        Button semstatusButton = new Button();        
        if (sistema.getEstado()){
            semstatusButton.setText("Desactivar Crear Semestre");
        } else{
            semstatusButton.setText("Activar Crear Semestre");
        }
        GridPane.setConstraints(semstatusButton, 0, 5);
        semstatusButton.setOnAction(e -> {
            if (semstatusButton.getText().equals("Activar Crear Semestre")){
                sistema.CambiarPeriodo();
                semstatusButton.setText("Desactivar Crear Semestre");
            } else {
                sistema.setFalseTomaRamos();
                semstatusButton.setText("Activar Crear Semestre");
            }
        });
   
        //Button Logout
        Button Logout = new Button("Logout");
        GridPane.setConstraints(Logout, 0, 6);
        Logout.setOnAction(e -> {
            alumno = null;
            admin = null;
            profesor = null;
            window.setScene(scene0);
        });
        
        //Agregando a grid
        grid_admin.getChildren().addAll(labelbienvenida_admin, RamoButton, 
                MallaButton, RestriccionesButton, semstatusButton, RamoMallaButton, Logout);
        grid_admin.setAlignment(Pos.CENTER);
        scene_admin = new Scene(grid_admin); 
        
        //FIN modo Admin
        
        
        
        //Modo Profesor
        
        
        //GridPane
        GridPane grid_profe = new GridPane();
        grid_profe.setPadding(new Insets(20, 50, 20, 50));
        grid_profe.setVgap(20);
        grid_profe.setHgap(10);
        grid_profe.setAlignment(Pos.CENTER);
        
        // Modo Admin
        final Label labelbienvenida_profe = new Label("Modo Profesor");
        labelbienvenida_profe.setFont(new Font("Arial", 15));
        labelbienvenida_profe.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(labelbienvenida_profe, 0, 0);
        
        //Button Calificar
        Button CalificarButton = new Button("Calificar");
        GridPane.setConstraints(CalificarButton, 0, 1);
        CalificarButton.setOnAction(e -> Calificar_profe());
        
        //Button Logout
        Button PLogout = new Button("Logout");
        GridPane.setConstraints(PLogout, 0, 2);
        PLogout.setOnAction(e -> {
            alumno = null;
            admin = null;
            profesor = null;
            window.setScene(scene0);
        });
   
        //Agregando a grid
        grid_profe.getChildren().addAll(labelbienvenida_profe, CalificarButton, PLogout);
        
        scene_profesor = new Scene(grid_profe); 
        
        //FIN modo Profesor
        
         //GridPane
        GridPane grid0 = new GridPane();
        grid0.setPadding(new Insets(20, 20, 20, 20));
        grid0.setVgap(8);
        grid0.setHgap(10);
        
        VBox box = new VBox(10);
        Image image = new Image("file:data/logo/Logo.png") {};
        ImageView view = new ImageView();
        view.setImage(image);
        box.getChildren().add(view);
        GridPane.setConstraints(box,0,0);
        //Username Label
        Label nameLabel = new Label("Nombre Usuario:");
        GridPane.setConstraints(nameLabel, 2, 2);
        box.getChildren().add(nameLabel);
        //Username Input
        TextField nameInput = new TextField();
        nameInput.setPromptText("nombre usuario");
        GridPane.setConstraints(nameInput, 3, 2);
        box.getChildren().add(nameInput);
        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 2, 3);
        box.getChildren().add(passLabel);
        //Password Input
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 3, 3);
        box.getChildren().add(passInput);
        
        //Login
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 4, 2);
        box.getChildren().add(loginButton);
        loginButton.setOnAction(e -> validarUsuario(nameInput.getText(), 
                passInput.getText(), nameInput, passInput));
        
        
        //Registrarse
        Button registerButton = new Button("Registrarse");
        GridPane.setConstraints(registerButton, 1, 3);
        registerButton.setOnAction(e -> window.setScene(scene1)); // se dirige a registro por primera vez

        //Agregando a grid
        //grid0.getChildren().addAll(box,nameLabel, nameInput, passLabel, passInput, loginButton);
        grid0.getChildren().add(box);
        // scene0, grid0 = Menu Principal
        grid0.setAlignment(Pos.CENTER);
        scene0 = new Scene(grid0);


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
        
        //NumeroLabel
        Label NumeroRegistro = new Label("Numero Alumno:");
        GridPane.setConstraints(NumeroRegistro, 0, 1);

        //Numero Input
        TextField NumeroInput = new TextField();
        GridPane.setConstraints(NumeroInput, 1, 1);
        
        //Rut Label
        Label RegistroRut = new Label("Rut:");
        GridPane.setConstraints(RegistroRut, 0, 2);

        //Rut Input
        TextField RutRegistroInput = new TextField();
        RutRegistroInput.setPromptText("XX.XXX.XXX-X");
        GridPane.setConstraints(RutRegistroInput, 1, 2);

        //EdadLabel
        Label EdadRegistro = new Label("Edad:");
        GridPane.setConstraints(EdadRegistro, 0, 3);
        
        //EdadInput
        TextField EdadInput = new TextField();
        GridPane.setConstraints(EdadInput, 1, 3);
        
        //SexoLabel
        Label sexoLabel = new Label("Sexo:");
        GridPane.setConstraints(sexoLabel, 0, 4);
        
        //Sexo
        ChoiceBox<String> sexo = new ChoiceBox<>();
        sexo.getItems().addAll("Masculino", "Femenino");
        sexo.setValue("Masculino");
        GridPane.setConstraints(sexo, 1, 4);
        
        //CarreraLabel
        Label carreraLabel = new Label("Carrera:");
        GridPane.setConstraints(carreraLabel, 0, 5);
        
        //Malla
        ComboBox<String> malla = new ComboBox<>();
        GridPane.setConstraints(malla, 1, 6);        
        
        //Carrera
        ComboBox<String> carrera = new ComboBox<>();
        ArrayList<String> carreras = sr.getCarreras();
        carrera.getItems().addAll(carreras);
        carrera.getSelectionModel().selectFirst();
        sistema.carreratemp = carrera.getValue();
        ArrayList<String> mallas = sr.getMallas(carrera.getValue());
        malla.getItems().addAll(mallas);
        GridPane.setConstraints(carrera, 1, 5);
        carrera.setOnAction(e -> {
            malla.getSelectionModel().clearSelection();
            malla.getItems().clear();
            if (carrera.getValue() != null){
                malla.setDisable(false);
                sistema.carreratemp = carrera.getValue();
                ArrayList<String> mallas0 = sr.getMallas(carrera.getValue());
                malla.getItems().addAll(mallas0);
            }
        });
        
        //MallaLabel
        Label mallaLabel = new Label("Malla:");
        GridPane.setConstraints(mallaLabel, 0, 6);
        
        //Boton Registrarse
        Button registrarmeButton = new Button("Registrarme");
        GridPane.setConstraints(registrarmeButton, 1, 7);
        registrarmeButton.setOnAction(e -> validarRegistro(
                RutRegistroInput.getText(), NombreInput.getText(), 
                EdadInput.getText(), sexo.getValue(), 
                carrera.getValue(), NumeroInput.getText(), malla.getValue()));
        

        //Agregando a grid
        grid1.getChildren().addAll(RegistroRut, RutRegistroInput, 
                NombreRegistro, NombreInput, NumeroRegistro, EdadRegistro, 
                EdadInput, sexoLabel, sexo, carreraLabel, carrera, mallaLabel, 
                malla, registrarmeButton, NumeroInput);

        // scene1, grid1 = Menu Registro
        scene1 = new Scene(grid1, 330, 300);
        
        // Fin menu registro
        
        // Scene2 Menu Logueado Alumno
        
        //GridPane
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(20, 20, 20, 20));
        grid2.setVgap(10);
        grid2.setHgap(10);
        
        // Mostrar Usuario
        final Label labelbienvenida = new Label("Bienvenido al Sistema Academico");
        labelbienvenida.setFont(new Font("Arial", 15));
        labelbienvenida.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(labelbienvenida, 0, 0);
        
        
        //ButtonSemestres
        Button SemestreButton = new Button("Crear Semestre");
        GridPane.setConstraints(SemestreButton, 0, 1);
        SemestreButton.setOnAction(e -> window.setScene(scene_tomaramos));
        
        //HistorialAcademico
        Button HistorialButton = new Button("Historial");
        GridPane.setConstraints(HistorialButton, 0, 2);
        HistorialButton.setOnAction(e -> VentanaHistorial());
        
        //AvanceCurricular
        Button ACButton = new Button("Avance Curricular");
        GridPane.setConstraints(ACButton, 0, 3);
        ACButton.setOnAction(e -> PreVentanaAvance());
        
        //BuscadorCursos
        Button BuscadorButton = new Button("Buscador de Cursos");
        GridPane.setConstraints(BuscadorButton, 0, 4);
        BuscadorButton.setOnAction(e -> BuscadordeCursos());
        
        //Button Logout
        Button ALogout = new Button("Logout");
        GridPane.setConstraints(ALogout, 0, 5);
        ALogout.setOnAction(e -> {
            alumno = null;
            admin = null;
            profesor = null;
            window.setScene(scene0);
        });
   
        //Agregando a grid
        grid2.getChildren().addAll(labelbienvenida, SemestreButton, HistorialButton, ACButton, 
                BuscadorButton, ALogout);
        
        // Sistema se puede crear ramo
        if (sistema.getEstado()){
            if (!grid2.getChildren().contains(SemestreButton)){
                grid2.getChildren().add(SemestreButton);
                
            }
            scene2 = new Scene(grid2, 290, 230);
        }else {
            if (grid2.getChildren().contains(SemestreButton)){
                grid2.getChildren().remove(SemestreButton);
                
            }
            scene2 = new Scene(grid2, 290, 190);
        }

        // scene2, grid2 = Menu Logueado
        

        window.setScene(scene0);
        window.show();
        }catch(Throwable e){
            System.out.println(e);
        }
        // Menu Toma de ramos
        try{
            //GridPane
        GridPane grid_tomaramos = new GridPane();
        grid_tomaramos.setPadding(new Insets(10, 10, 10, 10));
        grid_tomaramos.setVgap(8);
        grid_tomaramos.setHgap(5);
        
        //Ramo1 Label
        Label LabelIngresarSiglas = new Label("Ingresar Siglas de Ramos");
        GridPane.setConstraints(LabelIngresarSiglas, 0, 0);
        
        //Ramo1 Label
        Label LabelRamo1 = new Label("Ramo 1:");
        GridPane.setConstraints(LabelRamo1, 0, 2);

        //Ramo1 Input
        TextField InputRamo1 = new TextField();
        GridPane.setConstraints(InputRamo1, 1, 2);
        
        //Ramo2 Label
        Label LabelRamo2 = new Label("Ramo 2:");
        GridPane.setConstraints(LabelRamo2, 0, 3);

        //Ramo2 Input
        TextField InputRamo2 = new TextField();
        GridPane.setConstraints(InputRamo2, 1, 3);
        
        //Ramo3 Label
        Label LabelRamo3 = new Label("Ramo 3:");
        GridPane.setConstraints(LabelRamo3, 0, 4);

        //Ramo3 Input
        TextField InputRamo3 = new TextField();
        GridPane.setConstraints(InputRamo3, 1, 4);
        
        //Ramo4 Label
        Label LabelRamo4 = new Label("Ramo 4:");
        GridPane.setConstraints(LabelRamo4, 0, 5);

        //Ramo4 Input
        TextField InputRamo4 = new TextField();
        GridPane.setConstraints(InputRamo4, 1, 5);
        
        //Ramo5 Label
        Label LabelRamo5 = new Label("Ramo 5:");
        GridPane.setConstraints(LabelRamo5, 0, 6);

        //Ramo5 Input
        TextField InputRamo5 = new TextField();
        GridPane.setConstraints(InputRamo5, 1, 6);
        
        //Ramo6 Label
        Label LabelRamo6 = new Label("Ramo 6:");
        GridPane.setConstraints(LabelRamo6, 0, 7);

        //Ramo6 Input
        TextField InputRamo6 = new TextField();
        GridPane.setConstraints(InputRamo6, 1, 7);
        
        //Ramo7 Label
        Label LabelRamo7 = new Label("Ramo 7:");
        GridPane.setConstraints(LabelRamo7, 0, 8);

        //Ramo7 Input
        TextField InputRamo7 = new TextField();
        GridPane.setConstraints(InputRamo7, 1, 8);
        
        //Ramo8 Label
        Label LabelRamo8 = new Label("Ramo 8:");
        GridPane.setConstraints(LabelRamo8, 0, 9);

        //Ramo8 Input
        TextField InputRamo8 = new TextField();
        GridPane.setConstraints(InputRamo8, 1, 9);
        
        //Ramo9 Label
        Label LabelRamo9 = new Label("Ramo 9:");
        GridPane.setConstraints(LabelRamo9, 0, 10);

        //Ramo9 Input
        TextField InputRamo9 = new TextField();
        GridPane.setConstraints(InputRamo9, 1, 10);
        
        //Ramo10 Label
        Label LabelRamo10 = new Label("Ramo 10:");
        GridPane.setConstraints(LabelRamo10, 0, 11);

        //Ramo10 Input
        TextField InputRamo10 = new TextField();
        GridPane.setConstraints(InputRamo10, 1, 11);
        
        //Boton Ingresar Ramos
        Button ingresarRamosButton = new Button("Ingresar");
        GridPane.setConstraints(ingresarRamosButton, 1, 13);
        ingresarRamosButton.setOnAction(e -> ingresarRamos(InputRamo1.getText(), 
                InputRamo2.getText(), InputRamo3.getText(), 
                InputRamo4.getText(), InputRamo5.getText(), 
                InputRamo6.getText(), InputRamo7.getText(), 
                InputRamo8.getText(), InputRamo9.getText(), 
                InputRamo10.getText()));
        
        //ErrorLabel
        Label Labelerror = new Label("");
        GridPane.setConstraints(Labelerror, 0, 14);
        

        //Agregando a grid
        grid_tomaramos.getChildren().addAll(LabelIngresarSiglas, LabelRamo1, 
                InputRamo1, 
                LabelRamo2, InputRamo2, LabelRamo3, InputRamo3, 
                LabelRamo4, InputRamo4, LabelRamo5, InputRamo5, 
                LabelRamo6, InputRamo6, LabelRamo7, InputRamo7, 
                LabelRamo8, InputRamo8, LabelRamo9, InputRamo9, 
                LabelRamo10, InputRamo10, ingresarRamosButton);

        // scene1, grid1 = Menu Registro
        scene_tomaramos = new Scene(grid_tomaramos, 350, 430);
        
        // Fin menu Toma de Ramos
        }catch(Throwable e){
            System.out.println(e);
        }
        
        
    }
    private void validarRegistro(String RutInput, String NombreInput, 
            String EdadInput, String sexoInput, String CarreraInput, 
            String NumeroInput, String malla){
        try{
            String rut = RutInput;
        String Nombre = NombreInput;
        String Edad = EdadInput;
        String Sexo = sexoInput;
        String Carrera = CarreraInput;
        String Malla = malla;
        String NumeroAlumno = NumeroInput;
        // Hacer las modificaciones
        
        sistema.RegistrarAlumno(pre_username, Nombre, Carrera, Malla, Edad, 
                Sexo, rut, NumeroAlumno);
        alumno = sr.SetStudent(pre_username, pre_pass);
        window.setScene(scene_tomaramos);
        }catch(Throwable e){
            System.out.println(e); 
        }
    }
    private void validarUsuario(String username, String pass, TextField a, TextField b){
        // verifica username y pass con la base de datos
        int id;
        id = sr.Login(username, pass);
        if (id == 0){ //Alumno loguea por primera vez
            window.setScene(scene1);
            pre_username = username;
            pre_pass = pass;
            
        }
        else if (id == 1){ //Alumno Normal
            alumno = sr.SetStudent(username, pass);
            if(alumno!=null){
            window.setScene(scene2);
            }
            else{
                Stage window0 = new Stage();
                window0.setResizable(false);
                window0.initModality(Modality.APPLICATION_MODAL);
                window0.setTitle("Invalido");
                window0.setMaxWidth(450);
                window0.setMinWidth(400);
                window0.setMinHeight(150);
                

                Label label0 = new Label();
                label0.setText("Usuario excede cantidad de ramos reprobados. "
                        + "No puede entrar");
                Button closeButton0 = new Button("Cerrar");
                closeButton0.setOnAction(e -> window0.close());

                VBox layout = new VBox(10);
                layout.getChildren().addAll(label0, closeButton0);
                layout.setAlignment(Pos.CENTER);

                Scene scene_excesoramorep = new Scene(layout);
                window0.setScene(scene_excesoramorep);
                window0.showAndWait();
                }  
            }
            
        
        else if (id == 2){ //Admin
            admin = sr.SetAdmin(username, pass);
            window.setScene(scene_admin);
        }
        else if (id == 3){ //Profesor
            profesor = sr.SetProfesor(username, pass);
            window.setScene(scene_profesor);
        }
        else {
            //AlertBox Usuario Invalido
            Stage window1 = new Stage();
            window1.initModality(Modality.APPLICATION_MODAL);
            window1.setTitle("Invalido");
            window1.setMaxWidth(450);
            window1.setMinWidth(300);
            window1.setMinHeight(100);
            window1.setResizable(false);

            Label label = new Label();
            label.setText("Usuario invalido, intente de nuevo.");
            Button closeButton = new Button("Cerrar");
            closeButton.setOnAction(e -> window1.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window1.setScene(scene);
            window1.showAndWait();
            }
        a.clear();
        b.clear();
        
         
        
        //window.setScene(scene1);
    }
    private void ingresarRamos(String ramo1, String ramo2, String ramo3, 
            String ramo4, String ramo5, String ramo6, String ramo7, 
            String ramo8, String ramo9, String ramo10){
        String[] Ramos = new String[10];
        Ramos[0] = ramo1;
        Ramos[1] = ramo2;
        Ramos[2] = ramo3;
        Ramos[3] = ramo4;
        Ramos[4] = ramo5;
        Ramos[5] = ramo6;
        Ramos[6] = ramo7;
        Ramos[7] = ramo8;
        Ramos[8] = ramo9;
        Ramos[9] = ramo10;
        ArrayList<String> ramosIngresados = new ArrayList<>();
        for (String Ramo : Ramos) {
            if (!"".equals(Ramo)){
                ramosIngresados.add(Ramo);
            }
        }
        boolean ok = alumno.Tomar_Semestre(ramosIngresados);
        System.out.println(ok);
        
        
        //Iterador de ramosIngresados
        Iterator<String> ramosIterator = ramosIngresados.iterator();
        while(ramosIterator.hasNext()){
            String ramoIngresado = ramosIterator.next();
            System.out.println(ramoIngresado);
        }
        window.setScene(scene2);
    }
    private void BuscadordeCursos(){
        //Ventana Buscador de Cursos
        Stage windowBuscadordeCursos = new Stage();
        windowBuscadordeCursos.initModality(Modality.APPLICATION_MODAL);
        windowBuscadordeCursos.setTitle("Buscador de Cursos");
        windowBuscadordeCursos.setMinWidth(410);
        windowBuscadordeCursos.setMinHeight(350);

        //GridPane
        GridPane grid_buscacursos = new GridPane();
        grid_buscacursos.setPadding(new Insets(10, 10, 10, 10));
        grid_buscacursos.setVgap(8);
        grid_buscacursos.setHgap(5);
        grid_buscacursos.setAlignment(Pos.CENTER);
        
         //Sigla Label
        Label LabelSigla = new Label("Sigla:");
        LabelSigla.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelSigla, 0, 1);

        //Sigla Input
        TextField InputSigla = new TextField();
        GridPane.setConstraints(InputSigla, 1, 1);
        
        //Nombre Curso Label
        Label LabelNombrecurso = new Label("Nombre Curso:");
        LabelNombrecurso.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelNombrecurso, 0, 2);

        //Nombre Curso Input
        TextField InputNombrecurso = new TextField();
        GridPane.setConstraints(InputNombrecurso, 1, 2);
        
        //Profesor Label
        Label LabelProfesor = new Label("Profesor:");
        LabelProfesor.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelProfesor, 0, 3);

        //Profesor Input
        TextField InputProfesor = new TextField();
        GridPane.setConstraints(InputProfesor, 1, 3);
        
        //Facultad Label
        Label LabelFacultad = new Label("Facultad:");
        LabelFacultad.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelFacultad, 0, 4);

        //Facultad Input
        TextField Inputfacultad = new TextField();
        GridPane.setConstraints(Inputfacultad, 1, 4);
        
        //Horario Label
        Label LabelHorario = new Label("Horario");
        LabelHorario.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelHorario, 0, 5);
        
        //Dia Semana Label
        Label LabelDiadesemana = new Label("Dia de Semana:");
        LabelDiadesemana.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelDiadesemana, 0, 6);

        //Dia Semana Input
        ComboBox<String> diadesemanabox = new ComboBox<>();
        diadesemanabox.getItems().addAll("Todas", "L", "M", "W", "J", "V", "S");
        diadesemanabox.setValue("Todas");
        GridPane.setConstraints(diadesemanabox, 1, 6);
        
        //Modulo Label
        Label LabelModulo= new Label("Modulo:");
        LabelModulo.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelModulo, 0, 7);

        //Modulo Input
        ChoiceBox<String> modulobox = new ChoiceBox<>();
        modulobox.getItems().addAll("Todas", "1", "2", "3", "4", "5", "6", "7",
                "8");
        modulobox.setValue("Todas");
        GridPane.setConstraints(modulobox, 1, 7);
        
        //Button Ingresar Horario
        Button ButtonAgregarHorario = new Button("Agregar Horario");
        GridPane.setConstraints(ButtonAgregarHorario, 1, 5);
        
        //Texto Horarios Agregados
        Label Labelagregarhorario = new Label("Horarios Agregados: ");
        GridPane.setConstraints(Labelagregarhorario, 0, 9);
        
        //Resultado Horarios Agregados
        Label Resultadoagregarhorario = new Label("");
        GridPane.setConstraints(Resultadoagregarhorario, 1, 9);
        ArrayList<String> horariosingresados = new ArrayList<>();
        
        ButtonAgregarHorario.setOnAction((ActionEvent event) -> {
            if (!diadesemanabox.getValue().equals("Todas") && 
                    !modulobox.getValue().equals("Todas") && 
                    !horariosingresados.contains(diadesemanabox.getValue()+
                            ":"+modulobox.getValue())){
                String texto_actual = Resultadoagregarhorario.getText();
            Resultadoagregarhorario.setText(texto_actual+diadesemanabox.getValue()+
                    ":"+modulobox.getValue()+" ");
            horariosingresados.add(diadesemanabox.getValue()+":"+modulobox.getValue());
            System.out.println(horariosingresados);
            }
            
            
            });
        
        //Button Buscar
        Button ButtonBuscar = new Button("Buscar");
        GridPane.setConstraints(ButtonBuscar, 0, 11);
        ButtonBuscar.setOnAction(e -> ventanaResultadoBuscador(
                InputNombrecurso.getText(), InputSigla.getText(), 
                Inputfacultad.getText(), InputProfesor.getText(), 
                horariosingresados));
        
        //Button Limpiar Horario
        Button ButtonLimpiarHorario = new Button("Limpiar");
        GridPane.setConstraints(ButtonLimpiarHorario, 1, 11);
        ButtonLimpiarHorario.setOnAction(e -> {
            InputNombrecurso.clear();
            InputSigla.clear();
            Inputfacultad.clear();
            InputProfesor.clear();
            horariosingresados.clear();
            Resultadoagregarhorario.setText("");
        });
        
        //Display
        grid_buscacursos.getChildren().addAll(LabelSigla, InputSigla, 
                LabelNombrecurso, InputNombrecurso, LabelHorario, 
                LabelDiadesemana, diadesemanabox, LabelModulo, LabelFacultad,
                Inputfacultad, modulobox, ButtonAgregarHorario, 
                Labelagregarhorario, Resultadoagregarhorario, ButtonBuscar, 
                ButtonLimpiarHorario);
        Scene scene_buscacursos = new Scene(grid_buscacursos);
        windowBuscadordeCursos.setScene(scene_buscacursos);
        windowBuscadordeCursos.show();
    }
    
    private void VentanaAvanceCurricular(Malla_Curricular mallac){
        Malla_Curricular malla = mallac;
        Avance av = new Avance();
        RamoAvanceFX[] ramos = av.GetAvance(alumno, malla);
        Stage windowAvanceCurricular = new Stage();
        
        Scene scene_avance = new Scene(new Group());
        TableView<RamoAvanceFX> table = new TableView<>();
        ArrayList arrlstramos = new ArrayList<>(Arrays.asList(ramos));
        ObservableList<RamoAvanceFX> data = 
                FXCollections.observableArrayList(arrlstramos);
        windowAvanceCurricular.setTitle("Avance Curricular");
        //windowAvanceCurricular.setWidth(500);
        //windowAvanceCurricular.setHeight(500);
 
        final Label label = new Label("Ramos");
        label.setFont(new Font("Arial", 15));
 
        table.setEditable(false);
 
        TableColumn col1 = new TableColumn("Sigla");
        TableColumn col2 = new TableColumn("Nombre");
        TableColumn col3 = new TableColumn("Nota");
        
        col1.setCellValueFactory(
                new PropertyValueFactory<>("Sigla"));
        col1.setMinWidth(100);
        
        col2.setCellValueFactory(
                new PropertyValueFactory<>("Nombre"));
        col2.setMinWidth(200);
        
        col3.setCellValueFactory(
                new PropertyValueFactory<>("Nota"));
        col3.setMinWidth(80);
        
        System.out.println(data);
        table.setItems(data);
        table.getColumns().addAll(col1, col2, col3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene_avance.getRoot()).getChildren().addAll(vbox);
 
        windowAvanceCurricular.setScene(scene_avance);
        windowAvanceCurricular.show();
    }
    
    private void PreVentanaAvance(){
        Stage windowpreAvance = new Stage();
        windowpreAvance.setMinWidth(400);
        
        //GridPane
        GridPane grid_preavance = new GridPane();
        grid_preavance.setPadding(new Insets(20, 20, 20, 20));
        grid_preavance.setVgap(15);
        grid_preavance.setHgap(15);
        grid_preavance.setAlignment(Pos.CENTER);
        
        // Label Elegir Malla
        Label labelElegirMalla = new Label("Elegir Malla:");
        GridPane.setConstraints(labelElegirMalla, 0, 1);
        
        // Elegir Malla Box
        ComboBox<String> Mallabox = new ComboBox<>();
        GridPane.setConstraints(Mallabox, 1, 1);
                
        // Label Elegir Carrera
        Label labelElegirCarrera = new Label("Elegir Carrera:");
        GridPane.setConstraints(labelElegirCarrera, 0, 0);
        
        // Label Elegir Carrera
        Label mensaje = new Label("");
        
        // Elegir Carrera Box
        ComboBox<String> carrerabox = new ComboBox<>();
        String[] carreras = alumno.getCarrera();
        for (int i = 0; i< carreras.length; i++){
            if (!"".equals(carreras[i])){
            carrerabox.getItems().add(carreras[i]);
            }
        }
        carrerabox.getSelectionModel().selectFirst();
        GridPane.setConstraints(carrerabox, 1, 0);
        ArrayList<Malla_Curricular> mallas = 
                    alumno.getMallas(carrerabox.getValue());
            Iterator<Malla_Curricular> iterador = mallas.iterator();
            while (iterador.hasNext()){
                Malla_Curricular malla = iterador.next();
                String malla_string = malla.Especialidad;
                Mallabox.getItems().add(malla_string);
            }
        carrerabox.setOnAction(e -> {
            Mallabox.getItems().clear();
            ArrayList<Malla_Curricular> mallas2 = 
                    alumno.getMallas(carrerabox.getValue());
            Iterator<Malla_Curricular> iterador2 = mallas2.iterator();
            while (iterador2.hasNext()){
                Malla_Curricular malla = iterador.next();
                String malla_string = malla.Especialidad;
                Mallabox.getItems().add(malla_string);
            }
        });
        
        //Button Ingresar
        Button ingresar = new Button("Ingresar");
        GridPane.setConstraints(ingresar, 0, 2);
        ingresar.setOnAction(e -> VentanaAvanceCurricular(
                new Malla_Curricular(carrerabox.getValue(), Mallabox.getValue()
                )));
        
        //Agregando a grid
        grid_preavance.getChildren().addAll(carrerabox, labelElegirCarrera, 
                labelElegirMalla, Mallabox, ingresar);
        
        VBox vb = new VBox(10);
        vb.getChildren().addAll(grid_preavance, mensaje);
        vb.setAlignment(Pos.CENTER);
        
        Scene scene_preavance = new Scene(vb);
        windowpreAvance.setScene(scene_preavance);
        windowpreAvance.setTitle("Avance Curricular");
        windowpreAvance.show();
        
    }
    
    
        
    
    private void VentanaHistorial(){
        Stage windowHistorial = new Stage();
        Historial hist = new Historial();
        ArrayList<RamoAvanceFX> ramos = hist.GetHistorialFX(alumno);
        
        Scene scene_historial = new Scene(new Group());
        TableView<RamoAvanceFX> table = new TableView<>();
        ObservableList<RamoAvanceFX> data = FXCollections.observableArrayList(ramos);
        windowHistorial.setTitle("Historial");
        //windowAvanceCurricular.setWidth(500);
        //windowAvanceCurricular.setHeight(500);
 
        final Label label = new Label("Ramos");
        label.setFont(new Font("Arial", 15));
 
        table.setEditable(false);
 
        TableColumn col1 = new TableColumn("Sigla");
        TableColumn col2 = new TableColumn("Nombre");
        TableColumn col3 = new TableColumn("Nota");
        TableColumn col4 = new TableColumn("Periodo");
        
        col1.setCellValueFactory(
                new PropertyValueFactory<>("Sigla"));
        col1.setMinWidth(100);
        
        col2.setCellValueFactory(
                new PropertyValueFactory<>("Nombre"));
        col2.setMinWidth(200);
        
        col3.setCellValueFactory(
                new PropertyValueFactory<>("Nota"));
        col3.setMinWidth(80);
        
        col4.setCellValueFactory(
                new PropertyValueFactory<>("Periodo"));
        col4.setMinWidth(100);
        
        System.out.println(data);
        table.setItems(data);
        table.getColumns().addAll(col1, col2, col3, col4);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table);
        windowHistorial.setResizable(false);
 
        ((Group) scene_historial.getRoot()).getChildren().addAll(vbox);
 
        windowHistorial.setScene(scene_historial);
        windowHistorial.show();
    }
    private void Admin_crearramo(){
        //Ventana Crear Ramo
        Stage windowCrearRamo = new Stage();
        windowCrearRamo.initModality(Modality.APPLICATION_MODAL);
        windowCrearRamo.setTitle("Creador de Ramos");
        windowCrearRamo.setMinWidth(300);
        windowCrearRamo.setMinHeight(450);

        //GridPane
        GridPane grid_crearamo = new GridPane();
        grid_crearamo.setPadding(new Insets(10, 10, 10, 10));
        grid_crearamo.setVgap(8);
        grid_crearamo.setHgap(5);
        grid_crearamo.setAlignment(Pos.CENTER);

        //Nombre Label
        Label LabelNombre = new Label("Nombre:");
        LabelNombre.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelNombre, 0, 0);

        //Nombre Input
        TextField InputNombre = new TextField();
        GridPane.setConstraints(InputNombre, 1, 0);
        
         //Sigla Label
        Label LabelSigla = new Label("Sigla:");
        LabelSigla.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelSigla, 0, 1);

        //Sigla Input
        TextField InputSigla = new TextField();
        GridPane.setConstraints(InputSigla, 1, 1);
        
        //Profesor Label
        Label LabelProfesor = new Label("Profesor:");
        GridPane.setConstraints(LabelProfesor, 0, 2);

        //Profesor Input
        TextField InputProfesor = new TextField();
        GridPane.setConstraints(InputProfesor, 1, 2);
        
        //Facultad Label
        Label LabelCampus = new Label("Facultad:");
        GridPane.setConstraints(LabelCampus, 0, 3);

        //Facultad Input
        TextField Inputcampus = new TextField();
        GridPane.setConstraints(Inputcampus, 1, 3);
        
        //Horario Label
        Label LabelHorario = new Label("Horario:");
        GridPane.setConstraints(LabelHorario, 0, 4);
        
        //Horario Input
        TextField InputHorario = new TextField();
        InputHorario.setPromptText("M-J:4");
        GridPane.setConstraints(InputHorario, 1, 4);
        
        //Sala Label
        Label LabelSala = new Label("Sala:");
        GridPane.setConstraints(LabelSala, 0, 5);
        
        //Sala Input
        TextField InputSala = new TextField();
        GridPane.setConstraints(InputSala, 1, 5);
        
        //Creditos Label
        Label LabelCreditos = new Label("Creditos:");
        GridPane.setConstraints(LabelCreditos, 0, 6);
        
        //Creditos Input
        TextField Inputcreditos = new TextField();
        GridPane.setConstraints(Inputcreditos, 1, 6);
        
        //Retirable Label
        Label LabelRetirable = new Label("Retirable:");
        GridPane.setConstraints(LabelRetirable, 0, 7);
        
        //Retirable Box
        ComboBox<String> retirablebox = new ComboBox<>();
        retirablebox.getItems().addAll("Si", "No");
        retirablebox.setValue("Si");
        GridPane.setConstraints(retirablebox, 1, 7);
  
        //Seccion Label
        Label LabelSeccion = new Label("Seccion:");
        GridPane.setConstraints(LabelSeccion, 0, 8);
        
        //Seccion Input
        TextField InputSeccion = new TextField();
        GridPane.setConstraints(InputSeccion, 1, 8);
        
        //Comentario Label
        Label LabelComentario = new Label("Comentarios:");
        GridPane.setConstraints(LabelComentario, 0, 9);
        
        //Comentario Input
        TextArea Inputcomentario = new TextArea();
        GridPane.setConstraints(Inputcomentario, 1, 9);
        Inputcomentario.setMaxWidth(200);
        Inputcomentario.setMaxHeight(80);
        
        //Mensaje
        Label mensaje = new Label("");
        
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 0, 0, 10));
        vb.setSpacing(10);

        
        
        //Button Ingresar
        Button ButtonIngresarRamo = new Button("Ingresar Ramo");
        GridPane.setConstraints(ButtonIngresarRamo, 1, 11);
        ButtonIngresarRamo.setOnAction((ActionEvent e) -> {
            try{
                if ("".equals(InputNombre.getText()) ||
                        "".equals(InputProfesor.getText()) ||
                        "".equals(InputSigla.getText()) ||
                        "".equals(Inputcampus.getText())||
                        "".equals(InputHorario.getText()) ||
                        "".equals(Inputcreditos.getText()) ||
                        "".equals(retirablebox.getValue()) ||
                        "".equals(InputSala.getText()) ||
                        "".equals(InputSeccion.getText()) ||
                        "".equals(Inputcomentario.getText())){
                    
                    mensaje.setText("Debe Rellenar todos los campos");
                    mensaje.setStyle("-fx-font-weight: bold");
                    
                }else {
                    String Sigla = InputSigla.getText().toUpperCase();
                    String Nombre = InputNombre.getText();
                    String Profesor = InputProfesor.getText();
                    String Campus = Inputcampus.getText();
                    String Horario = InputHorario.getText().toUpperCase();
                    int Creditos = Integer.parseInt(Inputcreditos.getText());
                    boolean Esretirable = "Si".equals(retirablebox.getValue());
                    String Sala = InputSala.getText();
                    int Seccion = Integer.parseInt(InputSeccion.getText());
                    String Comentario = Inputcomentario.getText();
                    double Nota = 0.0;
                    
                    boolean aux = admin.CrearRamo(Sigla, Nombre, Horario, Sala, Campus,
                            Creditos, Nota, Esretirable, Seccion, Comentario, Profesor);
                    
                    if (aux){
                        mensaje.setText("Ingreso de Ramo Exitoso");
                    } else {
                        mensaje.setText("Error de Incompatibilidad");
                    }
                    mensaje.setStyle("-fx-font-weight: bold");
                }
            } catch (Exception err){
                System.out.println(err);
                mensaje.setText("Parametros Incompatibles");
                mensaje.setStyle("-fx-font-weight: bold");
            }
        });
        
        //Display
        grid_crearamo.getChildren().addAll(LabelSigla, InputSigla, LabelNombre, 
                InputNombre, LabelProfesor, InputProfesor, LabelCampus, 
                Inputcampus, LabelHorario, InputHorario, LabelCreditos, 
                Inputcreditos, LabelRetirable, retirablebox, LabelSeccion,
                InputSeccion, LabelComentario, Inputcomentario, 
                ButtonIngresarRamo, LabelSala, InputSala);
        vb.getChildren().addAll(grid_crearamo, mensaje);
        Scene scene_crearamo = new Scene(vb);
        windowCrearRamo.setScene(scene_crearamo);
        windowCrearRamo.show();
    }
    public void Admin_restricciones(){
        Stage windowRestricciones = new Stage();
        windowRestricciones.setResizable(false);
        windowRestricciones.initModality(Modality.APPLICATION_MODAL);
        windowRestricciones.setTitle("Restricciones");
        // windowRestricciones.setMinWidth(300);
        // windowRestricciones.setMinHeight(450);

        //GridPane
        GridPane grid_restricciones = new GridPane();
        grid_restricciones.setPadding(new Insets(10, 10, 10, 10));
        grid_restricciones.setVgap(8);
        grid_restricciones.setHgap(5);
        
        // Label Límite de Ramos Reprobados
        Label labellimramos = new Label("Limite de Ramos Reprobados:");
        GridPane.setConstraints(labellimramos, 0, 0);
        
        // Label Max Creditos
        Label labelmaxcr = new Label("Limite de Creditos por Semestre:");
        GridPane.setConstraints(labelmaxcr, 0, 1);
        
        // Input Limramos
        TextField inputlimramos = new TextField();
        GridPane.setConstraints(inputlimramos, 1, 0);
        
        // Input MaxCr
        TextField inputmaxcr = new TextField();
        GridPane.setConstraints(inputmaxcr, 1, 1);
        
        // Mensaje
        Label mensaje = new Label("");
        
        //Button ingresar
        Button Ingresar = new Button("Ingresar");
        GridPane.setConstraints(Ingresar, 1, 3);
        Ingresar.setOnAction(e -> {
            int maxcr;
            int limramos;
            try{
                if (!"".equals(inputlimramos.getText()) && !"".equals(inputmaxcr.getText())){
                    // Ejecutar Funcion para ambos
                    limramos = Integer.parseInt(inputlimramos.getText());
                    maxcr = Integer.parseInt(inputmaxcr.getText());
                    admin.SetRamosReprobados(limramos);
                    admin.SetCreditosMaximos(maxcr);
                    mensaje.setText("Se han cambiado ambos parametros");
                } else if (!"".equals(inputlimramos.getText())){
                    // Ejecutar funcion para limite de ramos
                    limramos = Integer.parseInt(inputlimramos.getText());
                    admin.SetRamosReprobados(limramos);
                    mensaje.setText("Se ha cambiado el limite de ramos reprobados");
                } else if (!"".equals(inputmaxcr.getText())){
                    // Ejecutar funcion para Maximo de Creditos
                    maxcr = Integer.parseInt(inputmaxcr.getText());
                    admin.SetCreditosMaximos(maxcr);
                    mensaje.setText("Se ha cambiado el maximo de creditos por semestre");
                } else {
                    mensaje.setText("Debe ingresar algun valor");
                }
                mensaje.setStyle("-fx-font-weight: bold");
            }catch (Exception err){
                System.out.println(err);
                mensaje.setText("Parametro Invalido");
                mensaje.setStyle("-fx-font-weight: bold");
            }
        });
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 0, 0, 10));
        vb.setSpacing(10);
        
        
        grid_restricciones.getChildren().addAll(labellimramos, labelmaxcr, 
                inputlimramos, inputmaxcr, Ingresar);
        
        vb.getChildren().addAll(grid_restricciones, mensaje);
        Scene scene_restricciones = new Scene(vb);
        windowRestricciones.setScene(scene_restricciones);
        windowRestricciones.show();
        
    }
    public void Admin_CrearMalla(){
        Stage windowCrearMalla = new Stage();
        windowCrearMalla.initModality(Modality.APPLICATION_MODAL);
        windowCrearMalla.setTitle("Creador de Mallas");
        // windowRestricciones.setMinWidth(300);
        // windowRestricciones.setMinHeight(450);

        //GridPane
        GridPane grid_crearmalla = new GridPane();
        grid_crearmalla.setPadding(new Insets(10, 10, 10, 10));
        grid_crearmalla.setVgap(8);
        grid_crearmalla.setHgap(5);
        grid_crearmalla.setAlignment(Pos.CENTER);
        
        
        // Label Carrera
        Label lcarrera = new Label("Carrera");
        GridPane.setConstraints(lcarrera, 0, 0);
        
        // Carrera Box
        ComboBox<String> carrerabox = new ComboBox<>();
        ArrayList<String> carreras = sr.getCarreras();
        carrerabox.getItems().addAll(carreras);
        carrerabox.setValue("Ingenieria");
        GridPane.setConstraints(carrerabox, 1, 0);
        
        // Label Nombre Malla
        Label nombremalla = new Label("Nombre Malla:");
        GridPane.setConstraints(nombremalla, 0, 1);
        
        // Input Nombre Malla
        TextField InputNombre = new TextField();
        GridPane.setConstraints(InputNombre, 1, 1);
        
        //Mensaje
        Label mensaje = new Label("");
        
        //Button Ingresar
        Button Ingresar = new Button("Agregar Malla");
        GridPane.setConstraints(Ingresar, 1, 3);
        Ingresar.setOnAction(e -> {
            try{
            if (!"".equals(InputNombre.getText())){
                Carrera ca = new Carrera(carrerabox.getValue());
                admin.CrearMalla(ca, InputNombre.getText());
                mensaje.setText("Malla agregada");
            }else{
                mensaje.setText("Debe rellenar todos los campos");
            }
            
            }catch (Exception err){
                System.out.println(err);
                mensaje.setText("Error");
            }
            mensaje.setStyle("-fx-font-weight: bold");
        });
        
        grid_crearmalla.getChildren().addAll(lcarrera, carrerabox, nombremalla, 
                InputNombre, Ingresar);
        
        
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 0, 0, 10));
        vb.setSpacing(10);
        
        vb.getChildren().addAll(grid_crearmalla, mensaje);
        Scene scene_crearmalla = new Scene(vb);
        windowCrearMalla.setScene(scene_crearmalla);
        windowCrearMalla.show();
        
    }
    public void Admin_RamoaMalla(){
        Stage windowRamoaMalla = new Stage();
        windowRamoaMalla.setResizable(true);
        windowRamoaMalla.initModality(Modality.APPLICATION_MODAL);
        windowRamoaMalla.setTitle("Agregar Ramos a Malla");
        windowRamoaMalla.setMinWidth(400);
        windowRamoaMalla.setMinHeight(250);

        //GridPane
        GridPane grid_ramoamalla = new GridPane();
        grid_ramoamalla.setPadding(new Insets(10, 10, 10, 10));
        grid_ramoamalla.setVgap(8);
        grid_ramoamalla.setHgap(5);
        grid_ramoamalla.setAlignment(Pos.CENTER);
        
        // Label Carrera
        Label lcarrera = new Label("Carrera");
        GridPane.setConstraints(lcarrera, 0, 0);
        
        // Label Nombre Malla
        Label nombremalla = new Label("Malla:");
        GridPane.setConstraints(nombremalla, 0, 1);
        
        // Label Nombre Ramo
        Label nombreramo = new Label("Ramo:");
        GridPane.setConstraints(nombreramo, 0, 2);
        
        // Ramo Box
        ComboBox<String> ramosbox = new ComboBox<>();
        GridPane.setConstraints(ramosbox, 1, 2);
        ramosbox.setDisable(true);
        
        // Malla Box
        ComboBox<String> mallabox = new ComboBox<>();
        GridPane.setConstraints(mallabox, 1, 1);
        mallabox.setDisable(true);
        mallabox.setOnAction(e -> {
            ramosbox.getSelectionModel().clearSelection();
            ramosbox.getItems().clear();
            if (mallabox.getValue() != null){
                ramosbox.setDisable(false);
                ArrayList<String> ramos = sistema.getRamosMalla(admin.carreratemp, mallabox.getValue());
                System.out.println(ramos);
                ramosbox.getItems().addAll(ramos);
            }
        });
        
        // Carrera Box
        ComboBox<String> carrerabox = new ComboBox<>();
        ArrayList<String> carreras = sr.getCarreras();
        carrerabox.getItems().addAll(carreras);
        GridPane.setConstraints(carrerabox, 1, 0);
        carrerabox.setOnAction(e -> {
            mallabox.getSelectionModel().clearSelection();
            mallabox.getItems().clear();
            ramosbox.getSelectionModel().clearSelection();
            ramosbox.getItems().clear();
            if (carrerabox.getValue() != null){
                mallabox.setDisable(false);
                admin.carreratemp = carrerabox.getValue();
                ArrayList<String> mallas = sr.getMallas(carrerabox.getValue());
                mallabox.getItems().addAll(mallas);
            }
        });
        
        //Mensaje
        Label mensaje = new Label("");
        
        //Button Ingresar
        Button Ingresar = new Button("Agregar Ramo a Malla");
        GridPane.setConstraints(Ingresar, 1, 4);
        Ingresar.setOnAction(e -> {
            if (carrerabox.getValue() == null || mallabox.getValue() == null || 
                    ramosbox.getValue() == null){
                mensaje.setText("Faltan campos");
        }else{
            Malla_Curricular ma = new Malla_Curricular(carrerabox.getValue(), 
                    mallabox.getValue());
            boolean aux = admin.AgregarRamoMalla(ma, ramosbox.getValue());
            if (aux){
                mensaje.setText("Ramo Agregado");
            } else {
                mensaje.setText("Error al agregar ramo");
            }
            }
            ramosbox.getItems().clear();
        });
        
        grid_ramoamalla.getChildren().addAll(lcarrera, carrerabox, nombremalla, 
                mallabox, nombreramo, ramosbox);
        
        
        VBox vb = new VBox();
        vb.setPadding(new Insets(30, 80, 10, 80));
        vb.setSpacing(10);
        
        vb.getChildren().addAll(grid_ramoamalla, Ingresar, mensaje);
        vb.setAlignment(Pos.CENTER);
        Scene scene_ramoamalla = new Scene(vb);
        windowRamoaMalla.setScene(scene_ramoamalla);
        windowRamoaMalla.show();
        
    }
    public void Calificar_profe(){
        
    Stage windowCalificar = new Stage();
        windowCalificar.initModality(Modality.APPLICATION_MODAL);
        windowCalificar.setTitle("Calificar");
        windowCalificar.setMinWidth(320);
        windowCalificar.setMinHeight(250);

        //GridPane
        GridPane grid_calificar = new GridPane();
        grid_calificar.setPadding(new Insets(10, 10, 10, 10));
        grid_calificar.setVgap(8);
        grid_calificar.setHgap(5);
        grid_calificar.setAlignment(Pos.CENTER);
        
        // Label Ramo
        Label lramo = new Label("Ramo:");
        GridPane.setConstraints(lramo, 0, 0);
        
        // Label Alumno
        Label lalumno = new Label("Alumno:");
        GridPane.setConstraints(lalumno, 0, 1);
        
        // Label Nota
        Label lnota = new Label("Nota:");
        GridPane.setConstraints(lnota, 0, 2);
        
        // Input Nota
        TextField inputnota = new TextField();
        GridPane.setConstraints(inputnota, 1, 2);
        
        // Alumno Box
        ComboBox<String> alumnobox = new ComboBox<>();
        GridPane.setConstraints(alumnobox, 1, 1);
        
        // Ramo Box
        ComboBox<String> ramobox = new ComboBox<>();
        GridPane.setConstraints(ramobox, 1, 0);
        ArrayList<String> ramos = profesor.getRamos();
        ramobox.getItems().addAll(ramos);
        ramobox.setOnAction(e -> {
            try{
            alumnobox.getItems().clear();
            ArrayList<String> alumnos = profesor.getAlumnos(ramobox.getValue());
            alumnobox.getItems().addAll(alumnos);
            }catch(Exception err){
                System.out.println(err);
            }
        });
        
        //Mensaje
        Label mensaje = new Label("");
        
        //Button Ingresar
        Button Ingresar = new Button("Ingresar");
        GridPane.setConstraints(Ingresar, 1, 4);
        Ingresar.setOnAction(e -> {
            if (ramobox.getValue() == null || alumnobox.getValue() == null || "".equals(inputnota.getText())){
                mensaje.setText("Falta campos");
            } else {
                try{
                    double nota= Double.parseDouble(inputnota.getText());
                    if (nota < 1 || nota > 7){
                        mensaje.setText("Nota mal ingresada. Debe estar entre 1 y 7");
                    } else {
                    boolean aux = profesor.CalificarAlumno(alumnobox.getValue(), 
                            ramobox.getValue(), nota);
                    if (aux){
                        mensaje.setText("Nota Ingresada");
                    } else {
                        mensaje.setText("Esta nota ya ha sido ingresada");
                    }
                    }
                } catch(Exception err){
                    System.out.println(err);
                    mensaje.setText("Error de incompatibilidad");
                }
            }
        });
        
        grid_calificar.getChildren().addAll(ramobox, alumnobox, 
                inputnota, lnota, lalumno, lramo);
        
        
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        
        vb.getChildren().addAll(grid_calificar, Ingresar, mensaje);
        Scene scene_calificar = new Scene(vb);
        windowCalificar.setScene(scene_calificar);
        windowCalificar.show();
        
    }
    public void ventanaResultadoBuscador(String Nombre, String Sigla, 
            String Facultad, String Profesor, ArrayList<String> horario){
        Stage windowResultadoBuscador = new Stage();
        Buscador busc = new Buscador();
        ArrayList<RamoFX> ramos = busc.Buscar(sr.getCarreras(), Nombre, Sigla, 
                Facultad, Profesor, horario);
        
        Scene scene_resultadobuscador = new Scene(new Group());
        TableView<RamoFX> table = new TableView<>();
        ObservableList<RamoFX> data = FXCollections.observableArrayList(ramos);
        windowResultadoBuscador.setTitle("Resultado Buscador");

 
        final Label label = new Label("Ramos");
        label.setFont(new Font("Arial", 15));
        table.setMinWidth(770);
        table.setMinHeight(550);
        table.setEditable(false);
 
        TableColumn col1 = new TableColumn("Sigla");
        TableColumn col2 = new TableColumn("Seccion");
        TableColumn col3 = new TableColumn("Nombre");
        TableColumn col4 = new TableColumn("Horario");
        TableColumn col5 = new TableColumn("Profesor");
        TableColumn col6 = new TableColumn("Facultad");
        TableColumn col7 = new TableColumn("Creditos");
        TableColumn col8 = new TableColumn("Retirable");
        
        col1.setCellValueFactory(
                new PropertyValueFactory<>("Sigla"));
        col1.setMinWidth(80);
        
        col2.setCellValueFactory(
                new PropertyValueFactory<>("Seccion"));
        col2.setMinWidth(80);
        
        col3.setCellValueFactory(
                new PropertyValueFactory<>("Nombre"));
        col3.setMinWidth(80);
        
        col4.setCellValueFactory(
                new PropertyValueFactory<>("Horario"));
        col4.setMinWidth(100);
        
        col5.setCellValueFactory(
                new PropertyValueFactory<>("Profesor"));
        col5.setMinWidth(100);
        
        col6.setCellValueFactory(
                new PropertyValueFactory<>("Facultad"));
        col6.setMinWidth(100);
        
        col7.setCellValueFactory(
                new PropertyValueFactory<>("Creditos"));
        col7.setMinWidth(80);
        
        col8.setCellValueFactory(
                new PropertyValueFactory<>("Retirable"));
        col8.setMinWidth(70);
        table.setItems(data);
        table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene_resultadobuscador.getRoot()).getChildren().addAll(vbox);
 
        windowResultadoBuscador.setScene(scene_resultadobuscador);
        windowResultadoBuscador.show();
    }
}
