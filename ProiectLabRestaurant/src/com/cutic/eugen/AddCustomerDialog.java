package com.cutic.eugen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldName;
    private JTextField textFieldEmail;
    private JLabel labelName;
    private JLabel labelEmail;

    public AddCustomerDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

        textFieldEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (textFieldEmail.getForeground() == Color.RED) {
                    textFieldEmail.setForeground(Color.BLACK);
                    textFieldEmail.setText("");
                }
            }
        });

        textFieldName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (textFieldName.getForeground() == Color.RED) {
                    textFieldName.setForeground(Color.BLACK);
                    textFieldName.setText("");
                }
            }
        });
    }

    private void onOK() {
        String name = textFieldName.getText();
        boolean notValid = false;
        if(!RegexTestPatternMatcher.validateName(name)) {
            textFieldName.setText("Name not valid.");
            textFieldName.setForeground(Color.RED);
            notValid = true;
        }
        String email = textFieldEmail.getText();
        if (!RegexTestPatternMatcher.validateEmail(email)){
            textFieldEmail.setText("Email not valid.");
            textFieldEmail.setForeground(Color.RED);
            notValid = true;
        }
        if (notValid)
            return;

        RestaurantService.getInstance().addCustomer(new Customer(name, email, 1));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
