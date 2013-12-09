package xmpp;

import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: djga
 * Date: 31.05.13
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class ItemEventCoordinator<T> implements ItemEventListener {
    @Override
    public void handlePublishedItems(ItemPublishEvent items)
    {
        //System.out.println("Item count: " + items.getItems().size());
        //System.out.println(items);
        JOptionPane.showMessageDialog(null, "neue Nachricht eingetroffen");

    }
}