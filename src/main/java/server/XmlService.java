package server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by denjae on 10.12.13.
 */
public class XmlService {
     //TODO: Überlegen wie Name des Files erzeugt wird
    //Erzeugt und Speichert ein neues XML-File
    public void createFile() throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document document = docBuilder.newDocument();
        Element rootElement = document.createElement("BZ");

        Element name = document.createElement("Name");
        rootElement.appendChild(name);

        Element entry = document.createElement("BZeintrag");
        rootElement.appendChild(entry);

        Element bz = document.createElement("Blutzucker");
        entry.appendChild(bz);

        Element time = document.createElement("Uhrzeit");
        entry.appendChild(time);

        Element date = document.createElement("Datum");
        entry.appendChild(date);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("/Users/denjae/git/DIA/src/main/resources/test.xml"));

        transformer.transform(source, result);
        System.out.println("File saved!");
    }
}
