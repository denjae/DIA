import diaPublisher.DiabeticService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Created by denjae on 12.12.13.
 */
public class DiabeticServiceTest {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DiabeticService diabeticService = new DiabeticService();
        diabeticService.getBZ("Denjae");
    }


}
