package com.example.fichaje;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;


//Realiza el método Update (cogiendo el id)
//Realiza el método Detele (borrar registro para alumno y otro para int id)

//CRUD=Create/Read/Update/Delete
@Data
public class RepositorioTrabajadores {
    //Sería interesante hacer una clase para las conexiones
    Connection conexion;
    //Es interesante llamar al método Create Table para que si no hay tabla que la cree
    public RepositorioTrabajadores(){
        setConexion();
    }
    public void setConexion(){
        try {
            // db parameters
            //jdbc:sqlite:sqlite/alumnos.db significa jdbc:SGBD:Carpeta/archivo donde se guarda la db
            Class.forName( "com.mysql.cj.jdbc.Driver");            // create a connection to the database
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/fichajes?createDatabaseIfNotExist=true", "root", "");
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
            String CREATE_TABLE_SQL="CREATE TABLE IF NOT EXISTS trabajadores (" +
                    "    id               INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    nombre           VARCHAR (50), apellidos VARCHAR(50), dni varchar(15), " +
                    "    departamento VARCHAR(50)" +
                    ");";
            stmt.executeUpdate(CREATE_TABLE_SQL);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public ArrayList<Trabajador> leerTodos(){
        ArrayList<Trabajador> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM alumnos");
            ResultSet rs=ps.executeQuery();
            lista=new ArrayList<>();
            while(rs.next()){
                Trabajador aux=new Trabajador();
                aux.setId(rs.getInt("id"));
                aux.setDni(rs.getString("dni"));
                aux.setNombre(rs.getString("nombre"));
                aux.setApellidos(rs.getString("apellidos"));
                aux.setDepartamento(rs.getString("departamento"));
                lista.add(aux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public ObservableList<Trabajador> leerTodosFX(){
        ObservableList<Trabajador> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM trabajadores");
            ResultSet rs=ps.executeQuery();
            lista= FXCollections.observableArrayList();//Método estático que genera (=new...)
            while(rs.next()){
                Trabajador aux=new Trabajador();
                aux.setId(rs.getInt("id"));
                aux.setDni(rs.getString("dni"));
                aux.setNombre(rs.getString("nombre"));
                aux.setApellidos(rs.getString("apellidos"));
                aux.setDepartamento(rs.getString("departamento"));
                lista.add(aux);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public void insert(Trabajador a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO trabajadores (nombre, apellidos, dni, departamento) VALUES (?, ?, ?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getNombre());
            sentencia.setString(2, a.getApellidos());
            sentencia.setString(3, a.getDni());
            sentencia.setString(4, a.getDepartamento());
            sentencia.executeUpdate();

            ResultSet rs = sentencia.getGeneratedKeys();
            a.setId(rs.getInt(1));

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    //Se suele devolver un boolean o un int para controlar las excepciones
    public void update(Trabajador trabajador){
        PreparedStatement sentencia=null;
        //Se tiene que poner igual que en la base de datos. El estándar es en minúscula y los espacios con "_"
        String sentenciaSQL="UPDATE trabajadores SET nombre=?, apellidos=?, dni=?, departamento=? WHERE id=?";
        try {
            sentencia=conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1,trabajador.getNombre());
            sentencia.setString(2,trabajador.getApellidos());
            sentencia.setString(3,trabajador.getDni());
            sentencia.setString(4,trabajador.getDepartamento());
            sentencia.setInt(5,trabajador.getId());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        PreparedStatement sentencia=null;
        String sentenciaSQL="DELETE from trabajadores WHERE id=?";
        try{
            sentencia=conexion.prepareStatement(sentenciaSQL);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
