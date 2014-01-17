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
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by denjae on 02.01.14.
 */
public class Doctor {
    private JPanel mainPanel;
    private JTable output;
    private JButton buttonLeft;
    private JButton buttonRight;
    private JTextArea note;
    private JButton send;
    private Document doc;
    private List<Element> list;
    private File xmlFile;
    private DiabeticService dia;
    private String user;


    public Doctor(String name) throws URISyntaxException {

        user = name;
        dia = new DiabeticService(user);
        xmlFile = dia.getXmlFile();
        //Fuehrt die notwendigen Methoden beim Absenden der eingetragenen Werte aus
        $$$setupUI$$$();

        //Oeffnet Mitteilung, dass das Absenden von Notizen im Prototypen nicht funktioniert
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Diese Funktion ist im Prototypen nicht implementiert..");
            }
        });
    }


    //Zeigt die aktuellen Werte in der JTable an bzw. ruft diese Werte auf
    private void createUIComponents() {
        String[][] values = fillJPanel();

        String[] title = new String[]{
                "Blutzucker", "Uhrzeit", "Datum"
        };

        output = new JTable(values, title);
        JTableHeader header = output.getTableHeader();
        output.setTableHeader(header);
    }




        //Variable j dient zum Vertauschen der Werte, somit werden die aktuellsten Werte oben statt unten ausgegeben
        private String[][] fillJPanel() {
            SAXBuilder b = new SAXBuilder();

            try {
                doc = b.build(xmlFile);
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Anzahl der Werte auf max 20 begrenzen
            Element root = (Element) doc.getRootElement();
            list = root.getChildren("BZeintrag");

            //Liest die Werte aus der XML in das Array ein
            String[][] values = new String[20][3];


            //Variable j dient zum Vertauschen der Werte, somit werden die aktuellsten Werte oben statt unten ausgegeben
            int size = list.size();
            if (size <= 20) {
                for (int i = (size - 1), j = 0; i >= 0; i--, j++) {
                    fillValueAtPosition(values, i, j);
                }
            } else {
                for (int i = size - 1, j = 0; j < 20; j++, i--) {
                    fillValueAtPosition(values, i, j);
                }
            }
            return values;
        }
    //Funktion zum Fuellen der Werte in das erzeugte Array
    private void fillValueAtPosition(String[][] values, int i, int j) {
        values[j][0] = list.get(i).getChildText("Blutzucker").toString();
        values[j][1] = list.get(i).getChildText("Uhrzeit").toString();
        values[j][2] = list.get(i).getChildText("Datum").toString();
    }
    public void run() {
        JFrame frame = new JFrame("Doctor");
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
        mainPanel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), 80, 60));
        mainPanel.setBorder(BorderFactory.createTitledBorder(null, "DIAbetesDIAry - Ihre Verbindung zum Arzt", TitledBorder.CENTER, TitledBorder.TOP));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buttonRight = new JButton();
        buttonRight.setText("Vor");
        buttonRight.setToolTipText("");
        mainPanel.add(buttonRight, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainPanel.add(output, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        buttonLeft = new JButton();
        buttonLeft.setText("Zurueck");
        mainPanel.add(buttonLeft, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        note = new JTextArea();
        note.setText("Notiz eingeben...\n");
        mainPanel.add(note, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        send = new JButton();
        send.setText("Notiz senden");
        mainPanel.add(send, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
