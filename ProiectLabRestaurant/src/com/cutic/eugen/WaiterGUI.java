package com.cutic.eugen;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class WaiterGUI {
    private JPanel panel1;
    private JLabel labelTables;
    private JComboBox comboBoxTables;
    private JLabel labelOrders;
    private JButton buttonNewOrder;
    private JTextArea textAreaNewOrder;
    private JButton buttonCancelOrder;
    private JButton buttonFinishOrder;
    private JComboBox comboBoxOrders;
    private JTextArea textAreaOrder;
    private JButton buttonDeleteOrder;
    private JButton buttonMakeCheck;
    private JComboBox comboBoxAddFood;
    private JComboBox comboBoxAddDrink;
    private JButton buttonDelivered;
    private JButton buttonRegisterCustomer;

    private Order mNewOrder = null;

    private RestaurantService restaurantService;

    public WaiterGUI() {
        restaurantService = RestaurantService.getInstance();

        buttonNewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxAddDrink.setEnabled(true);
                comboBoxAddFood.setEnabled(true);
                buttonCancelOrder.setEnabled(true);
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
                buttonCancelOrder.setEnabled(false);
                buttonFinishOrder.setEnabled(false);
                Table table = (Table) comboBoxTables.getSelectedItem();
                if (mNewOrder != null)
                    table.addOrder(mNewOrder);
                mNewOrder = null;

                comboBoxOrders.setModel(
                        new JComboBox(table.getOrders().toArray()).getModel());
                refreshSelectedOrderTextArea();
                refreshNewOrderTextArea();
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

        buttonCancelOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxAddDrink.setEnabled(false);
                comboBoxAddFood.setEnabled(false);
                buttonCancelOrder.setEnabled(false);
                buttonFinishOrder.setEnabled(false);

                mNewOrder = null;
                refreshNewOrderTextArea();
            }
        });

        buttonDeleteOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = (Order) comboBoxOrders.getSelectedItem();
                Table table = (Table) comboBoxTables.getSelectedItem();
                table.removeOrder(order);
                comboBoxOrders.setModel(new JComboBox(table.getOrders().toArray()).getModel());
                refreshSelectedOrderTextArea();
            }
        });

        buttonMakeCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table table = (Table) comboBoxTables.getSelectedItem();
                MakeCheckDialog makeCheckDialog = new MakeCheckDialog(table);
                makeCheckDialog.setSize(450, 300);
                makeCheckDialog.setLocationRelativeTo(null);
                //TODO: refresh table
                makeCheckDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        comboBoxOrders.setModel(new JComboBox(table.getOrders().toArray()).getModel());
                        refreshSelectedOrderTextArea();
                    }
                });
                makeCheckDialog.setVisible(true);
            }
        });

        buttonDelivered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = (Order)comboBoxOrders.getSelectedItem();
                Table table = (Table)comboBoxTables.getSelectedItem();
                order.setDeliverd(true);
                comboBoxOrders.setModel(new JComboBox(table.getOrders().toArray()).getModel());
            }
        });

        buttonRegisterCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomerDialog addCustomerDialog = new AddCustomerDialog();
                addCustomerDialog.setSize(450, 300);
                addCustomerDialog.setLocationRelativeTo(null);
                addCustomerDialog.setVisible(true);
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
        this.comboBoxTables.setModel(
                new JComboBox(RestaurantService.getInstance().getTables().toArray()).getModel());
    }

    private void initFood() {
        File foodMenu = new File(Const.FOOD_MENU_PATH);
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
        File drinkMenu = new File(Const.DRINK_MENU_PATH);
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

    private void refreshNewOrderTextArea() {
        if (mNewOrder == null) {
            textAreaNewOrder.setText("");
            return;
        }

        StringBuilder text = new StringBuilder();
        for (Map.Entry pair : mNewOrder.getProducts().entrySet()) {
            text.append(RestaurantService.getInstance().getProductById((int)pair.getKey()).toString() +
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
            text.append(RestaurantService.getInstance().getProductById((int)pair.getKey()).toString() +
                    " " + pair.getValue() + "\n");
        }
        textAreaOrder.setText(text.toString());
    }
}
