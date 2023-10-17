package com.example.piscina;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.nio.file.attribute.FileTime;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Venta v=new Venta();



        repositorio = new RepositorioVentas();

        final ObservableList<Venta> ventas = repositorio.leerTodos();
        colId.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("id"));//Aquí va el nombre de la variable, no de la tabla
        colNombre.setCellValueFactory(new PropertyValueFactory<Venta, String>("nombre"));
        colAdultos.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("adultos"));
        colPrecio_A.setCellValueFactory(new PropertyValueFactory<Venta, Float>("precioAdultos"));
        colMenores.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("menores"));
        colPrecio_M.setCellValueFactory(new PropertyValueFactory<Venta, Float>("precioMenores"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Venta, Date>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Venta, Time>("hora"));

        tbVentas.setItems(ventas);
    }

    public void refrescaTabla() {
        final ObservableList<Venta> ventas = repositorio.leerTodos();
        txtMenores.clear();
        txtAdultos.clear();
        txtNombre.clear();
        txtTotal.clear();
        tbVentas.setItems(ventas);
    }

    public void vender() {
        Venta v = new Venta();
        //No se pone el ID porque lo genera la DB directamente
        v.setNombre(txtNombre.getText());
        v.setAdultos(Integer.parseInt(txtAdultos.getText()));
        v.setPrecioAdultos(v.getPrecioAdultos());
        v.setMenores(Integer.parseInt(txtMenores.getText()));
        v.setPrecioMenores(v.getPrecioMenores());
        v.setFecha(LocalDate.now());
        v.setHora(LocalTime.now());

       repositorio.insertar(v);
       refrescaTabla();
    }
    public void total() {
        Venta v = new Venta();
        float total;
        if (txtAdultos.getText().equals("")){
            txtAdultos.setText("0");
        }
        if (txtMenores.getText().equals("")){
            txtMenores.setText("0");
        }
        total= (Float.parseFloat(txtAdultos.getText())*v.getPrecioAdultos()) + ((Float.parseFloat(txtMenores.getText()))*v.getPrecioAdultos());
        txtTotal.setText(String.valueOf(total));
    }
}
