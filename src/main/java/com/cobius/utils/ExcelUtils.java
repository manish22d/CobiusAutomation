package com.cobius.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    public static Workbook book;
    public static Sheet sheet;

    public static Map<String, String> getComplianceData() throws IOException {
        Map<String, String> data = new HashMap<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("dataFile/TestData.xlsx");

        book = WorkbookFactory.create(inputStream);
        sheet = book.getSheet("Compliance");
        for(int i =0 ;i<sheet.getRow(0).getLastCellNum();i++)
            data.put(sheet.getRow(0).getCell(i).getStringCellValue(),sheet.getRow(1).getCell(i).getStringCellValue());
        return data;
    }
}
