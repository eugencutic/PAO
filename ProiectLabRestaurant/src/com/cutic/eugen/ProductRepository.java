package com.cutic.eugen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductRepository {
    public ArrayList<Product> readRecordsFromFile() {
        ArrayList<Product> products = new ArrayList<>();

        File foodMenu = new File(Const.FOOD_MENU_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(foodMenu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return products;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            int price = Integer.parseInt(line[0]);
            int quantity = Integer.parseInt(line[1]);
            String name = line[2];
            products.add(new FoodItem(price, quantity, name));
        }
        scanner.close();

        File drinkMenu = new File(Const.DRINK_MENU_PATH);
        scanner = null;
        try {
            scanner = new Scanner(drinkMenu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return products;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            int price = Integer.parseInt(line[0]);
            int quantity = Integer.parseInt(line[1]);
            String name = line[2];
            products.add(new Drink(price, quantity, name));
        }
        scanner.close();

        return products;
    }
}
