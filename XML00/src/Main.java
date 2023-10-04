import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Alumno> lista = new ArrayList<>();
        //Forma 1: Crear el Alumno y añadirlo a la lista
        Alumno alumno = new Alumno("Paco", 4);
        lista.add(alumno);

        //Forma 2: Añadir a la lista metiendo los datos a la vez
        lista.add(new Alumno("Pepa",3));

        //Llamada para ejecutar el método escribir lista
        escribirXML(lista);
        //Llamada para ejecutar el método leer lista
        leerXML();
    }
    public static void escribirXML(ArrayList<Alumno> alumnos) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null,"xml",null);

            //Creamos la etiqueta raiz
            Element raiz = documento.createElement ("Alumnos");

            //Añadimos el elemento raiz en el documento
            documento.getDocumentElement().appendChild(raiz);
            Element nodoAlumno=null, nodoDatos=null;
            Text texto=null;
            for(Alumno alumno: alumnos){
                nodoAlumno=documento.createElement("alumno");
                raiz.appendChild(nodoAlumno);

                nodoDatos=documento.createElement("nombre");
                nodoAlumno.appendChild(nodoDatos);

                texto=documento.createTextNode(alumno.getNombre());
                nodoDatos.appendChild(texto);

                nodoDatos=documento.createElement("nota");
                nodoAlumno.appendChild(nodoDatos);

                texto=documento.createTextNode(String.valueOf(alumno.getNota()));
                nodoDatos.appendChild(texto);

            }
            //Tenemos una fuente para el archivo
            Source source=new DOMSource(documento);
            //Donde se va a guardar
            Result result=new StreamResult(new File("tutoria.xml"));
            //Prepara el archivo para ser guardado
            Transformer transformer= TransformerFactory.newInstance().newTransformer();
            //Guarda el objeto de memoria al archivo XML
            transformer.transform(source,result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leerXML(){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Document documento=null;
    try{
        //Creamos el documento en memoria
        DocumentBuilder builder=factory.newDocumentBuilder();
        documento=builder.parse(new File("tutoria.xml"));

        NodeList alumnos=documento.getElementsByTagName("alumno");
        //Para mostrar por pantalla
        for(int i=0; i<alumnos.getLength();i++){
            Node alumno=alumnos.item(i);
            //Lo convertimos en una clase elemento
            Element elemento=(Element) alumno;
            //Imprimimos los nombres
            System.out.println(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
            //Imprimimos las notas
            System.out.println(elemento.getElementsByTagName("nota").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println("----------------");
        }

    }
    catch (Exception e){
        e.printStackTrace();
    }
    }
}