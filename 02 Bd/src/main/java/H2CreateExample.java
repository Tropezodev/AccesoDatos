import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/*
Ejercicios:
    1-Crea un nuevo método para insertar registros en la tabla libros con parámetros id, título y autor: insertar(int id, String titulo, String autor)
    2-Crea un nuevo método que muestre los libros de un autor que se le pase como parámetro
    3-Utiliza el método eliminarTabla para eliminar la tabla de libros y después crearla de nuevo añadiendo un campo String llamado editorial. (En H2 no es imprescindible, pero...)
    4-Modifica también el método para insertar libros añadiendo el nuevo campo
    5-Utiliza el método anterior para añadir 5 libros en los que se repitan las editoriales y que todos no sean de la misma
    6-Crea un método que muestre un listado en pantalla con los libros de una editorial que se pase como parámetro
    7-Crea una nueva clase idéntica a esta llamada MiClaseH2 que tenga una propiedad para "almacenar" la conexión a la BBDD
    8-Modifica los métodos de esta nueva clase para que usen dicha conexión y no "conecten" cada vez que se llaman
    9-Crea una clase llamada Libro con las propiedades adecuadas según la tabla "libros" creada en la base de datos
    10-Crea un método que devuelva en forma de ArrayList<Libro> todos los libros de un autor
    (Opcional) Crea una tabla llamada editoriales con los campos adecuados y usa el método crearTablaFK para relacionar editoriales y libros
 */

public class H2CreateExample {

    public static void main(String[] argv) throws SQLException {
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
            Libro Libro=new Libro();
            ArrayList<Libro> libros = Libro.leerRegistros("Paco");
            //Libro.leerRegistroAutor();

    }

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
    public void crearTabla() throws SQLException {
        try {
            Connection connection = H2JDBCUtils.getConnection();//llamamos a la conexión de otra clase, daría igual
            Statement statement = connection.createStatement();
            String textoSQL="CREATE TABLE IF NOT EXISTS libros (id int(4) primary key, titulo varchar(60), autor varchar(40), idEditorial int (4));";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
    public void crearTablaFK() throws SQLException {
        try {
            Connection connection = H2JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            String textoSQL="CREATE TABLE IF NOT EXISTS libros (id int(3) primary key, titulo varchar(60), autor varchar(40), idEditorial int(4) " +
                    "CONSTRAINT FK_Editorial FOREIGN KEY (idEditorial) REFERENCES Editoriales(id));";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void eliminarTabla(String tabla) throws SQLException {
        try {
            Connection connection = H2JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            String textoSQL="DROP TABLE " + tabla + ";";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void insertarRegistro() throws SQLException{
        try  {
            Connection connection = H2JDBCUtils.getConnection();

            String textoSQL="insert into libros (id, titulo, autor, idEditorial) values (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(textoSQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Panza de burro");
            preparedStatement.setString(3, "Andrea Abreu");
            preparedStatement.setInt(4, 1);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
   //1
    public void insertarRegistro1( int id, String titulo, String autor, int idEditorial) throws SQLException{
        try  {
            Connection connection = H2JDBCUtils.getConnection();

            String textoSQL="insert into libros (id, titulo, autor, idEditorial) values (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(textoSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, titulo);
            preparedStatement.setString(3, autor);
            preparedStatement.setInt(4, idEditorial);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
    public void insertarRegistroJOP(){
        int param1 = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID:"));
        String param2 = JOptionPane.showInputDialog("Introduce el Título");
        String param3 = JOptionPane.showInputDialog("Introduce el Autor");
        int param4 = Integer.parseInt(JOptionPane.showInputDialog("Introduce la idEditorial"));
        try {
            insertarRegistro1(param1,param2,param3,param4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void leerRegistroId(int id) throws SQLException{
        try{
            Connection connection=getConnection();
            String textoSQL="select * from libros where id=?";

            PreparedStatement preparedStatement=connection.prepareStatement(textoSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int idEditorial = rs.getInt("idEditorial");
                System.out.println("Libro:" +  id + "," + titulo + "," + autor + ", " + idEditorial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   //2
   public void leerRegistroAutor() throws SQLException{
       try{
           String autorJOP=JOptionPane.showInputDialog("Introduce el AUTOR a buscar");
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
   //6
   public void leerRegistroEditorial() throws SQLException{
       try{
           int editorialJOP=Integer.parseInt(JOptionPane.showInputDialog("Introduce la IdEditorial a buscar"));
           Connection connection=getConnection();
           String textoSQL="select * from libros where lower(editorial)=lower(?)";

           PreparedStatement preparedStatement=connection.prepareStatement(textoSQL);
           preparedStatement.setInt(1, editorialJOP);

           ResultSet rs = preparedStatement.executeQuery();

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
