import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.now();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("fecha.txt"));
            pw.println(fecha.toString());
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}