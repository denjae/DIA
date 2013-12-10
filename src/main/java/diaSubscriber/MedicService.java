package diaSubscriber;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Created by denjae on 10.12.13.
 */
public class MedicService {

    public void createUser(String user, String password){
        XMPPConnection connection = new XMPPConnection("localhost");
        AccountManager accountManager = new AccountManager(connection);


        try {
            accountManager.createAccount(user, password);
        } catch (XMPPException e) {
            e.printStackTrace();
        }

    }
}
