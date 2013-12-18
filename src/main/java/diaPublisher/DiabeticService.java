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

    public void getBZ(String user) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("./src/main/resources/" + user + ".xml");

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
                    Element bzEntry = new Element("BZeintrag");
                    Element node = (Element) list.get(i);
                    bzEntry.addContent(new Element("Blutzucker").setText(node.getChildText("Blutzucker")));
                    bzEntry.addContent(new Element("Uhrzeit").setText(node.getChildText("Uhrzeit")));
                    bzEntry.addContent(new Element("Datum").setText(node.getChildText("Datum")));
                    lastEntries.getRootElement().addContent(bzEntry);
                }
            } else {
                for (int i = length - 20; i < length; i++) {
                    Element bzEntry = new Element("BZeintrag");
                    Element node = (Element) list.get(i);
                    bzEntry.addContent(new Element("Blutzucker").setText(node.getChildText("Blutzucker")));
                    bzEntry.addContent(new Element("Uhrzeit").setText(node.getChildText("Uhrzeit")));
                    bzEntry.addContent(new Element("Datum").setText(node.getChildText("Datum")));
                    lastEntries.getRootElement().addContent(bzEntry);
                }
            }


            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(lastEntries, new FileWriter("./src/main/resources//returnBz.xml"));

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }


    public void setBZ(String user, Integer bz, String date, String time) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("./src/main/resources/" + user + ".xml");
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
            xmlOutput.output(document, new FileWriter("./src/main/resources/" + user + ".xml"));


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
