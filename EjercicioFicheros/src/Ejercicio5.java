import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Ejercicio5 {
    public static void main(String[] args) {
        //Guardar el contenido de un documento en otro
        try {
            BufferedReader br = new BufferedReader(new FileReader("diario.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("diario-backup.txt",false));
            String linea = br.readLine();
            while (linea != null) {
                pw.println(linea);
                linea = br.readLine();
            }
            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}