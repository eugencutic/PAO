package com.cutic.eugen.model;

public class Drink extends Product {

    private boolean mBig = false;

    public Drink(String mName) {
        super(mName);
    }

    public Drink(int mPrice, int mQuantityAvailable, String mName) {
        super(mPrice, mQuantityAvailable, mName);
    }

    public boolean isBig() {
        return mBig;
    }

    public void setBig() {
        this.mBig = true;
    }

    public void setSmall() {
        this.mBig = false;
    }
}
