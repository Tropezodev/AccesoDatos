package com.example.todolist;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    Tarea t;

    RepositorioTarea repoTarea;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnModificar;

    @FXML
    private CheckBox chbRealizada;

    @FXML
    private TableColumn<Tarea, Integer> colIDCompletada;

    @FXML
    private TableColumn<Tarea, Integer> colIDPendiente;

    @FXML
    private TableColumn<Tarea, String> colTareaCompletada;

    @FXML
    private TableColumn<Tarea, String> colTareaPendiente;

    @FXML
    private Label lbID;

    @FXML
    private TableView<Tarea> tbCompletadas;

    @FXML
    private TableView<Tarea> tbPendientes;

    @FXML
    private TextField txtTarea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repoTarea = new RepositorioTarea();

        final ObservableList<Tarea> tareas_c=repoTarea.leerTodosCompletadasFX();
        colIDCompletada.setCellValueFactory(new PropertyValueFactory<Tarea, Integer>("id"));
        colTareaCompletada.setCellValueFactory(new PropertyValueFactory<Tarea, String>("tarea"));
        tbCompletadas.setItems(tareas_c);

        final ObservableList<Tarea> tareas_p=repoTarea.leerTodosPendientesFX();
        colIDPendiente.setCellValueFactory(new PropertyValueFactory<Tarea, Integer>("id"));
        colTareaPendiente.setCellValueFactory(new PropertyValueFactory<Tarea, String>("tarea"));
        tbPendientes.setItems(tareas_p);
        refrescaTabla();
    }
    public void refrescaTabla(){

        RepositorioTarea repo = new RepositorioTarea();
        final ObservableList<Tarea> tareas_c = repo.leerTodosCompletadasFX();
        final ObservableList<Tarea> tareas_p = repo.leerTodosPendientesFX();
        tbCompletadas.setItems(tareas_c);
        tbPendientes.setItems(tareas_p);

        txtTarea.clear();
        lbID.setText("");
        chbRealizada.setSelected(false);
    }
    public void insertar(){
        if (!txtTarea.getText().equals("")){
            Tarea t=new Tarea();
            //No se pone el ID porque lo genera la DB directamente
            t.setTarea(txtTarea.getText());
            t.setTerminada(chbRealizada.isSelected());
            RepositorioTarea rt = new RepositorioTarea();
            rt.insert(t);
            refrescaTabla();
        }
    }
    public void callbackClicTable (javafx.scene.input.MouseEvent mouseEvent){
        t = (Tarea) tbPendientes.getSelectionModel().getSelectedItem();
        lbID.setText(String.valueOf(t.getId()));
        txtTarea.setText(t.getTarea());
        chbRealizada.setSelected(t.isTerminada());
    }

    public void callbackClicTable1 (javafx.scene.input.MouseEvent mouseEvent){
        t = (Tarea) tbCompletadas.getSelectionModel().getSelectedItem();
//        lbID.setText(String.valueOf(t.getId()));
//        txtTarea.setText(t.getTarea());
//        chbRealizada.setSelected(t.isTerminada());
    }
    public void modificar(){
        Tarea t= new Tarea();
        t.setId(Integer.parseInt(lbID.getText()));
        t.setTarea(txtTarea.getText());
        t.setTerminada(chbRealizada.isSelected());

        repoTarea.update(t);

        refrescaTabla();
    }
    public void borrar(){
        RepositorioTarea r=new RepositorioTarea();
        r.delete(t.getId());
        refrescaTabla();
    }


}
