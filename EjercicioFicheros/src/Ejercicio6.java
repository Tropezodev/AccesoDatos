import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio6 {
    public static void main(String[] args) {
        //Mostrar por pantalla todas las líneas del documento de texto
        try {
            BufferedReader br1 = new BufferedReader(new FileReader("diario.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("diario-backup.txt"));
            String linea1 = br1.readLine();
            String linea2 = br2.readLine();
            boolean iguales=true;
            while (linea1 !=null && linea2 !=null && iguales) {
                if(!linea1.equals(linea2)) {
                    iguales = false;
                    System.out.println("Son diferentes.");
                }
                linea1 = br1.readLine();
                linea2  = br2.readLine();
                if(linea1==null && linea2!=null || (linea1!=null && linea2==null)){
                    iguales=false;
                    System.out.println("Son iguales.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}