package com.cutic.eugen;

import javax.swing.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textFieldVoucherCode;
    private JTextField textFieldProductName;
    private JTextField textFieldTableCount;
    private JTextField textFieldVoucherPercentage;
    private JComboBox comboBoxDeleteVoucher;
    private JRadioButton foodRadioButton;
    private JRadioButton drinkRadioButton;
    private JTextField textFieldProductPrice;
    private JComboBox comboBoxDeleteProduct;
    private JButton buttonAddVoucher;
    private JButton buttonAddProduct;
    private JButton buttonSetTableCount;
    private JComboBox comboBoxProducts;
    private JButton buttonDeleteVoucher;
    private JButton buttonDeleteProduct;

    private RestaurantService restaurantService;

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

        restaurantService = RestaurantService.getInstance();
        comboBoxProducts.setModel(new JComboBox(restaurantService.getProducts().toArray()).getModel());
        comboBoxDeleteProduct.setModel(new JComboBox(restaurantService.getProducts().toArray()).getModel());
        comboBoxDeleteVoucher.setModel(new JComboBox(restaurantService.getVouchers().toArray()).getModel());

        buttonAddVoucher.addActionListener((e) -> onAddVoucher());
        buttonDeleteVoucher.addActionListener((e) -> onDeleteVoucher());
    }

    private void onAddVoucher() {
        String code = textFieldVoucherCode.getText();
        Product product = (Product) comboBoxProducts.getSelectedItem();
        String productName = product.getName();
        String percentageString = textFieldVoucherPercentage.getText();
        double percentageValue;
        try {
            percentageValue = Double.parseDouble(percentageString.replace(",", "."));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(contentPane, "Percentage is not valid",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (percentageValue > 100) {
            JOptionPane.showMessageDialog(contentPane, "Percentage is not valid",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        restaurantService.addVoucher(new Voucher(code, productName, percentageValue));

        textFieldVoucherCode.setText("");
        textFieldVoucherPercentage.setText("");
        comboBoxDeleteVoucher.setModel(new JComboBox(restaurantService.getVouchers().toArray()).getModel());
    }

    private void onDeleteVoucher() {
        Voucher voucher = (Voucher) comboBoxDeleteVoucher.getSelectedItem();
        restaurantService.deleteVoucher(voucher);
        comboBoxDeleteVoucher.setModel(new JComboBox(restaurantService.getVouchers().toArray()).getModel());
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
