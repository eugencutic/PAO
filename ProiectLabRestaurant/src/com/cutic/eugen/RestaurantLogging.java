package com.cutic.eugen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RestaurantLogging {

    private static String logPath = Const.LOG_PATH;

    public static void logCustomerAdded(Customer customer) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            fw.write("ADDED: " + customer.toLogFormat() + "\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCustomerDeleted(Customer customer) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            fw.write("DELETED: " + customer.toLogFormat() + "\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logProductAdded(Product product) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            fw.write("ADDED: " + product.toLogFormat() + "\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logProductDeleted(Product product) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            fw.write("DELETED: " + product.toLogFormat() + "\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logVoucherAdded(Voucher voucher) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            fw.write("ADDED: " + voucher.toLogFormat() + "\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logVoucherDeleted(Voucher voucher) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            fw.write("DELETED: " + voucher.toLogFormat() + "\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCheckDetails(StringBuilder details) {
        try (FileWriter fw = new FileWriter(logPath, true)) {
            fw.write("CHECK: {" + details + "}\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
