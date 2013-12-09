package xmpp;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.*;
import org.xmpp.packet.Roster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PubSub {
    private XMPPConnection connection = new XMPPConnection("localhost");
    private PubSubManager mgr = new PubSubManager(connection);
    private ServiceDiscoveryManager sdMgr;


    public PubSub(String user, String pass) throws XMPPException {
        connection.connect();
        connection.login(user, pass);
    }
    //TODO delete erstellen

    //Verbindung trennen
    public void disconnect() {
        connection.disconnect();
    }

    //Abonniert Topic fuer angemeldeten Benutzer
    public void subscribe(String team) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(team);
        node.addItemEventListener(new ItemEventCoordinator<RosterPacket.Item>());
        node.subscribe(connection.getUser());

    }

    //Meldet angemeldeten Benutzer von uebergebenem Knoten ab
    public void unsubscribe(String team) throws XMPPException {

        LeafNode node = (LeafNode) mgr.getNode(team);
        node.addItemEventListener(new ItemEventCoordinator<Roster.Item>());
        node.unsubscribe(connection.getUser());
    }

    //Gibt alle Abonnenten aus
    public void getAllSubscriptions() throws XMPPException {
        List<Subscription> subscriptions = mgr.getSubscriptions();
        System.out.println("Abonennten aller Nodes:");
        System.out.println(subscriptions.toString());
        System.out.println("");

    }

    //Gibt alle Topics (Nodes) zurueck
    public List<String> discover() throws XMPPException {
        this.sdMgr = ServiceDiscoveryManager.getInstanceFor(connection);
        List<String> list = new ArrayList<String>();
        for (Iterator<DiscoverItems.Item> iterator = sdMgr.discoverItems("pubsub." + "localhost").getItems(); iterator.hasNext(); ) {
            DiscoverItems.Item item = iterator.next();
            list.add(item.getNode());
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return list;
    }

    //Gibt die publizierten Nachrichten des ausgewaehlten Knoten zurueck
    public Collection getMessagesFromNode(String team) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(team);
        Collection<? extends RosterPacket.Item> items = node.getItems();

       /* System.out.println("Alle Messages von " + team + ":");
        for (int i = 0; i < node.getItems().size(); i++) {
            System.out.println(node.getItems(node.getSubscriptions().get(0).getId()).get(i));
        }
        System.out.println("");*/

        return items;
    }

    //TODO Pub-Methoden ueberschreiben vorherige Nodes, ueberarbeiten!
    //Publiziert Kommentar
    public void pubComment(String team, int min, String comment) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(team);
        SimplePayload payload = new SimplePayload("Liveticker", null, "<Spiel><Kommentare><Kommentar><Minute>" + min + "</Minute><Text>" + comment + "</Text></Kommentar></Kommentare></Spiel>");
        PayloadItem item = new PayloadItem<SimplePayload>(team, payload);
        node.publish(item);
        System.out.println("Kommentar erzeugt.");
    }

    //Publiziert ein Tor und korrigiert manuell das aktuelle Ergebnis
    public void pubGoal(String team, String schuetze, int min, String ergebnis) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(team);
        SimplePayload payload = new SimplePayload("Liveticker", null, "<Endergebnis><Ergebnis>" + ergebnis + "</Ergebnis><Tore><Tor><Torschuetze>" + schuetze + "</Torschuetze><Mannschaft_Schuetze>" + team + "</Mannschaft_Schuetze><Minute>" + min + "</Minute></Tor></Tore></Endergebnis>");
        PayloadItem item = new PayloadItem<SimplePayload>(team, payload);
        node.publish(item);
    }

    //Entfernt letzte Publikation
    public void deleteMessage(String Id, String team) throws XMPPException {
        LeafNode node = (LeafNode) mgr.getNode(team);
        node.deleteItem(Id);
        System.out.println("Item entfernt");
        System.out.println("");

    }
}