package diaPublisher;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by denjae on 09.12.13.
 */
public class DiabeticService {


    // Erstellt XML mit den maximal 20 zuletzt eingetragenen Werten und gibt diese zurück
  /*  public void getBZ(String user)  {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();

           Document document = docBuilder.parse("/Users/denjae/git/DIA/src/main/resources/" + user + ".xml");
           Document lastValues = docBuilder.newDocument();

            // Root Element
            Element rootElement = lastValues.createElement("BZ");
            lastValues.appendChild(rootElement);

            //Element Name
            Element name = lastValues.createElement("Name");
            name.appendChild(lastValues.createTextNode(user));
            rootElement.appendChild(name);


        //Neues XML-Dokument mit den max. 20 letzten Werten
            //Ermitteln von max 20 Knoten
            NodeList list = document.getElementsByTagName("BZeintrag");
            int length = 0;
            if(list.getLength()<20) {
                length = list.getLength();
            }
            else {
                length= 20;
            }

            for (int i = 0; i < length ; i++) {
                // Element Eintrag
              // Element entry = lastValues.createElement("BZeintrag");
              //rootElement.appendChild(entry);
              Node node = lastValues.importNode(document.getDocumentElement(),true);
              lastValues.getDocumentElement().appendChild(node);
                System.out.println(1);
             }



        // Inhalt in XML speichern
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(lastValues);
        StreamResult result = new StreamResult(new File("/Users/denjae/git/DIA/src/main/resources/returnBz.xml"));
        transformer.transform(source, result);

        System.out.println("File saved!");
        transformer.transform(source, result);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    */

    public void getBZ(String user){


    }


    public void setBZ(String name) {
    }

}
