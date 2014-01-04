package gui;

import diaPublisher.DiabeticService;
import org.jivesoftware.smack.XMPPException;
import server.Xmpp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private String user;
    private int bz = 0;
    private String timeInput;
    private String dateInput;
    private Xmpp xmpp;
    private DiabeticService dia;

    public Diabetic() {
        //Liest eingegebenen Blutzuckerwert aus und speichert diesen als Integer-Wert
        bloodSugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bz = Integer.parseInt(bloodSugar.getText());
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Bitte gueltigen Blutzucker-Wert eingeben!");
                }
            }
        });

        //Liest eingegebene Uhrzeit aus und speichert diesen als String
        time.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timeInput = time.getText();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Bitte gueltige Uhrzeit eingeben!");
                }
            }
        });

        //Liest eingegebenes Datum aus und speichert dieses als String
        date.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dateInput = date.getText();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Bitte gueltiges Datum eingeben!");
                }

            }
        });
        //Fuehrt die notwendigen Methoden beim Absenden der eingetragenen Werte aus
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dia.setBZ(user, bz, timeInput, dateInput);
                try {
                    xmpp.sendBZ(bz,user,timeInput,dateInput);
                } catch (XMPPException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    //Setzt den Benutzernamen, der im Loginfenster eingegeben wurde
    public void setUser(String name) {
        this.user = name;
    }

    public  void run() {
        JFrame frame = new JFrame("Diabetic");
        frame.setContentPane(new Diabetic().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
