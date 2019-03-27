package com.cutic.eugen;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MakeCheckDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonApplyVoucher;
    private JTextField textFieldVoucher;
    private JComboBox comboBoxCustomer;
    private JComboBox comboBoxCashCard;
    private JLabel labelCustomer;
    private JLabel labelCashCard;

    private Check mCheck;

    public MakeCheckDialog(Table table) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        mCheck = new Check(table);

        buttonApplyVoucher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = textFieldVoucher.getText();
                Voucher voucher = RestaurantService.getInstance().getVoucherByCode(code);
                if (voucher != null) {
                    mCheck.applyVoucher(voucher);
                }
                textFieldVoucher.setText("");
            }
        });

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
    }

    private void onOK() {
        mCheck.checkout();
        mCheck.refreshTable();

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
