package com.cutic.eugen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TableRepository {
    public ArrayList<Table> readRecordsFromFile() {
        ArrayList<Table> tables = new ArrayList<>();
        File tablesFile = new File(Const.TABLES_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(tablesFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null)
            return tables;

        if (scanner.hasNextLine()) {
            int tablesCount = scanner.nextInt();
            for (int i = 0; i < tablesCount; i++) {
                tables.add(new Table());
            }
        }
        return tables;
    }

    public void writeTableCount(int count) {
        File tablesFile = new File(Const.TABLES_PATH);
        File temp = new File(Const.TABLES_PATH + "temp");

        try(FileWriter fw = new FileWriter(temp)) {
            fw.write(count);
            fw.write("\n");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean delete = tablesFile.delete();
        boolean b = temp.renameTo(tablesFile);
    }
}
