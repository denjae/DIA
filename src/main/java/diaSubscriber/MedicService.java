package diaSubscriber;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import server.XmlService;

/**
 * Created by denjae on 10.12.13.
 */
public class MedicService {

    public void createUser(String username, String password){
        XMPPConnection connection = new XMPPConnection("localhost");
        AccountManager accountManager = new AccountManager(connection);
        try {
            accountManager.createAccount(username, password);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        XmlService xmlService = new XmlService();
        xmlService.createFile(username);

    }
}
