import org.jivesoftware.smack.XMPPException;
import server.PubSub;

/**
 * Created by denjae on 30.12.13.
 */
public class XmppTest {
    public static void main(String[] args) throws XMPPException {
        PubSub pubSub = new PubSub("user1", "test");
        pubSub.sendBZ(99,"denjae", "13:13", "01.02.2003");
    }
}
