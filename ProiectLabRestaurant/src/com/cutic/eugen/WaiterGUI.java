package com.cutic.eugen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaiterGUI {
    private JPanel panel1;
    private JLabel labelTables;
    private JComboBox comboBoxTables;
    private JLabel labelOrders;
    private JButton buttonNewOrder;
    private JComboBox comboBoxAddProduct;
    private JTextArea textArea2;
    private JButton buttonDeleteLastProduct;
    private JButton buttonFinishOrder;
    private JLabel labelCheck;
    private JComboBox comboBoxCashorCard;
    private JComboBox comboBoxOrders;
    private JTextArea textAreaOrder;
    private JButton buttonDeleteOrder;

    public WaiterGUI() {
        buttonNewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add new order
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WaiterGUI");
        frame.setContentPane(new WaiterGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setVisible(true);


    }
}
