package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by denjae on 31.12.13.
 */
public class Login {
    private JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JLabel Header;
    private JButton Einloggen;
    private JComboBox ChoosingUser;

    public Login() {
        ChoosingUser.addItemListener(new ItemListener() {
            String selectedItem = (String) ChoosingUser.getSelectedItem();

            @Override
            public void itemStateChanged(ItemEvent e) {
            }
        });
        Einloggen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
