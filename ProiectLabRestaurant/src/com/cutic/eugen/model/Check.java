package com.cutic.eugen.model;

import com.cutic.eugen.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Check {
    private final Table mTable;
    private HashSet<Voucher> mVouchers;
    private double mTotal = 0;
    private Customer mCustomer;

    public enum Payment {CASH, CARD};
    private Payment mPaymentMethod = Payment.CASH;


    public Check(Table table) {
        this.mTable = table;
        mVouchers = new HashSet<>();
    }

    public HashSet<Voucher> getVouchers() {
        return mVouchers;
    }

    public Table getTable() {
        return mTable;
    }

    public void applyVoucher(Voucher voucher) {
        mVouchers.add(voucher);
    }

    public void setCash() {
        mPaymentMethod = Payment.CASH;
    }

    public void setCard() {
        mPaymentMethod = Payment.CARD;
    }

    public double getTotal() {
        return mTotal;
    }

    public Customer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(Customer mCustomer) {
        this.mCustomer = mCustomer;
    }

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

        if (mCustomer.isRegular()) {
            mTotal -= mTotal * 0.1;
        }
    }

    public Payment getPaymentMethod() {
        return mPaymentMethod;
    }

    public String getPaymentMethodToString() {
        switch (mPaymentMethod) {
            case CASH:
                return "Cash";
            case CARD:
                return "Card";
        }
        return "";
    }

    public void refreshTable() {
        mTable.refreshOrders();
    }
}
