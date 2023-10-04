import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

public class Ejercicio2  {
    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("fecha.txt"));
            String fecha=br.readLine();
            System.out.println("La última fecha registrada es "+fecha + " y la actual es "+ LocalDate.now());

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
