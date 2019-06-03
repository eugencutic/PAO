package com.cutic.eugen.model;

public class FoodItem extends Product {

    private boolean mVegan = false;

    public FoodItem(String mName) {
        super(mName);
    }

    public FoodItem(int mPrice, int mQuantityAvailable, String mName) {
        super(mPrice, mQuantityAvailable, mName);
    }

    public boolean isVegan() {
        return mVegan;
    }

    public void setVegan() {
        this.mVegan = true;
    }

    public void setNotVegan() {
        this.mVegan = false;
    }
}
