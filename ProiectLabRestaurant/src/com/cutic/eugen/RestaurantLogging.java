package com.cutic.eugen;

import com.cutic.eugen.model.Customer;
import com.cutic.eugen.model.Product;
import com.cutic.eugen.model.Voucher;

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
            sb.append(",");
            sb.append("thread_name");
            sb.append("\n");

            pw.write(sb.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCustomerAdded(Customer customer) {
        Thread t = Thread.currentThread();
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("customer_added");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append(",");
            sb.append(t.getName());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCustomerDeleted(Customer customer) {
        Thread t = Thread.currentThread();
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("customer_deleted");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append(",");
            sb.append(t.getName());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logProductAdded(Product product) {
        Thread t = Thread.currentThread();
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("product_added");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append(",");
            sb.append(t.getName());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logProductDeleted(Product product) {
        Thread t = Thread.currentThread();
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("product_deleted");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append(",");
            sb.append(t.getName());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logVoucherAdded(Voucher voucher) {
        Thread t = Thread.currentThread();
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("voucher_added");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append(",");
            sb.append(t.getName());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logVoucherDeleted(Voucher voucher) {
        Thread t = Thread.currentThread();
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("voucher_deleted");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append(",");
            sb.append(t.getName());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCheckDetails(StringBuilder details) {
        Thread t = Thread.currentThread();
        try (FileWriter fw = new FileWriter(logPath, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("check_paid");
            sb.append(",");
            sb.append(LocalDateTime.now().toString());
            sb.append(",");
            sb.append(t.getName());
            sb.append("\n");
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
