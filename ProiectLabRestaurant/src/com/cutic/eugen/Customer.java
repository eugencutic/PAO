package com.cutic.eugen;

public class Customer {
    private static int mNextId = 0;
    private int mId;
    private String mName;
    private String mEmail;
    private boolean mRegular = false;
    private int mVisits = 1;

    public Customer() {
        mId = mNextId;
        mNextId++;
    }

    public Customer(String mName, String mEmail, int visits) {
        mId = mNextId;
        mNextId++;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mVisits = visits;
        this.mRegular = this.mVisits > 5;
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

    public String toFileFormatString() {
        return mVisits + "*" +  mName + "*" + mEmail + "\n";
    }

    @Override
    public String toString() {
        return "Customer: " + mName + "\n";
    }

    public String toLogFormat() {
        return "Customer{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", Email='" + mEmail + '\'' +
                ", Regular=" + mRegular +
                ", Visits=" + mVisits +
                '}';
    }

    public void addVisit() {
        mVisits++;
        mRegular = mVisits > 5;
    }
}
