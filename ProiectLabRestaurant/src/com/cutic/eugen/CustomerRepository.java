package com.cutic.eugen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.cutic.eugen.Customer;

public class CustomerRepository{

    public ArrayList<Customer> readRecordsFromFile() {
        ArrayList<Customer> customers = new ArrayList<>();
        File customersFile = new File(Const.CUSTOMERS_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(customersFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return customers;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\*");
            if (line[0].equals(""))
                return customers;
            int visits = Integer.parseInt(line[0]);
            String name = line[1];
            String email = line[2];
            customers.add(new Customer(name, email, visits));
        }
        scanner.close();
        return customers;
    }

    public void writeRecordsToFile(ArrayList<Customer> customers) {
        try (FileWriter fw = new FileWriter(Const.CUSTOMERS_PATH)) {
            for(Customer customer : customers) {
                fw.write(customer.toFileFormatString());
            }
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
