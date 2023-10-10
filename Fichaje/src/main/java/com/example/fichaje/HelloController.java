package com.example.fichaje;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnIsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private ComboBox<Trabajador> cmbDepartamento;

    @FXML
    private TableColumn<Trabajador, String> colApellidos;

    @FXML
    private TableColumn<Trabajador, String> colDNI;

    @FXML
    private TableColumn<Trabajador, String> colDepartamento;

    @FXML
    private TableColumn<Trabajador, Integer> colId;

    @FXML
    private TableColumn<Trabajador, String> colNombre;

    @FXML
    private TableView<Trabajador> tbView;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tbFichaje;

    @FXML
    private Tab tbTrabajador;

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
        colId.setCellValueFactory(new PropertyValueFactory<Trabajador, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("apellidos"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("departamento"));

        //Carga las opciones de departamentos en el combo
        ObservableList<String> listaDepartamentos = FXCollections.observableArrayList("Administración","Producción","Limpieza");
        //cmbDepartamento.setItems(listaDepartamentos);
        cmbDepartamento.getSelectionModel().selectFirst();

        //tbTrabajador.setItems


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
        //Combo Departamento: alumno.setDepartamento(txtDepartamento.getText());

        RepositorioTrabajadores rt = new RepositorioTrabajadores();
        rt.insert(t);
        refrescaTabla();
    }
    public void callbackClicTable (javafx.scene.input.MouseEvent mouseEvent){

        /*Trabajador t = (Trabajador) tbTrabajador.getSelectionModel().getSelectedItem();
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
