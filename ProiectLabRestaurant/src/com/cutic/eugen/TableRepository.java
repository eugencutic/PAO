package com.cutic.eugen;

import java.io.File;
import java.io.FileNotFoundException;
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
}
