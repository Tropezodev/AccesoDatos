package com.example.piscina;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;


public class RepositorioVentas {

    Connection conexion;

    public RepositorioVentas() {
        setConexion();
    }

    public void setConexion() {
        try {
            // db parameters
            //jdbc:sqlite:sqlite/alumnos.db significa jdbc:SGBD:Carpeta/archivo donde se guarda la db
            Class.forName("com.mysql.cj.jdbc.Driver");// create a connection to the database
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventasdb?createDatabaseIfNotExist=true", "root", "");
            System.out.println("Connection to mysql has been established.");
            createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() {
        Statement stmt = null;
        try {
            stmt = conexion.createStatement();
            //El id se genera automáticamente con el AUTOINCREMENT para evitar líos al crear registros (nunca meter id)
            String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ventas (" +
                    "    id               INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    adultos           INTEGER, precio_adultos FLOAT" +
                    "    menores           INTEGER, precio_menores FLOAT" +
                    "    fecha DATE, hora TIME" +
                    ");";

            stmt.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ObservableList<Venta> leerTodos() {
        ObservableList<Venta> lista = null;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM ventas");
            ResultSet rs = ps.executeQuery();
            lista = FXCollections.observableArrayList();
            while (rs.next()) {
                Venta aux = new Venta();
                aux.setId(rs.getInt("id"));
                aux.setNombre(rs.getString("nombre"));
                aux.setAdultos(rs.getInt("adultos"));
                aux.setPrecioAdultos(rs.getFloat("precio_adultos"));
                aux.setMenores(rs.getInt("menores"));
                aux.setPrecioAdultos(rs.getFloat("precio_menores"));
                aux.setFecha(rs.getDate("fecha").toLocalDate());
                aux.setHora(rs.getTime("hora").toLocalTime());
                lista.add(aux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void insertar(Venta v) {
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO ventas (nombre, adultos, precio_adultos, menores, precio_menores, fecha, hora ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, v.getNombre());
            sentencia.setInt(2, v.getAdultos());
            sentencia.setFloat(3, v.getPrecioAdultos());
            sentencia.setInt(4, v.getMenores());
            sentencia.setFloat(5, v.getPrecioMenores());
            sentencia.setDate(6, Date.valueOf(v.getFecha()));
            sentencia.setTime(7, Time.valueOf(v.getHora()));

            sentencia.executeUpdate( );

            //Como el id es autoincremental, el .getGeneratedKeys te devuelve al objeto
            //Pero al crear la tabla se tiene que indicar algo, se verá más adelante
            ResultSet rs = sentencia.getGeneratedKeys();
            v.setId(rs.getInt(1));

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}