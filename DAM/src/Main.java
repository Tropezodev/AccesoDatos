import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //Escritura de Archivo de Propiedades
        Properties config = new Properties();
        String miuser = "Cristian", mideporte = "Furgoh", miserie = "Ted Lasso";
        config.setProperty("USUARIO", miuser);
        config.setProperty("DEPORTE",mideporte);
        config.setProperty("SERIE", miserie);
        try {
            config.store(new FileOutputStream("Config.props"), "Fichero de Configuracion");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //Lectura de Archivo de Propiedades
        Properties configuracion = new Properties();
        try {
            configuracion.load(new
        FileInputStream("Config.props"));
            miuser = configuracion.getProperty("USUARIO");
            mideporte = configuracion.getProperty("DEPORTE");
            miserie = configuracion.getProperty("SERIE");
            System.out.println("Datos de Configuración:");
            System.out.println("Usuario:" +miuser +"\tDEPORTE:" +mideporte + "\tSERIE:" +miserie);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}