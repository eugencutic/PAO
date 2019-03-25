package com.cutic.eugen;

import java.util.ArrayList;

public class RestaurantService {
    private static ArrayList<Product> mProducts;

    public static Product getProductById(int id) {
        for (Product prod : mProducts) {
            if (prod.getId() == id)
                return prod;
        }
        return null;
    }
}
