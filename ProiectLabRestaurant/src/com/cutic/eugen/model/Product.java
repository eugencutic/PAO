package com.cutic.eugen.model;

public class Product {
    private int mId;
    private static int mNextId = 0;
    private int mPrice;
    private int mQuantityAvailable;
    private final String mName;

    public Product(String mName) {
        this.mId = mNextId;
        mNextId++;
        this.mName = mName;
    }

    public Product(int mPrice, int mQuantityAvailable, String mName) {
        this.mId = mNextId;
        mNextId++;
        this.mPrice = mPrice;
        this.mQuantityAvailable = mQuantityAvailable;
        this.mName = mName;
    }

    public int getPrice() {
        return mPrice;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getQuantityAvailable() {
        return mQuantityAvailable;
    }

    public void setQuantityAvailable(int mQuantityAvailable) {
        this.mQuantityAvailable = mQuantityAvailable;
    }

    @Override
    public String toString() {
        return mName;
    }

    public String toLogFormat() {
        return "Product{" +
                "Id=" + mId +
                ", Price=" + mPrice +
                ", QuantityAvailable=" + mQuantityAvailable +
                ", Name='" + mName + '\'' +
                '}';
    }

    public String toFileFormatString() {
        return mPrice + " " + mQuantityAvailable + " " + mName;
    }
}
