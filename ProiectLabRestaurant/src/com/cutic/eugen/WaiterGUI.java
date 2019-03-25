package com.cutic.eugen;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WaiterGUI {
    private JPanel panel1;
    private JLabel labelTables;
    private JComboBox comboBoxTables;
    private JLabel labelOrders;
    private JButton buttonNewOrder;
    private JTextArea textArea2;
    private JButton buttonDeleteLastProduct;
    private JButton buttonFinishOrder;
    private JComboBox comboBoxOrders;
    private JTextArea textAreaOrder;
    private JButton buttonDeleteOrder;
    private JButton buttonMakeCheck;
    private JComboBox comboBoxAddFood;
    private JComboBox comboBoxAddDrink;

    public WaiterGUI() {
        buttonNewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add new order
            }
        });

        initRestaurantConfig();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WaiterGUI");
        frame.setContentPane(new WaiterGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setVisible(true);


    }

    private void initRestaurantConfig() {
        initDrinks();
        initFood();

        ArrayList<Product> products = RestaurantService.getInstance().getProducts();
        ArrayList<Drink> drinks = new ArrayList<>();
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        for (Product prod : products) {
            if (prod instanceof Drink) {
                drinks.add((Drink) prod);
            }
            if (prod instanceof FoodItem) {
                foodItems.add((FoodItem) prod);
            }
        }

        this.comboBoxAddDrink.setModel(new JComboBox(drinks.toArray()).getModel());
        this.comboBoxAddFood.setModel(new JComboBox(foodItems.toArray()).getModel());
    }

    private void initFood() {
        File foodMenu = new File("F:/Projects/PAO/ProiectLabRestaurant/src/com/cutic/eugen/foodMenu");
        Scanner scanner = null;
        try {
            scanner = new Scanner(foodMenu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            int price = Integer.parseInt(line[0]);
            int quantity = Integer.parseInt(line[1]);
            String name = line[2];
            RestaurantService.getInstance().addProduct(new FoodItem(price, quantity, name));
        }
        scanner.close();
    }

    private void initDrinks(){
        File drinkMenu = new File("F:/Projects/PAO/ProiectLabRestaurant/src/com/cutic/eugen/drinkMenu");
        Scanner scanner = null;
        try {
            scanner = new Scanner(drinkMenu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            int price = Integer.parseInt(line[0]);
            int quantity = Integer.parseInt(line[1]);
            String name = line[2];
            RestaurantService.getInstance().addProduct(new Drink(price, quantity, name));
        }
        scanner.close();
    }
}
