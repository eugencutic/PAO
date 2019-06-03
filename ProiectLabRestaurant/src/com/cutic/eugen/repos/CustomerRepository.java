package com.cutic.eugen.repos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.cutic.eugen.Const;
import com.cutic.eugen.model.Customer;

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
            if (!line[0].equals("")) {
                int visits = Integer.parseInt(line[0]);
                String name = line[1];
                String email = line[2];
                customers.add(new Customer(name, email, visits));
            }
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

    public ArrayList<Customer> readRecordsFromDB() {
        ArrayList<Customer> customers = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            conn = DriverManager.getConnection(Const.DB_URL);

            stmt = conn.createStatement();

            String qrySQL = "SELECT * FROM Customers ORDER BY id";
            rs = stmt.executeQuery(qrySQL);

            while(rs.next()) {
                customers.add(new Customer(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("visits")
                ));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Eroare la conectarea la BD: " + ex);
        }
        finally
        {
            try
            {
                if(rs != null)
                    rs.close();

                if(stmt != null)
                    stmt.close();

                if(conn != null)
                    conn.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Eroare la închiderea conexiunii cu BD!");
            }
        }
        return customers;
    }

    public void addRecordToDB(Customer customer) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = DriverManager.getConnection(Const.DB_URL);

            String qrySQL = "INSERT INTO Customers VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(qrySQL);

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setInt(3, customer.getVisits());

            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("Eroare la conectarea la BD: " + ex);
        }
        finally
        {
            try
            {
                if(pstmt != null)
                    pstmt.close();

                if(conn != null)
                    conn.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Eroare la închiderea conexiunii cu BD!");
            }
        }
    }

    public void deleteRecordFromDB(Customer customer) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = DriverManager.getConnection(Const.DB_URL);

            String qrySQL = "DELETE FROM Customers WHERE id = ?";
            pstmt = conn.prepareStatement(qrySQL);

            pstmt.setInt(1, customer.getId());
            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("Eroare la conectarea la BD: " + ex);
        }
        finally
        {
            try
            {
                if(pstmt != null)
                    pstmt.close();

                if(conn != null)
                    conn.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Eroare la închiderea conexiunii cu BD!");
            }
        }
    }

    public void appendRecordToFile(Customer customer) {
        File customersFile = new File(Const.CUSTOMERS_PATH);

        try(FileWriter fw = new FileWriter(customersFile, true)) {
            fw.write(customer.toFileFormatString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteRecordFromFile(Customer customer) {
        File customersFile = new File(Const.CUSTOMERS_PATH);
        File temp = new File(Const.CUSTOMERS_PATH + "temp");

        try(FileWriter fw = new FileWriter(temp)) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(customersFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (scanner == null)
                return;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] properties = line.split("\\*");
                String name = properties[1];

                if (!name.equals(customer.getName())) {
                    fw.write(line);
                    fw.write("\n");
                }
            }
            fw.flush();
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean delete = customersFile.delete();
        boolean b = temp.renameTo(customersFile);
    }
}
