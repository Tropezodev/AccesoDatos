package com.example.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;


//Realiza el método Update (cogiendo el id)
//Realiza el método Detele (borrar registro para alumno y otro para int id)

//CRUD=Create/Read/Update/Delete
public class RepositorioTarea {
    //Sería interesante hacer una clase para las conexiones
    Connection conexion;
    //Es interesante llamar al método Create Table para que si no hay tabla que la cree
    public RepositorioTarea(){
        setConexion();
    }
    public void setConexion(){
        try {
            // db parameters
            //jdbc:sqlite:sqlite/alumnos.db significa jdbc:SGBD:Carpeta/archivo donde se guarda la db
            Class.forName( "com.mysql.cj.jdbc.Driver");            // create a connection to the database
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist?createDatabaseIfNotExist=true", "root", "");
            System.out.println("Connection to mysql has been established.");
            createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTable(){
        Statement stmt=null;
        try {
            stmt = conexion.createStatement();
            //El id se genera automáticamente con el AUTOINCREMENT para evitar líos al crear registros (nunca meter id)
            String CREATE_TABLE_SQL="CREATE TABLE IF NOT EXISTS tareas (" +
                    "    id               INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    tarea           VARCHAR (50)," +
                    "    terminada BOOLEAN" +
                    ");";
            stmt.executeUpdate(CREATE_TABLE_SQL);

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public ObservableList<Tarea> leerTodosPendientesFX(){
        ObservableList<Tarea> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM tareas WHERE terminada=false"); //WHERE NOT terminada
            ResultSet rs=ps.executeQuery();
            lista= FXCollections.observableArrayList();//Método estático que genera (=new...)
            while(rs.next()){
                Tarea aux=new Tarea();
                aux.setId(rs.getInt("id"));
                aux.setTarea(rs.getString("tarea"));
                aux.setTerminada(rs.getBoolean("terminada"));
                lista.add(aux);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public ObservableList<Tarea> leerTodosCompletadasFX(){
        ObservableList<Tarea> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM tareas WHERE terminada=true"); //WHERE terminada
            ResultSet rs=ps.executeQuery();
            lista= FXCollections.observableArrayList();//Método estático que genera (=new...)
            while(rs.next()){
                Tarea aux=new Tarea();
                aux.setId(rs.getInt("id"));
                aux.setTarea(rs.getString("tarea"));
                aux.setTerminada(rs.getBoolean("terminada"));
                lista.add(aux);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void insert(Tarea a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO tareas (tarea, terminada) VALUES (?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getTarea());
            sentencia.setBoolean(2, a.isTerminada());

            sentencia.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    //Se suele devolver un boolean o un int para controlar las excepciones
    public void update(Tarea tarea){
        PreparedStatement sentencia=null;
        //Se tiene que poner igual que en la base de datos. El estándar es en minúscula y los espacios con "_"
        String sentenciaSQL="UPDATE tareas SET tarea=?, terminada=? WHERE id=?";
        try {
            sentencia=conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1,tarea.getTarea());
            sentencia.setBoolean(2,tarea.isTerminada());
            sentencia.setInt(3,tarea.getId());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        PreparedStatement sentencia=null;
        String sentenciaSQL="DELETE from tareas WHERE id=?";
        try{
            sentencia=conexion.prepareStatement(sentenciaSQL);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
