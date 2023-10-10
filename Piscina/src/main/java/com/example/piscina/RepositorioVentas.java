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
                    "    adultos           INTEGER, menores INTEGER" +
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
                aux.setAdultos(rs.getInt("adultos"));
                aux.setMenores(rs.getInt("menores"));
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
        String sentenciaSql = "INSERT INTO ventas (adultos, menores, fecha, hora) VALUES (?, ?, ?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, v.getAdultos());
            sentencia.setInt(2, v.getMenores());
            sentencia.setDate(3, Date.valueOf(v.getFecha()));
            sentencia.setTime(4, Time.valueOf(v.getHora()));
            sentencia.executeUpdate();

            //Como el id es autoincremental, el .getGeneratedKeys te devuelve al objeto
            //Pero al crear la tabla se tiene que indicar algo, se verá más adelante
            /*ResultSet rs = sentencia.getGeneratedKeys();
            v.setId(rs.getInt(1));*/

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
