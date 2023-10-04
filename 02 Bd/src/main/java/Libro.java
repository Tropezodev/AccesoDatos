import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

//A las clases se le nombra singular y primera letra mayúscula (Libro)
//A las tablas se le nombra en plural y minúscula (libros)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//9
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String Editorial;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public void leerRegistroAutor() throws SQLException {
        try{
            String autorJOP= JOptionPane.showInputDialog("Introduce el AUTOR a buscar");
            Connection connection=getConnection();
            String textoSQL="select * from libros where lower(autor)=lower(?)";
            PreparedStatement preparedStatement=connection.prepareStatement(textoSQL);
            preparedStatement.setString(1, autorJOP );

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                System.out.println("Libro:" + id + ", " + titulo + "," + autorJOP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Libro> leerRegistros(String autor ){
        Connection miConexion=getConnection();
        String textoSQL="SELECT * FROM libros WHERE autor=?";
        ArrayList<Libro> arrayLibros = new ArrayList<>();
        try{
            PreparedStatement ps=miConexion.prepareStatement(textoSQL);
            ps.setString(1, autor);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                /*Libro aux=new Libro();
                aux.getId(((rs.getInt("id"));
                aux.setTitulo(rs.getString("titulo"));
                aux.setAutor(rs.getString("autor"));
                aux.setEditorial(rs.getString("editorial"));
                arrayLibros.add(aux);*/
                arrayLibros.add(new Libro(rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial")));
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    return arrayLibros;
    }
}

