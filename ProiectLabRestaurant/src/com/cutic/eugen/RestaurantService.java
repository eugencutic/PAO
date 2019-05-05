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

    private CustomerRepository mCustomerRepo;
    private VoucherRepository mVoucherRepo;
    private TableRepository mTableRepo;
    private ProductRepository mProductRepo;

    private RestaurantService(){
        mProducts = new ArrayList<>();
        mTables = new ArrayList<>();
        mVouchers = new ArrayList<>();
        mCustomers = new ArrayList<>();

        mCustomerRepo = new CustomerRepository();
        mVoucherRepo = new VoucherRepository();
        mTableRepo = new TableRepository();
        mProductRepo = new ProductRepository();
        initCustomers();
        initVouchers();
        initTables();
        initProducts();
    }

    public static RestaurantService getInstance() {
        if (instance == null)
            instance = new RestaurantService();
        return instance;
    }

    public void initCustomers() {
        mCustomers = mCustomerRepo.readRecordsFromFile();
    }

    public void initVouchers() {
        mVouchers = mVoucherRepo.readRecordsFromFile();
    }

    private void initTables() {
        mTables = mTableRepo.readRecordsFromFile();
    }

    private void initProducts() {
        mProducts = mProductRepo.readRecordsFromFile();
    }

    public ArrayList<Customer> getCustomers() {
        return mCustomers;
    }

    public ArrayList<Voucher> getVouchers() {
        return mVouchers;
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
        mVoucherRepo.appendRecordToFile(voucher);
    }

    public void deleteVoucher(Voucher voucher) {
        mVouchers.remove(voucher);
        mVoucherRepo.deleteRecordFromFile(voucher);
    }

    public Voucher getVoucherByCode(String code) {
        for (Voucher voucher : mVouchers) {
            if (voucher.getCode().equals(code))
                return voucher;
        }
        return null;
    }

    public void refreshCustomersFile() {
        mCustomerRepo.writeRecordsToFile(mCustomers);
    }

    public void refreshVouchersFile() {
        mVoucherRepo.writeRecordsToFile(mVouchers);
    }
}
