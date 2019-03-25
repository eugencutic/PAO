package com.cutic.eugen;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private int mId;
    private int mTableId;
    private static int mNextId = 0;
    private boolean mWasDeliverd;
    private ArrayList< Pair<Integer, Integer> > mProducts; //<product id, quantity>

    public Order(int tableId) {
        this.mId = mNextId;
        this.mTableId = tableId;
        mNextId++;
        mProducts = new ArrayList<>();
    }

    public Order(boolean mWasDeliverd) {
        this.mId = mNextId;
        mNextId++;
        this.mWasDeliverd = mWasDeliverd;
        mProducts = new ArrayList<>();
    }

    public int getTableId() {
        return mTableId;
    }

    public ArrayList<Pair<Integer, Integer>> getProducts() {
        return mProducts;
    }
}
