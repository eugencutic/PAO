package com.cutic.eugen.repos;

import com.cutic.eugen.Const;
import com.cutic.eugen.model.Voucher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VoucherRepository {
    public ArrayList<Voucher> readRecordsFromFile() {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        File vouchersFile = new File(Const.VOUCHERS_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(vouchersFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return vouchers;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            if (!line[0].equals("")) {
                String code = line[0];
                String name = line[1];
                double percentage = Double.parseDouble(line[2]);
                vouchers.add(new Voucher(code, name, percentage));
            }
        }
        scanner.close();
        return vouchers;
    }

    public void writeRecordsToFile(ArrayList<Voucher> vouchers) {
        try (FileWriter fw = new FileWriter(Const.VOUCHERS_PATH)) {
            for(Voucher voucher : vouchers) {
                fw.write(voucher.toFileFormatString());
            }
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void appendRecordToFile(Voucher voucher) {
        try(FileWriter fw = new FileWriter(Const.VOUCHERS_PATH, true)) {
            fw.write("\n");
            fw.write(voucher.toFileFormatString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteRecordFromFile(Voucher voucher) {
        File vouchersFile = new File(Const.VOUCHERS_PATH);
        File temp = new File(Const.VOUCHERS_PATH + "temp");

        try(FileWriter fw = new FileWriter(temp)) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(vouchersFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (scanner == null)
                return;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] properties = line.split(" ");
                String code = properties[0];

                if (!code.equals(voucher.getCode())) {
                    fw.write(line);
                    fw.write("\n");
                }
            }
            fw.flush();
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean delete = vouchersFile.delete();
        boolean b = temp.renameTo(vouchersFile);
    }

    public ArrayList<Voucher> readRecordsFromDB() {
        ArrayList<Voucher> Vouchers = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            conn = DriverManager.getConnection(Const.DB_URL);

            stmt = conn.createStatement();

            String qrySQL = "SELECT * FROM Vouchers ORDER BY id";
            rs = stmt.executeQuery(qrySQL);

            while(rs.next()) {
                Vouchers.add(new Voucher(
                        rs.getString("code"),
                        rs.getString("product_name"),
                        rs.getDouble("percentage")
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
        return Vouchers;
    }

    public void addRecordToDB(Voucher Voucher) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = DriverManager.getConnection(Const.DB_URL);

            String qrySQL = "INSERT INTO Vouchers VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(qrySQL);

            pstmt.setString(1, Voucher.getCode());
            pstmt.setString(2, Voucher.getProductName());
            pstmt.setDouble(3, Voucher.getPercentage());

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

    public void deleteRecordFromDB(Voucher Voucher) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = DriverManager.getConnection(Const.DB_URL);

            String qrySQL = "DELETE FROM Vouchers WHERE id = ?";
            pstmt = conn.prepareStatement(qrySQL);

            pstmt.setString(1, Voucher.getCode());
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
