package diaPublisher;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

/**
 * Created by denjae on 09.12.13.
 */
public class DiabeticService {

    public DiabeticService(String name) {
        File bzPatient = null;
    }

    // Erstellt XML mit den maximal 20 zuletzt eingetragenen Werten und gibt diese zurück
    public File getBZ(String Name) throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


        Document document = docBuilder.newDocument();
        Element rootElement = document.createElement("BZ");

        File currentBZ = null;

        return currentBZ;
    }

    public void setBZ(String name) {

    }

}
