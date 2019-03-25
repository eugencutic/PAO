package com.cutic.eugen;

public class Customer {
    private static int mNextId = 0;
    private int mId;
    private String mName;
    private String mEmail;
    private boolean mRegular = false;
    private int mVisits = 0;

    public Customer() {
        mId = mNextId;
        mNextId++;
    }

    public Customer(String mName, String mEmail, boolean mRegular) {
        mId = mNextId;
        mNextId++;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mRegular = mRegular;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public boolean isRegular() {
        return mRegular;
    }

    public void setRegular(boolean mRegular) {
        this.mRegular = mRegular;
    }
}
