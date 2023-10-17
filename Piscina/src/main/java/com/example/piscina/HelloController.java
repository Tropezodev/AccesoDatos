package com.example.piscina;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.nio.file.attribute.FileTime;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Button btnInsertar;

    @FXML
    private TableColumn<Venta, Integer> colAdultos;

    @FXML
    private TableColumn<Venta, Date> colFecha;

    @FXML
    private TableColumn<Venta, Time> colHora;

    @FXML
    private TableColumn<Venta, Integer> colId;

    @FXML
    private TableColumn<Venta, Integer> colMenores;

    @FXML
    private TableColumn<Venta, String> colNombre;

    @FXML
    private TableColumn<Venta, Float> colPrecio_A;

    @FXML
    private TableColumn<Venta, Float> colPrecio_M;

    @FXML
    private Label lbAdultos;

    @FXML
    private Label lbFechaHora;

    @FXML
    private Label lbMenores;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbTotal;

    @FXML
    private TableView<Venta> tbVentas;

    @FXML
    private TextField txtAdultos;

    @FXML
    private TextField txtMenores;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTotal;

    @FXML
    void insertar(ActionEvent event) {

    }
    private RepositorioVentas repositorio;

    public void inicialize (URL url, ResourceBundle resourceBundle){
        repositorio=new RepositorioVentas();

        final ObservableList<Venta> ventas=repositorio.leerTodos();
        colId.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Venta, String>("nombre"));
        colAdultos.setCellValueFactory(new PropertyValueFactory<Venta,Integer>("adultos"));
        colPrecio_A.setCellValueFactory(new PropertyValueFactory<Venta,Float>("precio_adultos"));
        colMenores.setCellValueFactory(new PropertyValueFactory<Venta,Integer>("menores"));
        colPrecio_M.setCellValueFactory(new PropertyValueFactory<Venta,Float>("precio_menores"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Venta,Date>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Venta,Time>("hora"));
    }
    public void refrescaTabla(){
        RepositorioVentas repositorio = new RepositorioVentas();
        final ObservableList<Venta> ventas = repositorio.leerTodos();
    }
    public void insertar(){
        Venta v=new Venta();
        //No se pone el ID porque lo genera la DB directamente
        v.setNombre(txtNombre.getText());
        v.setAdultos(Integer.parseInt(txtAdultos.getText()));
        v.setPrecioAdultos(v.getPrecioAdultos());
        v.setMenores(Integer.parseInt(txtMenores.getText()));
        v.setPrecioMenores(v.getPrecioMenores());
        v.setFecha(LocalDate.from(v.getFecha()));
        v.setHora(LocalTime.from(v.getHora()));

        RepositorioVentas rv = new RepositorioVentas();
        rv.insertar(v);
        refrescaTabla();
    }
    /*public void callbackClicTable (javafx.scene.input.MouseEvent mouseEvent){
        Venta v = (Venta) tbVentas.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(v.getId()));
        txtNombre.setText(t.getNombre());
        .setText(t.getApellidos());
        txtDNI.setText(t.getDni());
    }*/
}
