import java.sql.*;

//8
public class MiClaseH2 {
    Connection miConexion;

    //De esta forma, usando esta clase no hace falta crear una conexión distinta para cada clase
    public MiClaseH2() {
        try {
            miConexion = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void librosAutor(String autor){
        String textoSQL="SELECT id, titulo FROM libros WHERE autor=?";
        try {
            PreparedStatement ps = miConexion.prepareStatement(textoSQL);
            ps.setString(1, autor);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("titulo"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}