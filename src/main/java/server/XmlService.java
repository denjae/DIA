package server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by denjae on 10.12.13.
 */
public class XmlService {

    //Erzeugt und Speichert ein neues XML-File
    public void createFile() throws Exception{
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


    Document document = docBuilder.newDocument();
    Element rootElement = document.createElement("BZ");
}
}
