package com.cutic.eugen;

import java.util.ArrayList;

public class Table {
    private int mId;
    private static int mNextId = 0;
    private boolean mIsAvailable = true;
    private ArrayList<Order> mOrders;

    public Table() {
        this.mId = mNextId;
        mNextId++;
        mOrders = new ArrayList<>();
    }

    public boolean isAvailable() {
        return mIsAvailable;
    }

    public void setAvailable(boolean mIsAvailable) {
        this.mIsAvailable = mIsAvailable;
    }

    public void addOrder(Order order) {
        mOrders.add(order);
    }
}
