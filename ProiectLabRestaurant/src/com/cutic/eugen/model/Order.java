package com.cutic.eugen.model;

import javafx.util.Pair;

import java.util.HashMap;

public class Order {
    private int mId;
    private int mTableId;
    private static int mNextId = 0;
    private boolean mWasDeliverd = false;
    private HashMap<Integer, Integer> mProducts; //<product id, quantity>

    public Order(int tableId) {
        this.mId = mNextId;
        this.mTableId = tableId;
        mNextId++;
        mProducts = new HashMap<>();
    }

    public Order(boolean mWasDeliverd) {
        this.mId = mNextId;
        mNextId++;
        this.mWasDeliverd = mWasDeliverd;
        mProducts = new HashMap<>();
    }

    public void addProduct(Integer productId) {
        if (!mProducts.containsKey(productId)) {
            mProducts.put(productId, 1);
        }
        else {
            mProducts.computeIfPresent(productId, (k, v) -> v + 1);
        }
    }

    public int getTableId() {
        return mTableId;
    }

    public boolean wasDeliverd() {
        return mWasDeliverd;
    }

    public void setDeliverd(boolean mWasDeliverd) {
        this.mWasDeliverd = mWasDeliverd;
    }

    public HashMap<Integer, Integer> getProducts() {
        return mProducts;
    }

    @Override
    public String toString() {
        return "Order " + mId + "(" +
                (mWasDeliverd ? "Delivered" : "Pending") + ")";
    }

    public void removeProduct(Integer productId) {
        if (mProducts.containsKey(productId)) {
            if (mProducts.get(productId) == 1)
                mProducts.remove(productId);
            else
                mProducts.computeIfPresent(productId, (k, v) -> v - 1);
        }
    }
}
