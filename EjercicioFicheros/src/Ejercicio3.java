import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Ejercicio3 {
    public static void main(String[] args) {
        //Te muestra un mensaje en pantalla antes de capturar por teclado
        String linea = JOptionPane.showInputDialog("Escribe algo");

        try {
            PrintWriter pw=new PrintWriter(new FileWriter("diario.txt",true));
            pw.println(LocalDateTime.now() + " - " + linea);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
