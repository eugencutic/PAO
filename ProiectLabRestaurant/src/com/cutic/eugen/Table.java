package com.cutic.eugen;

import java.util.ArrayList;

public class Table {
    private int mId;
    private static int mNextId = 0;
    private boolean mIsAvailable;
    private ArrayList<Order> mOrders;

    public Table() {
        this.mId = mNextId;
        mNextId++;
        mOrders = new ArrayList<>();
    }

    public Table(boolean mIsAvailable) {
        this.mId = mNextId;
        mNextId++;
        this.mIsAvailable = mIsAvailable;
        mOrders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        mOrders.add(order);
    }
}
