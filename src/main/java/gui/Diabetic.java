package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import diaPublisher.DiabeticService;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by denjae on 02.01.14.
 */
public class Diabetic {
    private JPanel values;
    private JPanel mainPanel;
    private JTextField bloodSugar;
    private JTextField time;
    private JTextField date;
    private JTable output;
    private JButton send;
    private JButton buttonLeft;
    private JButton buttonRight;
    private int bz = 0;
    private String timeInput;
    private String dateInput;
    private Document doc;
    private List<Element> list;
    private int length;
    private DiabeticService diabetic;


    public Diabetic(final String name) {
       //Ausfuehren zum Akualisieren der Datei return.xml
        try {
            diabetic.getBZ(name);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String user = name;
        //Fuehrt die notwendigen Methoden beim Absenden der eingetragenen Werte aus
        $$$setupUI$$$();
        send.addActionListener(new ActionListener() {

            private DiabeticService dia = new DiabeticService();



            //ActionListener, der bei Druck auf Senden-Button aktiv wird
            @Override
            public void actionPerformed(ActionEvent e) {
                //Auslesen des BZ-Wertes
                try {
                    bz = Integer.parseInt(bloodSugar.getText());
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Bitte gueltigen Blutzucker-Wert eingeben!");
                }
                //Auslesen der eingetragenen Uhrzeit
                timeInput = time.getText();
                //Auslesen des eingetragenen Datums
                dateInput = date.getText();
                //Speichert eingegebene Werte in user.xml, try/catch fuer Statusanzeige

                try {
                    dia.setBZ(user, bz, timeInput, dateInput);
                    JOptionPane.showMessageDialog(null, "Blutzucker erfolgreich eingetragen!");
                } catch (Exception ecx) {
                    JOptionPane.showMessageDialog(null, "Fehler bei der Eintragung");
                }

                //Senden an XMPP-Server - Im Prototypen nicht notwendig
              /*  try {
                    mgr.getNode(user);
                } catch (Exception exc) {
                    try {
                        mgr.createNode(user);
                    } catch (XMPPException e1) {
                        JOptionPane.showMessageDialog(null, "Fehler beim Senden der Werte!");
                    }
                }
                try {
                    node = (LeafNode) mgr.getNode(user);
                } catch (XMPPException e1) {
                    e1.printStackTrace();
                }

                try {
                    xmpp.sendBZ(user, bz, timeInput, dateInput);
                    JOptionPane.showMessageDialog(null, "Blutzucker erfolgreich eingetragen!");
                } catch (XMPPException e1) {
                    e1.printStackTrace();
                }*/

            }
        });
    }


    //Zeigt die aktuellen Werte in der JTable an bzw. ruft diese Werte auf
    private void createUIComponents() {

        String[][] values = setOutput();


        String[] title = new String[]{
                "Blutzucker", "Uhrzeit", "Datum"
        };
        output = new JTable(values, title);
        JTableHeader header = output.getTableHeader();
        output.setTableHeader(header);
    }

    private String[][] setOutput() {
        SAXBuilder b = new SAXBuilder();
        try {
            doc = b.build(new File("./src/main/resources//returnBz.xml"));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Anzahl der Werte auf max 20 begrenzen
        Element root = (Element) doc.getRootElement();
        list = root.getChildren("BZeintrag");

        if (list.size() <= 20)
            length = list.size();
        else
            length = 20;

        //Liest die Werte aus der XML in das Array ein
        String[][] values = new String[20][3];

        for (int i = 0; i < list.size(); i++) {
            values[i][0] = list.get(i).getChildText("Blutzucker").toString();
            values[i][1] = list.get(i).getChildText("Uhrzeit").toString();
            values[i][2] = list.get(i).getChildText("Datum").toString();
        }
        return values;
    }

    public void run() {
        JFrame frame = new JFrame("Diabetic");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), 80, 60));
        mainPanel.setBorder(BorderFactory.createTitledBorder(null, "DIAbetesDIAry - Ihre Verbindung zum Arzt", TitledBorder.CENTER, TitledBorder.TOP));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        values = new JPanel();
        values.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), 1, 1));
        mainPanel.add(values, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, true));
        bloodSugar = new JTextField();
        bloodSugar.setHorizontalAlignment(2);
        bloodSugar.setText("Blutzucker");
        values.add(bloodSugar, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 10), null, 0, false));
        time = new JTextField();
        time.setText("hh:mm");
        values.add(time, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        date = new JTextField();
        date.setText("tt.mm.jjjj");
        values.add(date, new GridConstraints(2, 0, 2, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("mg/dl");
        values.add(label1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Uhr");
        values.add(label2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        send = new JButton();
        send.setText("Eintragen");
        values.add(send, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonRight = new JButton();
        buttonRight.setText("Vor");
        buttonRight.setToolTipText("");
        mainPanel.add(buttonRight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainPanel.add(output, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        buttonLeft = new JButton();
        buttonLeft.setText("Zurueck");
        mainPanel.add(buttonLeft, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
