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
    private JLabel Header;
    private JButton login;
    private JComboBox ChoosingUser;
    private JTextField benutzernameTextField;
    private int selectedUser = 0;

    public Login() {
        ChoosingUser.addItemListener(new ItemListener() {

                public void itemStateChanged( ItemEvent event ) {
                if( event.getStateChange() == ItemEvent.SELECTED ) {
                    selectedUser = ChoosingUser.getSelectedIndex();
                    System.out.println(selectedUser);
                }
            }
        });
        login.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
               /* if(selectedUser == 0){
                    Diabetic diabetic = new Diabetic();
                    diabetic.setName(name);
                }
                else{
                    Doctor doctor = new Doctor();
                    doctor.setName(name);
                }*/
            }

        });


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
