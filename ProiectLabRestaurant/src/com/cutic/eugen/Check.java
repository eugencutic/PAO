package com.cutic.eugen;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Check {
    private final Table mTable;
    private HashSet<Voucher> mVouchers;
    private double mTotal = 0;
    private Customer mCustomer;

    public Check(Table table) {
        this.mTable = table;
        mVouchers = new HashSet<>();
    }

    public void applyVoucher(Voucher voucher) {
        mVouchers.add(voucher);
    }

    public double getTotal() {
        return mTotal;
    }

    //TODO: test checkout
    public void checkout() {
        ArrayList<Order> orders = mTable.getOrders();
        for (Order order : orders) {
            if (order.wasDeliverd()) {
                for (Map.Entry item : order.getProducts().entrySet()) {
                    double price = 0;
                    Product product = RestaurantService.getInstance().getProductById((int)item.getKey());
                    int quantity = (int)item.getValue();
                    price = product.getPrice();
                    for (Voucher voucher : mVouchers) {
                        if (voucher.getProductName().equals(product.getName())) {
                            price -= price * (voucher.getPercentage() / 100);
                        }
                    }
                    mTotal += price * quantity;
                }
            }
        }
    }

    public void refreshTable() {
        mTable.refreshOrders();
    }
}
