import javax.swing.*;
import java.sql.*;

//8
public class MiClaseH2 {

    /*public static void main(String[] argv) throws SQLException {
        H2CreateExample createTableExample = new H2CreateExample();
        //createTableExample.eliminarTabla("libros");
        //createTableExample.crearTabla();
        //createTableExample.insertarRegistro();
        //createTableExample.leerRegistroId(1);

        //1
        //createTableExample.insertarRegistro1(1, "Patrick", "El Perfume");
        //createTableExample.insertarRegistroJOP();
        //2
        //createTableExample.leerRegistroAutor();
        //6
        //createTableExample.leerRegistroEditorial();
        //10
        //ArrayList<Libro> libros = createTableExample.leerRegistros("Paco");
    }*/
    Connection miConexion;
    //De esta forma, usando esta clase no hace falta crear una conexión distinta para cada clase
    public MiClaseH2() {
        try {
            miConexion = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void crearTabla() throws SQLException {
        try {
            Statement statement = miConexion.createStatement();
            String textoSQL="CREATE TABLE IF NOT EXISTS libros (id int(4) primary key, titulo varchar(60), autor varchar(40), editorial varchar (60));";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
    public void crearTablaFK() throws SQLException {
        try {
            Statement statement = miConexion.createStatement();
            String textoSQL="CREATE TABLE IF NOT EXISTS libros (id int(3) primary key, titulo varchar(60), autor varchar(40), idEditorial varchar(40) " +
                    "CONSTRAINT FK_Editorial FOREIGN KEY (idEditorial) REFERENCES Editoriales(id));";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void eliminarTabla(String tabla) throws SQLException {
        try {
            Statement statement = miConexion.createStatement();
            String textoSQL="DROP TABLE " + tabla + ";";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void insertarRegistro() throws SQLException{
        try  {
            String textoSQL="insert into libros (id, titulo, autor, editorial) values (?, ?, ?, ?)";

            PreparedStatement ps = miConexion.prepareStatement(textoSQL);
            ps.setInt(1, 1);
            ps.setString(2, "Panza de burro");
            ps.setString(3, "Andrea Abreu");
            ps.setString(4, "ANAYA");

            ps.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
    //1
    public void insertarRegistro1( int id, String titulo, String autor, String editorial) throws SQLException{
        try  {
            String textoSQL="insert into libros (id, titulo, autor, editorial) values (?, ?, ?, ?)";

            PreparedStatement ps = miConexion.prepareStatement(textoSQL);
            ps.setInt(1, id);
            ps.setString(2, titulo);
            ps.setString(3, autor);
            ps.setString(4, editorial);

            ps.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
    public void insertarRegistroJOP(){
        int param1 = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID:"));
        String param2 = JOptionPane.showInputDialog("Introduce el Título");
        String param3 = JOptionPane.showInputDialog("Introduce el Autor");
        String param4 = JOptionPane.showInputDialog("Introduce la Editorial");
        try {
            insertarRegistro1(param1,param2,param3,param4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void leerRegistroId(int id) throws SQLException{
        try{
            String textoSQL="select * from libros where id=?";

            PreparedStatement ps=miConexion.prepareStatement(textoSQL);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                System.out.println("Libro:" +  id + "," + titulo + "," + autor + ", " + editorial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //2
    public void leerRegistroAutor() throws SQLException{
        try{
            String autorJOP=JOptionPane.showInputDialog("Introduce el AUTOR a buscar");
            String textoSQL="select * from libros where lower(autor)=lower(?)";
            PreparedStatement ps=miConexion.prepareStatement(textoSQL);
            ps.setString(1, autorJOP );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                System.out.println("Libro:" + id + ", " + titulo + "," + autorJOP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //6
    public void leerRegistroEditorial() throws SQLException{
        try{
            String editorialJOP=JOptionPane.showInputDialog("Introduce la EDITORIAL a buscar");
            String textoSQL="select * from libros where lower(editorial)=lower(?)";

            PreparedStatement ps=miConexion.prepareStatement(textoSQL);
            ps.setString(1, editorialJOP);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                System.out.println("Libro:" + id + ", " + titulo + "," + autor + ", " + editorialJOP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}