package com.example.fichaje;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Statement;
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
    private Tab tbCrud;

    @FXML
    private Tab tbFichaje;

    @FXML
    private TabPane tblTrabajadores;

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
    }

}
