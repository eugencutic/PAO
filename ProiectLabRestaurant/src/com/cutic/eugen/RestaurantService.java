package com.cutic.eugen;

import javafx.scene.shape.Path;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RestaurantService {
    private static RestaurantService instance = null;

    private ArrayList<Product> mProducts;
    private ArrayList<Table> mTables;
    private ArrayList<Voucher> mVouchers;
    private ArrayList<Customer> mCustomers;


    private RestaurantService(){
        mProducts = new ArrayList<>();
        mTables = new ArrayList<>();
        mVouchers = new ArrayList<>();
        mCustomers = new ArrayList<>();
    }

    public static RestaurantService getInstance() {
        if (instance == null)
            instance = new RestaurantService();
        return instance;
    }

    public ArrayList<Customer> getCustomers() {
        return mCustomers;
    }

    public void addTable(Table table) {
        mTables.add(table);
    }

    public void addProduct(Product product) {
        mProducts.add(product);
    }

    public void addCustomer(Customer customer) {
        mCustomers.add(customer);
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

    public void refreshCustomersFile() {
        try (FileWriter fw = new FileWriter(Const.CUSTOMERS_PATH)) {
            for(Customer customer : mCustomers) {
                fw.write(customer.toFileFormatString());
            }
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
