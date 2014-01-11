package server;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smackx.pubsub.*;

import java.util.Collection;

public class Xmpp {
    private ConnectionConfiguration config;
    private XMPPConnection connection;
    private AccountManager accountManager;
    private XmlService xmlService;
    private LeafNode node;
    private String user;


    public Xmpp(String user) {
        config = new ConnectionConfiguration("127.0.0.1", 5222, "localhost");
        connection = new XMPPConnection(config);
        accountManager = new AccountManager(connection);
        xmlService = new XmlService();
        mgr = new PubSubManager(connection);
        try {
            node = (LeafNode) mgr.getNode(user);
        } catch (XMPPException e) {
            e.printStackTrace();
        }

    }

    public PubSubManager getMgr() {
        return mgr;
    }

    private PubSubManager mgr;

    public void login(String user, String pass) throws XMPPException {
        try {
            connection.connect();
            connection.login(user, pass);

        } catch (XMPPException e) {
            System.err.println("Login failed!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public XMPPConnection getConnection() {
        return this.connection;
    }

    public void createUser(String user, String password) {


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

        node.addItemEventListener(new ItemEventCoordinator<RosterPacket.Item>());
        node.subscribe(connection.getUser());

    }

    public void createNode(String user) {
        try {
            LeafNode node = mgr.createNode(user);
            ConfigureForm form1 = new ConfigureForm(FormType.submit);
            form1.setAccessModel(AccessModel.open);
            form1.setDeliverPayloads(true);
            form1.setNotifyRetract(true);
            form1.setPersistentItems(true);
            form1.setPublishModel(PublishModel.open);
            node.sendConfigurationForm(form1);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }


    //Gibt die publizierten Nachrichten des ausgewaehlten Knoten zurueck
    public Collection getMessagesFromNode(String user) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(user);
        Collection<? extends Item> items = node.getItems();
        return items;
    }

    //Uebertraegt einen neuen BZ-Wert
    public void sendBZ(String name, int bz, String time, String date) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(name);
        SimplePayload payload = new SimplePayload("BZ", null, " <BZeintrag><Blutzucker>" + bz + "</Blutzucker><Uhrzeit>" + time + "</Uhrzeit><Datum>" + date + "</Datum></BZeintrag>");
        PayloadItem item = new PayloadItem<SimplePayload>(name, payload);
        node.publish(item);
        System.out.println("Eintrag erfolgreich hinzugefuegt");
    }


}