package com.cutic.eugen;

import javafx.util.Pair;

import java.util.ArrayList;

public class Check {
    private final ArrayList<Order> mOrders;
    private ArrayList<Voucher> mVouchers;
    private double mTotal = 0;
    private Customer mCustomer;

    public Check(ArrayList<Order> mOrders) {
        this.mOrders = mOrders;
    }

    public void applyVoucher(Voucher voucher) {
        mVouchers.add(voucher);
    }

    public void checkout() {
        /*for (Order order : mOrders) {
            for (Pair<Integer, Integer> item : order.getProducts()) {
                Product product = RestaurantService.getProductById(item.getKey());
                if (product != null) {
                    double price = product.getPrice();
                    for (Voucher voucher: mVouchers) {
                        if (product.getName().equals(voucher.getProductName())) {
                            price -= price * voucher.getPercentage();
                        }
                    }
                    mTotal += item.getValue() * price;
                }
            }
        }*/
        //TODO: rewrite checkout for hashmap
    }
}
