package diaPublisher;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import server.XmlService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by denjae on 09.12.13.
 */
public class DiabeticService {
    private String user;
    private File xmlFile;
    private XmlService xmlService;

    public DiabeticService(String name) {

        this.user = name;
        //Entweder bestehende XML-File auswaehlen oder neue erstellen
        try {
            xmlFile = new File("./data/Denjae.xml");
        }
        catch (Exception exp){
            xmlService = new XmlService();
            xmlService.createFile(user);
            xmlFile = new File("./data/Denjae.xml");
        }

    }

    public File getXmlFile(){
        return xmlFile;
    }

    private void createElement(Document lastEntries, List list, int i) {
        Element bzEntry = new Element("BZeintrag");
        Element node = (Element) list.get(i);
        bzEntry.addContent(new Element("Blutzucker").setText(node.getChildText("Blutzucker")));
        bzEntry.addContent(new Element("Uhrzeit").setText(node.getChildText("Uhrzeit")));
        bzEntry.addContent(new Element("Datum").setText(node.getChildText("Datum")));
        lastEntries.getRootElement().addContent(bzEntry);
    }

    //Schreibt in die Datei returnBz.xml die maximal 20 letzten Werte
    public void getBZ(String user) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();


        try {
            Document document = (Document) builder.build(xmlFile);
            Document lastEntries = new Document();
            Element rootNode = document.getRootElement();
            Element BZ = new Element("BZ");
            lastEntries.setRootElement(BZ);
            List list = rootNode.getChildren("BZeintrag");

            //Begrenzt Ergebnis auf die max 20 letzten Werte
            int length = list.size();
            if (length <= 20) {
                for (int i = 0; i < length; i++) {
                    createElement(lastEntries, list, i);
                }
            } else {
                for (int i = length - 20; i < length; i++) {
                    createElement(lastEntries, list, i);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();

            // Ausgabe formatieren
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(lastEntries, new FileWriter("./data/returnBz.xml"));

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }


    public void setBZ(String user, Integer bz, String date, String time) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("./data/Denjae.xml");
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootElement = document.getRootElement();
            Element bzEntry = new Element("BZeintrag");
            Element actbz = bzEntry.addContent(new Element("Blutzucker").setText(bz.toString()));
            Element acttime = bzEntry.addContent(new Element("Uhrzeit").setText(time));
            Element actdate = bzEntry.addContent(new Element("Datum").setText(date));
            rootElement.addContent(bzEntry);

            XMLOutputter xmlOutput = new XMLOutputter();

            // Formatierung verbessern
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter("./data/Denjae.xml"));


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}