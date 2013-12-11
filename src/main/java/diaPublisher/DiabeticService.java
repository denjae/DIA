package diaPublisher;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by denjae on 09.12.13.
 */
public class DiabeticService {

    public DiabeticService() {
        File bzPatient = null;
    }

    // Erstellt XML mit den maximal 20 zuletzt eingetragenen Werten und gibt diese zurück
    public File getBZ(String user) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        try {
            docBuilder.parse("/Users/denjae/git/DIA/src/main/resources/"+ user +".xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = docBuilder.newDocument();
        //Ermitteln von max 20 Knoten
        NodeList list = document.getElementsByTagName("BZeintrag");
        int length = 0;
            if(list.getLength()<20) {
        length = list.getLength();
            }
            else {
                length= 20;
            }





        File currentBZ = null;



        return currentBZ;
    }

    public void setBZ(String name) {

    }

}
