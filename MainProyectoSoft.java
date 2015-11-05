package mainproyectosoft;

import javafx.application.Application;
import javafx.scene.Group;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.geometry.Insets; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        
        //Modo Administrador
        
        
        //GridPane
        GridPane grid_admin = new GridPane();
        grid_admin.setPadding(new Insets(50, 50, 50, 50));
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
   
        //Agregando a grid
        grid_admin.getChildren().addAll(labelbienvenida_admin, RamoButton, MallaButton);
        
        scene_admin = new Scene(grid_admin, 250, 200); 
        
        //FIN modo Admin
        
        //GridPane
        GridPane grid0 = new GridPane();
        grid0.setPadding(new Insets(20, 20, 20, 20));
        grid0.setVgap(8);
        grid0.setHgap(10);
        

        //Username Label
        Label nameLabel = new Label("Nombre Usuario:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Username Input
        TextField nameInput = new TextField();
        nameInput.setPromptText("nombre usuario");
        GridPane.setConstraints(nameInput, 1, 0);

        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        //Password Input
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);

        //Login
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> validarUsuario(nameInput.getText(), 
                passInput.getText()));
        
        //Registrarse
        Button registerButton = new Button("Registrarse");
        GridPane.setConstraints(registerButton, 1, 3);
        registerButton.setOnAction(e -> window.setScene(scene1)); // se dirige a registro por primera vez

        //Agregando a grid
        grid0.getChildren().addAll(nameLabel, nameInput, passLabel, 
                passInput, loginButton);

        // scene0, grid0 = Menu Principal
        scene0 = new Scene(grid0, 320, 130);
        

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
        
        //Carrera
        ChoiceBox<String> carrera = new ChoiceBox<>();
        carrera.getItems().addAll("Ingenieria");
        carrera.setValue("Ingenieria");
        GridPane.setConstraints(carrera, 1, 5);
        
        //MallaLabel
        Label mallaLabel = new Label("Malla:");
        GridPane.setConstraints(mallaLabel, 0, 6);
        
        //Malla
        ChoiceBox<String> malla = new ChoiceBox<>();
        malla.getItems().addAll("Ingenieria-Malla1", "Ingenieria-Malla2");
        malla.setValue("Ingenieria-Malla1");
        GridPane.setConstraints(malla, 1, 6);
        
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
        grid2.setVgap(8);
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
        HistorialButton.setOnAction(e -> PreVentanaHistorial());
        
        //AvanceCurricular
        Button ACButton = new Button("Avance Curricular");
        GridPane.setConstraints(ACButton, 0, 3);
        ACButton.setOnAction(e -> PreVentanaAvance());
        
        //BuscadorCursos
        Button BuscadorButton = new Button("Buscador de Cursos");
        GridPane.setConstraints(BuscadorButton, 0, 4);
        BuscadorButton.setOnAction(e -> BuscadordeCursos());
   
        //Agregando a grid
        grid2.getChildren().addAll(labelbienvenida, SemestreButton, HistorialButton, ACButton, 
                BuscadorButton);
        
        // Sistema.poderCrearRamo()
        if (true){
            if (!grid2.getChildren().contains(SemestreButton)){
                grid2.getChildren().add(SemestreButton);
                
            }
            scene2 = new Scene(grid2, 290, 200);
        }else {
            if (grid2.getChildren().contains(SemestreButton)){
                grid2.getChildren().remove(SemestreButton);
                
            }
            scene2 = new Scene(grid2, 290, 160);
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
    private void validarUsuario(String username, String pass){
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
            window.setScene(scene2);
            
        }
        else if (id == 2){ //Admin
            window.setScene(scene_admin);
        }
        else if (id == 3){ //Profesor
            
        }
        else {
            //AlertBox Usuario Invalido
            Stage window1 = new Stage();
            window1.initModality(Modality.APPLICATION_MODAL);
            window1.setTitle("Invalido");
            window1.setMinWidth(250);

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
        windowBuscadordeCursos.setMinWidth(300);
        windowBuscadordeCursos.setMinHeight(450);

        //GridPane
        GridPane grid_buscacursos = new GridPane();
        grid_buscacursos.setPadding(new Insets(10, 10, 10, 10));
        grid_buscacursos.setVgap(8);
        grid_buscacursos.setHgap(5);

        //Semestres Label
        Label LabelSemestre = new Label("Semestre:");
        LabelSemestre.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelSemestre, 0, 0);

        //Semestre Input
        ChoiceBox<String> semestresbox = new ChoiceBox<>();
        semestresbox.getItems().add("Semestre Ejemplo");
        semestresbox.setValue("Semestre Ejemplo");
        GridPane.setConstraints(semestresbox, 1, 0);
        
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
        
        //Campus Label
        Label LabelCampus = new Label("Campus:");
        LabelCampus.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelCampus, 0, 4);

        //Campus Input
        ChoiceBox<String> campusbox = new ChoiceBox<>();
        campusbox.getItems().addAll("Todas", "Campus Ejemplo");
        campusbox.setValue("Todas");
        GridPane.setConstraints(campusbox, 1, 4);
        
        //Facultad Label
        Label LabelFacultad = new Label("Facultad:");
        LabelFacultad.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelFacultad, 0, 5);

        //Facultad Input
        ChoiceBox<String> facultadbox = new ChoiceBox<>();
        facultadbox.getItems().addAll("Todas", "Facultad Ejemplo");
        facultadbox.setValue("Todas");
        GridPane.setConstraints(facultadbox, 1, 5);
        
        //Horario Label
        Label LabelHorario = new Label("Horario");
        LabelHorario.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelHorario, 0, 6);
        
        //Dia Semana Label
        Label LabelDiadesemana = new Label("Dia de Semana:");
        LabelDiadesemana.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelDiadesemana, 0, 7);

        //Dia Semana Input
        ComboBox<String> diadesemanabox = new ComboBox<>();
        diadesemanabox.getItems().addAll("Todas", "L", "M", "W", "J", "V", "S");
        diadesemanabox.setValue("Todas");
        GridPane.setConstraints(diadesemanabox, 1, 7);
        
        //Modulo Label
        Label LabelModulo= new Label("Modulo:");
        LabelModulo.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(LabelModulo, 0, 8);

        //Modulo Input
        ChoiceBox<String> modulobox = new ChoiceBox<>();
        modulobox.getItems().addAll("Todas", "1", "2", "3", "4", "5", "6", "7",
                "8");
        modulobox.setValue("Todas");
        GridPane.setConstraints(modulobox, 1, 8);
        
        //Button Ingresar Horario
        Button ButtonAgregarHorario = new Button("Agregar Horario");
        GridPane.setConstraints(ButtonAgregarHorario, 0, 9);
        
        
        //Texto Horarios Agregados
        Label Labelagregarhorario = new Label("Horarios Agregados: ");
        GridPane.setConstraints(Labelagregarhorario, 0, 10);
        
        //Resultado Horarios Agregados
        Label Resultadoagregarhorario = new Label("");
        GridPane.setConstraints(Resultadoagregarhorario, 1, 10);
        ArrayList<String> horariosingresados = new ArrayList<>();
        
        ButtonAgregarHorario.setOnAction((ActionEvent event) -> {
            String texto_actual = Resultadoagregarhorario.getText();
            Resultadoagregarhorario.setText(texto_actual+diadesemanabox.getValue()+
                    ":"+modulobox.getValue()+" ");
            horariosingresados.add(diadesemanabox.getValue()+":"+modulobox.getValue());
            System.out.println(horariosingresados);
            
            });
        
        //Button Buscar
        Button ButtonBuscar = new Button("Buscar");
        GridPane.setConstraints(ButtonBuscar, 0, 12);
        
        //Display
        grid_buscacursos.getChildren().addAll(LabelSemestre, 
                semestresbox, LabelSigla, InputSigla, LabelNombrecurso, 
                InputNombrecurso, LabelProfesor, InputProfesor, LabelCampus, 
                campusbox, LabelFacultad, facultadbox, LabelHorario, 
                LabelDiadesemana, diadesemanabox, LabelModulo, modulobox, 
                ButtonAgregarHorario, Labelagregarhorario, 
                Resultadoagregarhorario, ButtonBuscar);
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
        ObservableList<RamoAvanceFX> data = FXCollections.observableArrayList(arrlstramos);
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
        
        //GridPane
        GridPane grid_preavance = new GridPane();
        grid_preavance.setPadding(new Insets(30, 120, 30, 120));
        grid_preavance.setVgap(15);
        grid_preavance.setHgap(15);
        
        // Label Elegir Malla
        Label labelElegirMalla = new Label("Elegir Malla:");
        GridPane.setConstraints(labelElegirMalla, 0, 1);
        
        // Elegir Malla Box
        ComboBox<String> Mallabox = new ComboBox<>();
        GridPane.setConstraints(Mallabox, 1, 1);
                
        // Label Elegir Carrera
        Label labelElegirCarrera = new Label("Elegir Carrera:");
        GridPane.setConstraints(labelElegirCarrera, 0, 0);
        
        // Elegir Carrera Box
        ComboBox<String> carrerabox = new ComboBox<>();
        String[] carreras = alumno.getCarrera();
        for (int i = 0; i< carreras.length; i++){
            if (!"".equals(carreras[i])){
            carrerabox.getItems().add(carreras[i]);
            }
        }
        GridPane.setConstraints(carrerabox, 1, 0);
        carrerabox.setOnAction(e -> {
            Mallabox.getItems().clear();
            ArrayList<Malla_Curricular> mallas = 
                    alumno.getMallas(carrerabox.getValue());
            Iterator<Malla_Curricular> iterador = mallas.iterator();
            while (iterador.hasNext()){
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
        
        Scene scene_preavance = new Scene(grid_preavance, 520, 200);
        windowpreAvance.setScene(scene_preavance);
        windowpreAvance.setTitle("Avance Curricular");
        windowpreAvance.show();
        
    }
    
    private void PreVentanaHistorial(){
        Stage windowpreHistorial = new Stage();
        
        //GridPane
        GridPane grid_prehistorial = new GridPane();
        grid_prehistorial.setPadding(new Insets(30, 85, 30, 85));
        grid_prehistorial.setVgap(15);
        grid_prehistorial.setHgap(15);
                
        // Label Elegir Periodo
        Label labelElegirPeriodo = new Label("Elegir Periodo:");
        GridPane.setConstraints(labelElegirPeriodo, 0, 0);
        
        // Elegir Periodo Box
        ChoiceBox<String> periodobox = new ChoiceBox<>();
        periodobox.getItems().addAll("Periodo1", "Periodo2");
        periodobox.setValue("Periodo1");
        GridPane.setConstraints(periodobox, 0, 1);
        
        //Button Ingresar
        Button ingresar = new Button("Ingresar Periodo");
        GridPane.setConstraints(ingresar, 0, 2);
        
        //Agregando a grid
        grid_prehistorial.getChildren().addAll(labelElegirPeriodo, periodobox, 
                ingresar);
        
        Scene scene_prehistorial = new Scene(grid_prehistorial, 290, 200);
        windowpreHistorial.setScene(scene_prehistorial);
        windowpreHistorial.show();
        
    }
    private void VentanaHistorial(){
        Stage windowHistorial = new Stage();
        
        Scene scene_historial = new Scene(new Group());
        TableView table = new TableView();
        windowHistorial.setTitle("Historial");
        windowHistorial.setWidth(350);
        windowHistorial.setHeight(500);
 
        final Label label = new Label("Ramos");
        label.setFont(new Font("Arial", 15));
 
        table.setEditable(false);
 
        TableColumn col1 = new TableColumn("Nombre");
        TableColumn col2 = new TableColumn("Nota");
        
        table.getColumns().addAll(col1, col1, col2);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
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
        InputHorario.setPromptText("M-j:4");
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
        
        //Button Ingresar
        Button ButtonIngresarRamo = new Button("Ingresar Ramo");
        GridPane.setConstraints(ButtonIngresarRamo, 1, 11);
        
        //Display
        grid_crearamo.getChildren().addAll(LabelSigla, InputSigla, LabelNombre, 
                InputNombre, LabelProfesor, InputProfesor, LabelCampus, 
                Inputcampus, LabelHorario, InputHorario, LabelCreditos, 
                Inputcreditos, LabelRetirable, retirablebox, LabelSeccion,
                InputSeccion, LabelComentario, Inputcomentario, 
                ButtonIngresarRamo, LabelSala, InputSala);
        Scene scene_crearamo = new Scene(grid_crearamo);
        windowCrearRamo.setScene(scene_crearamo);
        windowCrearRamo.show();
    }
}
