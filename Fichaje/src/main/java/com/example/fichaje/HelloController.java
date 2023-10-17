package com.example.fichaje;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnFichaje;

    @FXML
    private Button btnIsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private ComboBox<Trabajador> cmbDepartamento;

    @FXML
    private TableColumn<Trabajador, String> colApellidosCRUD;

    @FXML
    private TableColumn<Trabajador, String> colApellidosTrabajadores;

    @FXML
    private TableColumn<Trabajador, String> colDNICRUD;

    @FXML
    private TableColumn<Trabajador, String> colDNITrabajadores;

    @FXML
    private TableColumn<Trabajador, String> colDepartamentoCRUD;

    @FXML
    private TableColumn<Trabajador, String> colDepartamentoTrabajadores;

    @FXML
    private TableColumn<Trabajador, ?/*Date*/> colFEntradaFichaje;

    @FXML
    private TableColumn<Trabajador, ?/*Date?*/> colFSalidaFichaje;

    @FXML
    private TableColumn<Trabajador, ?/*Date?*/> colHEntradaFichaje;

    @FXML
    private TableColumn<Trabajador, ?/*Date?*/> colHSalidaFichaje;

    @FXML
    private TableColumn<Trabajador, Integer> colIdCRUD;

    @FXML
    private TableColumn<Trabajador, Integer> colIdFichaje;

    @FXML
    private TableColumn<Trabajador, Integer> colIdTrabajadores;

    @FXML
    private TableColumn<Trabajador, String> colNombreCRUD;

    @FXML
    private TableColumn<Trabajador, String> colNombreFichaje;

    @FXML
    private TableColumn<Trabajador, String> colNombreTrabajadores;

    @FXML
    private Label lbReloj;

    @FXML
    private TableView<Trabajador> tbCRUDTrabajadores;

    @FXML
    private TableView<Trabajador> tbFichaje;

    @FXML
    private TableView<Trabajador> tbTrabajadores;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;


    private RepositorioTrabajadores repositorio;

    public void inicialize (URL url, ResourceBundle resourceBundle){
        repositorio=new RepositorioTrabajadores();

        final ObservableList<Trabajador> trabajadores=repositorio.leerTodosFX();
        colIdCRUD.setCellValueFactory(new PropertyValueFactory<Trabajador, Integer>("id"));
        colNombreCRUD.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
        colApellidosCRUD.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("apellidos"));
        colDepartamentoCRUD.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("departamento"));

        //Carga las opciones de departamentos en el combo
        ObservableList<String> listaDepartamentos = FXCollections.observableArrayList("Administración","Producción","Limpieza");
        //cmbDepartamento.setItems(listaDepartamentos);
        cmbDepartamento.getSelectionModel().selectFirst();
        //tbCRUDTrabajadores.setItems

    }
    public void refrescaTabla(){
        RepositorioTrabajadores repositorio = new RepositorioTrabajadores();
        final ObservableList<Trabajador> trabajadores = repositorio.leerTodosFX();
        //tbTrabajador.setItems(trabajadores);
    }
    public void insertar(){
        Trabajador t=new Trabajador();
        //No se pone el ID porque lo genera la DB directamente
        t.setNombre(txtNombre.getText());
        t.setApellidos(txtApellidos.getText());
        t.setDni(txtDNI.getText());
        //t.setDepartamento();

        RepositorioTrabajadores rt = new RepositorioTrabajadores();
        rt.insert(t);
        refrescaTabla();
    }
    public void callbackClicTable (javafx.scene.input.MouseEvent mouseEvent){
        Trabajador t = (Trabajador) tbCRUDTrabajadores.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(t.getId()));
        txtNombre.setText(t.getNombre());
        txtApellidos.setText(t.getApellidos());
        txtDNI.setText(t.getDni());
        //Combo Departamento*/
    }
    public void modificar(){
        Trabajador t= new Trabajador();
        t.setId(Integer.parseInt(txtId.getText()));
        t.setNombre(txtNombre.getText());
        t.setApellidos(txtApellidos.getText());
        t.setDni(txtDNI.getText());
        //Combo Departamento: t.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));

        RepositorioTrabajadores r= new RepositorioTrabajadores();
        r.update(t);
        refrescaTabla();
    }
    public void borrar(){
        RepositorioTrabajadores r=new RepositorioTrabajadores();
        r.delete(Integer.parseInt(txtId.getText()));
        refrescaTabla();
    }
}
