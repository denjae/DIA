package gui;

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
    }

    public void setUser(String name) {
        this.user = name;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Diabetic");
        frame.setContentPane(new Diabetic().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
