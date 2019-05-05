package com.cutic.eugen;

import javax.swing.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textFieldVoucherCode;
    private JTextField textFieldProductName;
    private JTextField textFieldTableCount;
    private JTextField textFieldVoucherProductName;
    private JTextField textFieldVoucherPercentage;
    private JComboBox comboBoxDeleteVoucher;
    private JRadioButton foodRadioButton;
    private JRadioButton drinkRadioButton;
    private JTextField textFieldProductPrice;
    private JComboBox comboBoxDeleteProduct;
    private JButton buttonAddVoucher;
    private JButton buttonAddProduct;
    private JButton buttonSetTableCount;

    public SettingsDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
