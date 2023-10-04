import org.w3c.dom.DOMImplementation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args){
                ArrayList<Alumnos> lista = new ArrayList<>();

                ArrayList<Alumnos> listaAlumnos) {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        Document documento = null;
                        try {
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                DOMImplementation dom = builder.getDOMImplementation();
                                documento = dom.createDocument(null,  "xml", null);

                                Element raiz = documento.createElement("Productos");
                                documento.getDocumentElement().appendChild(raiz);
                                Element nodoProducto = null, nodoDatos = null;
                                Text texto = null;
                                for (Producto producto : listaProductos) {
                                        nodoProducto = documento.createElement("Producto");
                                        raiz.appendChild(nodoProducto);
                                        nodoDatos = documento.createElement("nombre");
                                        nodoProducto.appendChild(nodoDatos);
                                        nodoDatos.setAttribute("nacionalidad", "española");
                                        texto = documento.createTextNode(producto.getNombre());
                                        nodoDatos.appendChild(texto);
                                        nodoDatos = documento.createElement("precio");
                                        nodoProducto.appendChild(nodoDatos);
                                        texto = documento.createTextNode(String.valueOf(producto.getPrecio()));
                                        nodoDatos.appendChild(texto);
                                }
                                Source source = new DOMSource(documento);
                                Result resultado = new StreamResult(new File("archivo.xml"));
                                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                                transformer.transform(source, resultado);
                        } catch (ParserConfigurationException pce) {
                                pce.printStackTrace();
                        } catch (TransformerConfigurationException tce) {
                                tce.printStackTrace();
                        } catch (TransformerException te) {
                                te.printStackTrace();
                        }
                }

        }

}