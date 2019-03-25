package com.cutic.eugen;

import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File file =
                new File("F:/Projects/PAO/ProiectLabRestaurant/src/com/cutic/eugen/configRestaurant");
        Scanner scanner = null;
        try{
            scanner = new Scanner(file);
        }
        catch (IOException ex) {
            System.out.println("Nu s-a gasit fisierul configRestaurant.txt");
        }

        if (scanner == null)
            return;


        int tableCount = Integer.parseInt(scanner.nextLine());
        System.out.println(tableCount);
        String line;
        String product;
        int quantity, price;

        //TODO: save drinks and food
        while(!(line = scanner.nextLine()).equals("-")) {
            String[] productQuantityPrice = line.split("_");
            product = productQuantityPrice[0];
            System.out.println(product);
            quantity = Integer.parseInt( productQuantityPrice[1] );
            System.out.println(quantity);
            price = Integer.parseInt( productQuantityPrice[2] );
            System.out.println(price);
        }
        while(scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        WaiterGUI waiterGUI = new WaiterGUI();
        try {

            waiterGUI.wait();
        }catch (InterruptedException ex) {
            System.out.println("interrupted");
        }
    }
}
