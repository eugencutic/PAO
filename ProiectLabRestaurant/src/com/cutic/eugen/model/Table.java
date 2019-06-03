package com.cutic.eugen.model;

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

    public ArrayList<Order> getOrders() {
        return mOrders;
    }

    public int getId() {
        return mId;
    }

    @Override
    public String toString() {
        return "Table " + mId;
    }

    public void removeOrder(Order order) {
        if (!mOrders.contains(order))
            return;
        mOrders.remove(order);
    }

    public void refreshOrders() {
        mOrders = new ArrayList<>();
    }
}
