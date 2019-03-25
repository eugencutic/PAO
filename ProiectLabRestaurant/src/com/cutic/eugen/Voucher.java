package com.cutic.eugen;

import java.util.ArrayList;

public class Voucher {
    private String mCode;
    private String mProductName;
    private double mPercentage;

    public Voucher(String mCode, String mProductName, double mPercentage) {
        this.mCode = mCode;
        this.mProductName = mProductName;
        this.mPercentage = mPercentage;
    }

    public String getProductName() {
        return mProductName;
    }

    public double getPercentage() {
        return mPercentage;
    }
}
