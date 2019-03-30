package com.cutic.eugen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CheckDetailsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane textPaneDetails;

    private final Check mCheck;

    public CheckDetailsDialog(Check check) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        mCheck = check;

        showDetails();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void showDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Table: ");
        details.append(mCheck.getTable().getId());
        details.append("\n");
        details.append("Orders:\n");
        for (Order order : mCheck.getTable().getOrders()) {
            for (Map.Entry item : order.getProducts().entrySet()) {
                Product product = RestaurantService.getInstance().getProductById((int)item.getKey());
                details.append("-");
                details.append(product.getName());
                details.append(" * ");
                details.append(item.getValue());
                details.append("\n");
            }
        }
        details.append("Vouchers applied:\n");
        for (Voucher voucher : mCheck.getVouchers()) {
            details.append("-");
            details.append(voucher.getCode());
            details.append(" for ");
            details.append(voucher.getProductName());
            details.append(" ");
            details.append(voucher.getPercentage());
            details.append("% off\n");
        }

        if (mCheck.getCustomer() != null) {
            details.append("Customer: ");
            details.append(mCheck.getCustomer().getName());
            details.append("\n");
        }

        details.append(mCheck.getTotal());
        textPaneDetails.setText(details.toString());
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
