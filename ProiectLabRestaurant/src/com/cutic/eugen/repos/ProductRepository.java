package com.cutic.eugen.repos;

import com.cutic.eugen.Const;
import com.cutic.eugen.model.Drink;
import com.cutic.eugen.model.FoodItem;
import com.cutic.eugen.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
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
            if (!line[0].equals("")) {
                int price = Integer.parseInt(line[0]);
                int quantity = Integer.parseInt(line[1]);
                String name = line[2];
                products.add(new FoodItem(price, quantity, name));
            }
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

    public void appendRecordToFile(Product product) {
        String path = Const.FOOD_MENU_PATH;
        if (product instanceof Drink)
            path = Const.DRINK_MENU_PATH;
        try(FileWriter fw = new FileWriter(path, true)) {
            fw.write(product.toFileFormatString());
            fw.write("\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteRecordFromFile(Product product) {
        String path;
        if (product instanceof Drink) {
            path = Const.DRINK_MENU_PATH;
        } else if (product instanceof FoodItem) {
            path = Const.FOOD_MENU_PATH;
        } else {
            return;
        }

        File productsFile = new File(path);
        File temp = new File(path + "temp");

        try(FileWriter fw = new FileWriter(temp)) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(productsFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (scanner == null)
                return;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] properties = line.split(" ");
                String name = properties[2];

                if (!name.equals(product.getName())) {
                    fw.write(line);
                    fw.write("\n");
                }
            }
            fw.flush();
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean delete = productsFile.delete();
        boolean b = temp.renameTo(productsFile);

    }

    public ArrayList<Product> readRecordsFromDB() {
        ArrayList<Product> Products = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            conn = DriverManager.getConnection("");

            stmt = conn.createStatement();

            String qrySQL = "SELECT * FROM Products ORDER BY id";
            rs = stmt.executeQuery(qrySQL);

            while(rs.next()) {
                Products.add(new Product(
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        rs.getString("name")
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
        return Products;
    }

    public void addRecordToDB(Product Product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = DriverManager.getConnection("");

            String qrySQL = "INSERT INTO Products VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(qrySQL);

            pstmt.setInt(1, Product.getPrice());
            pstmt.setInt(2, Product.getQuantityAvailable());
            pstmt.setString(3, Product.getName());

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

    public void deleteRecordFromDB(Product Product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = DriverManager.getConnection("");

            String qrySQL = "DELETE FROM Products WHERE id = ?";
            pstmt = conn.prepareStatement(qrySQL);

            pstmt.setInt(1, Product.getId());
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
}
