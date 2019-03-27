package com.cutic.eugen;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WaiterGUI {
    private JPanel panel1;
    private JLabel labelTables;
    private JComboBox comboBoxTables;
    private JLabel labelOrders;
    private JButton buttonNewOrder;
    private JTextArea textAreaNewOrder;
    private JButton buttonDeleteLastProduct;
    private JButton buttonFinishOrder;
    private JComboBox comboBoxOrders;
    private JTextArea textAreaOrder;
    private JButton buttonDeleteOrder;
    private JButton buttonMakeCheck;
    private JComboBox comboBoxAddFood;
    private JComboBox comboBoxAddDrink;

    private Order mNewOrder = null;

    public WaiterGUI() {
        buttonNewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxAddDrink.setEnabled(true);
                comboBoxAddFood.setEnabled(true);
                buttonDeleteLastProduct.setEnabled(true);
                buttonFinishOrder.setEnabled(true);
                Table table = (Table)comboBoxTables.getSelectedItem();
                mNewOrder = new Order(table.getId());
            }
        });

        comboBoxAddFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = (Product) comboBoxAddFood.getSelectedItem();
                mNewOrder.addProduct(product.getId());
                refreshNewOrderTextArea();
            }
        });

        comboBoxAddDrink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = (Product) comboBoxAddDrink.getSelectedItem();
                mNewOrder.addProduct(product.getId());
                refreshNewOrderTextArea();
            }
        });

        buttonFinishOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxAddDrink.setEnabled(false);
                comboBoxAddFood.setEnabled(false);
                buttonDeleteLastProduct.setEnabled(false);
                buttonFinishOrder.setEnabled(false);
                Table table = (Table) comboBoxTables.getSelectedItem();
                if (mNewOrder != null)
                    table.addOrder(mNewOrder);
                mNewOrder = null;

                comboBoxOrders.setModel(
                        new JComboBox(table.getOrders().toArray()).getModel());
                refreshSelectedOrderTextArea();
                textAreaNewOrder.setText("");
            }
        });

        comboBoxTables.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Table table = (Table) e.getItem();
                    comboBoxOrders.setModel(
                            new JComboBox(table.getOrders().toArray()).getModel());
                    refreshSelectedOrderTextArea();
                }
            }
        });

        comboBoxOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshSelectedOrderTextArea();
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
        initTables();

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
        this.comboBoxTables.setModel(
                new JComboBox(RestaurantService.getInstance().getTables().toArray()).getModel());
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

    private void initTables() {
        File tablesFile = new File("F:/Projects/PAO/ProiectLabRestaurant/src/com/cutic/eugen/tables");
        Scanner scanner = null;
        try {
            scanner = new Scanner(tablesFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return;

        if (scanner.hasNextLine()) {
            int tablesCount = scanner.nextInt();
            for (int i = 0; i < tablesCount; i++) {
                RestaurantService.getInstance().addTable(new Table());
            }
        }
    }

    private void refreshNewOrderTextArea() {
        StringBuilder text = new StringBuilder();
        for (Map.Entry pair : mNewOrder.getProducts().entrySet()) {
            text.append(RestaurantService.getProductById((int)pair.getKey()).toString() +
                    " " + pair.getValue() + "\n");
        }
        textAreaNewOrder.setText(text.toString());
    }

    private void refreshSelectedOrderTextArea() {
        StringBuilder text = new StringBuilder();
        Order order = (Order)comboBoxOrders.getSelectedItem();
        if (order == null) {
            textAreaOrder.setText("");
            return;
        }
        for (Map.Entry pair : order.getProducts().entrySet()) {
            text.append(RestaurantService.getProductById((int)pair.getKey()).toString() +
                    " " + pair.getValue() + "\n");
        }
        textAreaOrder.setText(text.toString());
    }
}
