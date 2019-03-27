package com.cutic.eugen;

import java.util.ArrayList;

public class RestaurantService {
    private static RestaurantService instance = null;

    private ArrayList<Product> mProducts;
    private ArrayList<Table> mTables;
    private ArrayList<Voucher> mVouchers;

    private RestaurantService(){
        mProducts = new ArrayList<>();
        mTables = new ArrayList<>();
        mVouchers = new ArrayList<>();
    }

    public static RestaurantService getInstance() {
        if (instance == null)
            instance = new RestaurantService();
        return instance;
    }

    public void addTable(Table table) {
        mTables.add(table);
    }

    public void addProduct(Product product) {
        mProducts.add(product);
    }

    public ArrayList<Product> getProducts() {
        return mProducts;
    }

    public ArrayList<Table> getTables() {
        return mTables;
    }

    public Product getProductById(int id) {
        for (Product prod : mProducts) {
            if (prod.getId() == id)
                return prod;
        }
        return null;
    }

    public void addVoucher(Voucher voucher) {
        mVouchers.add(voucher);
    }

    public Voucher getVoucherByCode(String code) {
        for (Voucher voucher : mVouchers) {
            if (voucher.getCode().equals(code))
                return voucher;
        }
        return null;
    }
}
