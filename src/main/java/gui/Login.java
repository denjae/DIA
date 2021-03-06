package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

/**
 * Created by denjae on 06.01.14.
 */
public class Login {
    private JPanel mainPanel;
    private JButton login;
    private JTextField username;
    private JComboBox choiceUser;
    private String name;
    private int choice;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Login() {
        $$$setupUI$$$();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();


            }
        });
        //Somit kann der eingegebene Benutzername auch via Druck auf Enter-Taste abgesendet werden
        username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
    }

    private void performLogin() {
        name = username.getText();
        //Es wird die Auswahl aus der Dropdown-Liste verarbeitet die dementsprechenden Schritte eingeleitet
        choice = choiceUser.getSelectedIndex();
        //Oeffnet GUI fuer Patient
        if (choice == 0) {
            Diabetic diabetic = new Diabetic(name);
            diabetic.run();
            frame.dispose();

            //Oeffnet GUI fuer Diabetologe
        } else {
            Doctor doctor = null;
            try {
                doctor = new Doctor(name);
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
            doctor.run();
            frame.dispose();
        }
    }

    private void createUIComponents() {
        String[] choice = {"Diabetiker", "Arzt"};
        choiceUser = new JComboBox(choice);
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
        mainPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder(null, "DIAbetes DIAry - Ihre Verbindung zum Arzt", TitledBorder.LEADING, TitledBorder.TOP));
        login = new JButton();
        login.setText("Einloggen");
        mainPanel.add(login, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        username = new JTextField();
        username.setText("Benutzername");
        mainPanel.add(username, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mainPanel.add(choiceUser, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
