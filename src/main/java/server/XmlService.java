package server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by denjae on 10.12.13.
 */
public class XmlService {
    public void createFile(String user) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Root Element
            Document document = docBuilder.newDocument();
            Element rootElement = document.createElement("BZ");
            document.appendChild(rootElement);

            // Element Name
            Element name = document.createElement("Name");
            rootElement.appendChild(name);

            // Element Eintrag
            Element entry = document.createElement("BZeintrag");
            rootElement.appendChild(entry);

            // Element Blutzucker
            Element bz = document.createElement("Blutzucker");
            entry.appendChild(bz);

            // Element Uhrzeit
            Element time = document.createElement("Uhrzeit");
            entry.appendChild(time);

            // Element Datum
            Element nickname = document.createElement("Datum");
            entry.appendChild(nickname);

            // Inhalt in XML speichern
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("/Users/denjae/git/DIA/src/main/resources/" + user + ".xml"));
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}