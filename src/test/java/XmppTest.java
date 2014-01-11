import org.jivesoftware.smack.XMPPException;
import server.Xmpp;

/**
 * Created by denjae on 30.12.13.
 */
public class XmppTest {
    public static void main(String[] args) throws XMPPException {
        Xmpp xmpp = new Xmpp("denjae");
        xmpp.login("user1", "test");
        //xmpp.createNode("denjae");
        xmpp.sendBZ("denjae", 99, "13:13", "01.02.2003");
    }
}
