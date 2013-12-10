package diaSubscriber;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.pubsub.PubSubManager;

/**
 * Created by denjae on 10.12.13.
 */
public class MedicService {
    private XMPPConnection connection = new XMPPConnection("localhost");
    private PubSubManager mgr = new PubSubManager(connection);
    private ServiceDiscoveryManager sdMgr;
}
