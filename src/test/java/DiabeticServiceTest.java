import diaPublisher.DiabeticService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by denjae on 12.12.13.
 */
public class DiabeticServiceTest {
    public static void main(String[] args) throws Exception {
        DiabeticService diabeticService = new DiabeticService();
        try {
            diabeticService.getBZ("Denjae");
        } catch (Exception e) {
            e.printStackTrace();
        }
       // diabeticService.setBZ("Denjae",122, "12.12.2012", "12:12");
    }



}
