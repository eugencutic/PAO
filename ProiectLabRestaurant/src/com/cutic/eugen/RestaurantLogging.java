package com.cutic.eugen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class RestaurantLogging {

    private static String logPath = Const.CSV_LOG_PATH;

    public static void initLog() {
        try (PrintWriter pw = new PrintWriter(new File(logPath))) {
            StringBuilder sb = new StringBuilder();

            sb.append("nume_actiune");
            sb.append(",");
            sb.append("timestamp");
            sb.append("\n");

            pw.write(sb.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCustomerAdded(Customer customer) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("customer_added");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCustomerDeleted(Customer customer) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("customer_deleted");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logProductAdded(Product product) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("product_added");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logProductDeleted(Product product) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("product_deleted");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logVoucherAdded(Voucher voucher) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("voucher_added");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logVoucherDeleted(Voucher voucher) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("voucher_deleted");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCheckDetails(StringBuilder details) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("check_paid");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
