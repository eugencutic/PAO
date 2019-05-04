package com.cutic.eugen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            String code = line[0];
            String name = line[1];
            int percentage = Integer.parseInt(line[2]);
            vouchers.add(new Voucher(code, name, percentage));
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
}
