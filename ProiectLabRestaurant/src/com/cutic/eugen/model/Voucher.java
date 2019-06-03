package com.cutic.eugen.model;

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

    public String getCode() {
        return mCode;
    }

    @Override
    public String toString() {
        return mCode;
    }

    public String toLogFormat() {
        return "Voucher{" +
                "Code='" + mCode + '\'' +
                ", ProductName='" + mProductName + '\'' +
                ", Percentage=" + mPercentage +
                '}';
    }

    public String toFileFormatString() {
        return mCode + " " + mProductName + " " + mPercentage;
    }
}
