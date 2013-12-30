package server;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.pubsub.*;
import org.xmpp.packet.Roster;

import java.util.Collection;

public class PubSub {
    private XMPPConnection connection = new XMPPConnection("localhost");
    private PubSubManager mgr = new PubSubManager(connection);
    private ServiceDiscoveryManager sdMgr;

    public PubSub(String user, String pass) throws XMPPException {
        try {
            connection.connect();
            connection.login(user, pass);
        } catch (XMPPException e) {
            System.err.println("Login failed!");
            e.printStackTrace();
            System.exit(1);
        }}

    public XMPPConnection getConnection() {
        return this.connection;
    }

    public void createUser(String user, String password) {
        AccountManager accountManager = new AccountManager(connection);
        XmlService xmlService = new XmlService();
        xmlService.createFile(user);
        try {
            accountManager.createAccount(user, password);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    //Verbindung trennen
    public void disconnect() {
        connection.disconnect();
    }

    //Abonniert Topic fuer angemeldeten Benutzer
    public void subscribe(String user) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(user);
        node.addItemEventListener(new ItemEventCoordinator<RosterPacket.Item>());
        node.subscribe(connection.getUser());

    }


    //Gibt die publizierten Nachrichten des ausgewaehlten Knoten zurueck
    public Collection getMessagesFromNode(String user) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(user);
        Collection<? extends Item> items = node.getItems();
        return items;
    }

    //Uebertraegt einen neuen BZ-Wert
    public void sendBZ(int bz, String name, String time, String date) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(name);
        SimplePayload payload = new SimplePayload("Liveticker", null, " <BZeintrag><Blutzucker>"+ bz+ "</Blutzucker><Uhrzeit>" +time+ "</Uhrzeit><Datum>" +date+ "</Datum></BZeintrag>");
        PayloadItem item = new PayloadItem<SimplePayload>(name, payload);
        node.publish(item);
        System.out.println("Eintrag erfolgreich hinzugefuegt");
    }


}