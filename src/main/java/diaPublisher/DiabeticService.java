package diaPublisher;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by denjae on 09.12.13.
 */
public class DiabeticService {


    // Erstellt XML mit den maximal 20 zuletzt eingetragenen Werten und gibt diese zurück
    /*public void getBZ(String user) {
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
            if (list.getLength() < 20) {
                length = list.getLength();
            } else {
                length = 20;
            }

            for (int i = 0; i < length; i++) {
                // Element Eintrag
                // Element entry = lastValues.createElement("BZeintrag");
                //rootElement.appendChild(entry);
                System.out.println();
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

    }*/
     //TODO: Letzte Werte von unten auslesen, nicht von oben! ANPASSEN!
    public void getBZ(String user) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Users/denjae/git/DIA/src/main/resources/" + user + ".xml");

        try {
            Document document = (Document) builder.build(xmlFile);
            Document lastEntries = new Document();
            Element rootNode = document.getRootElement();
            Element BZ = new Element("BZ");
            lastEntries.setRootElement(BZ);
            List list = rootNode.getChildren("BZeintrag");

            //Begrenzt Ergebnis auf die max 20 letzten Werte
            int length = 0;
            if (list.size() < 20) {
                length = list.size();
            } else {
                length = 20;
            }


            for (int i = 0; i < length; i++) {
                Element bzEntry = new Element("BZeintrag");
                Element node = (Element) list.get(i);
                bzEntry.addContent(new Element("Blutzucker").setText(node.getChildText("Blutzucker")));
                bzEntry.addContent(new Element("Uhrzeit").setText(node.getChildText("Uhrzeit")));
                bzEntry.addContent(new Element("Datum").setText(node.getChildText("Datum")));
                lastEntries.getRootElement().addContent(bzEntry);
            }

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(lastEntries, new FileWriter("/Users/denjae/git/DIA/src/main/resources/returnBz.xml"));

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }


    public void setBZ(String user,Integer bz, String date, String time) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Users/denjae/git/DIA/src/main/resources/" + user + ".xml");
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootElement = document.getRootElement();
            Element bzEntry = new Element("BZeintrag");
            Element actbz = bzEntry.addContent(new Element("Blutzucker").setText(bz.toString()));
            Element acttime = bzEntry.addContent(new Element("Uhrzeit").setText(time));
            Element actdate = bzEntry.addContent(new Element("Datum").setText(date));
            rootElement.addContent(bzEntry);

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter("/Users/denjae/git/DIA/src/main/resources/" + user + ".xml"));


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
