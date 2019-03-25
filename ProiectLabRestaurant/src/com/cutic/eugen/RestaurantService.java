package com.cutic.eugen;

import java.util.ArrayList;

public class RestaurantService {
    private static RestaurantService instance = null;

    private static ArrayList<Product> mProducts;
    private static ArrayList<Table> mTables;

    private RestaurantService(){
        mProducts = new ArrayList<>();
        mTables = new ArrayList<>();
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

    public static Product getProductById(int id) {
        for (Product prod : mProducts) {
            if (prod.getId() == id)
                return prod;
        }
        return null;
    }
}
