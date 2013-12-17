package server;

import diaPublisher.DiabeticService;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by denjae on 10.12.13.
 */
public class XmlService {
    public void createFile(String user) {
        try {
            Document document = new Document();
            Element bz = new Element("BZ");
            document.setRootElement(bz);

            Element bzEntry = new Element("BZeintrag");
            bzEntry.addContent(new Element("Blutzucker"));
            bzEntry.addContent(new Element("Uhrzeit"));
            bzEntry.addContent(new Element("Datum"));
            document.getRootElement().addContent(bzEntry);

// new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter("/Users/denjae/git/DIA/src/main/resources/" + user + ".xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //Berechnet den Durchschnitt der letzten 20 eingetragenen BZ-Werte
    public int getAverage(String user) throws JDOMException, IOException {
        //Definition des Tolleranzbereiches
        int maxBz = 160;
        int minBz = 70;
        int average = 0;
        DiabeticService service = new DiabeticService();
        service.getBZ(user);

        //Erstellt XML mit den letzten 20 BZ-Werten
        File xmlFile = new File("/Users/denjae/git/DIA/src/main/resources/returnBz.xml");
        SAXBuilder builder = new SAXBuilder();
        Document document = null;
        try {
            document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("BZeintrag");

            //Durchschnittsberechnung
            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                average += Integer.parseInt(node.getChildText("Blutzucker"));
            }

            average = average / list.size();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(average > maxBz || average < minBz)
            JOptionPane.showMessageDialog(null, "Blutzucker von "+user+ " ausserhalb der Tolleranz - Kontrolle notwendig");
        return average;
    }
}