import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

//A las clases se le nombra singular y primera letra mayúscula (Libro)
//A las tablas se le nombra en plural y minúscula (libros)
@Getter
@Setter
@AllArgsConstructor
//9
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int idEditorial;
    Connection miConexion;
    public Libro() {
        try{
            //Método con la conexión con la BD de H2
            miConexion = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Libro(int id, String titulo, String autor, int idEditorial ) {
    }

    public void leerRegistroAutor() {
        try{
            //JOP para obtener el autor a listar en la BD
            String autorJOP= JOptionPane.showInputDialog("Introduce el AUTOR a buscar");

            //Consulta SQL preparada para buscar el autor sin distinguir mayúsculas y minúsculas
            String textoSQL="select * from libros where lower(autor)=lower(?)";
            PreparedStatement ps=miConexion.prepareStatement(textoSQL);
            ps.setString(1, autorJOP);

            ResultSet rs = ps.executeQuery();

            //Impresión de los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                int idEditorial = rs.getInt("idEditorial");
                System.out.println("Libro:" + id + ", " + titulo + "," + autorJOP + ", " + idEditorial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Libro> leerRegistros(String autor ){
        String textoSQL="SELECT * FROM libros WHERE lower(autor)=lower(?)";
        ArrayList<Libro> arrayLibros = new ArrayList<>();
        try{
            PreparedStatement ps = miConexion.prepareStatement(textoSQL);
            ps.setString(1, autor);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){

                //Método 1: Añadiendo los parámetros directamente en la arrayList Libro
                arrayLibros.add(new Libro(
                        (rs.getInt("id")),
                        (rs.getString("titulo")),
                        (rs.getString("autor")),
                        (rs.getInt("idEditorial"))));

                //Método 2: Usando un arrayList auxiliar
                Libro aux=new Libro();
                aux.setId(rs.getInt("id"));
                aux.setTitulo(rs.getString("titulo"));
                aux.setAutor(rs.getString("autor"));
                aux.setIdEditorial(rs.getInt("idEditorial"));
                arrayLibros.add(aux);
                //Aprovechando el While, mostramos por pantalla cada objeto de la lista nada más añadirlo a ella
                System.out.println(aux.id + ": " + aux.titulo + ". Autor: " +  aux.autor + ". idEditorial: " + aux.idEditorial);
            }
            System.out.println();

            //Mostrar por pantalla el arrayList gracias al método toString creado más abajo
            System.out.println(arrayLibros);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    return arrayLibros;
    }

    //Método toString generado con @public String toString() (generate via Wizard)
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idEditorial=" + idEditorial +
                '}';
    }
}

