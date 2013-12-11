import server.XmlService;

/**
 * Created by denjae on 11.12.13.
 */
public class XmlServiceTest {

    public static void main(String[] args) {

    XmlService test = new XmlService();
        try {
            test.createFile("Denjae");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
