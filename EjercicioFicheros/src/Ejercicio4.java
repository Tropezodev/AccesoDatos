import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio4 {
    public static void main(String[] args) {
        //Mostrar por pantalla todas las líneas del documento de texto
        try {
            BufferedReader br = new BufferedReader(new FileReader("diario.txt"));
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
