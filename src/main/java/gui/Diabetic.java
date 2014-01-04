package gui;

import javax.swing.*;

/**
 * Created by denjae on 02.01.14.
 */
public class Diabetic {
    private JTextField bloodSugar;
    private JTable output;
    private JPanel values;
    private JTextField time;
    private JTextField date;
    private JButton send;
    private JButton buttonLeft;
    private JButton buttonRight;
    private JPanel mainPanel;
    private String user;

    public void setUser(String name){
        this.user=name;

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Diabetic");
        frame.setContentPane(new Diabetic().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
