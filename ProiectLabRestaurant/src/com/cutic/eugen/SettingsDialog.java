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
    private JTextField textFieldQuantity;

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
        buttonAddProduct.addActionListener((e) -> onAddProduct());
        buttonDeleteProduct.addActionListener((e) -> onDeleteProduct());
        buttonSetTableCount.addActionListener((e) -> onSetTableCount());
    }

    private void onSetTableCount() {
        int count;
        try {
            count = Integer.parseInt(textFieldTableCount.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(contentPane, "Table count not valid.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        restaurantService.setTableCount(count);
    }

    private void onDeleteProduct() {
        Product product = (Product) comboBoxDeleteProduct.getSelectedItem();
        restaurantService.deleteProduct(product);
        comboBoxDeleteProduct.setModel(new JComboBox(restaurantService.getProducts().toArray()).getModel());
        comboBoxProducts.setModel(new JComboBox(restaurantService.getProducts().toArray()).getModel());
    }

    private void onAddProduct() {
        String type;
        if (drinkRadioButton.isSelected()) {
            type = "drink";
        } else if (foodRadioButton.isSelected()) {
            type = "food";
        } else {
            JOptionPane.showMessageDialog(contentPane, "Choose product type.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = textFieldProductName.getText();
        String priceString = textFieldProductPrice.getText();
        String quantityString = textFieldQuantity.getText();
        int priceValue;
        int quantityValue;
        try {
            priceValue = Integer.parseInt(priceString);
            quantityValue = Integer.parseInt(quantityString);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(contentPane, "Price or quantity not valid.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (type.equals("drink")) {
            restaurantService.addProduct(new Drink(priceValue, quantityValue, name));
        } else {
            restaurantService.addProduct(new FoodItem(priceValue, quantityValue, name));
        }

        comboBoxDeleteProduct.setModel(new JComboBox(restaurantService.getProducts().toArray()).getModel());
        comboBoxProducts.setModel(new JComboBox(restaurantService.getProducts().toArray()).getModel());

        textFieldProductName.setText("");
        textFieldQuantity.setText("");
        textFieldProductPrice.setText("");
        drinkRadioButton.setSelected(false);
        foodRadioButton.setSelected(false);

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
