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
    private JButton login;
    private JComboBox choosingUser;
    private JTextField username;
    private int selectedUser = 0;
    private static JFrame frame = new JFrame("Login");
    String name;

    public static void main(String[] args) {

        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Login() {
        initComponents();
    }


    //Erstellt die Komponenten des Fensters
    private void initComponents(){
        panel1 = new JPanel();
        login = new JButton();
        choosingUser = new JComboBox();
        username = new JTextField();

        choosingUser.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    selectedUser = choosingUser.getSelectedIndex();
                    System.out.println(selectedUser);
                }
            }
        });

        username.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name = username.getText();
                    System.out.println(name);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Bitte einen gueltigen Namen eingeben!");
                }
            }
        });
        login.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUser == 0) {
                    Diabetic diabetic = new Diabetic();
                    diabetic.run();
                    diabetic.setUser(name);
                } /*
                else{
                    Doctor doctor = new Doctor();
                    doctor.setName(name);
                }*/
                frame.dispose();
            }

        });
    }



}
