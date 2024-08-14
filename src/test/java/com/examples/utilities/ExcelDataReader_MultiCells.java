package com.examples.utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelDataReader_MultiCells {

    public static List<Map<String, String>> readDataFromExcel(String filePath, int numberOfRows) {

        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0); // if data was in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) { // to skip the header row
                rowIterator.next();
            }

            int rowCount = 0;
            while (rowIterator.hasNext() && rowCount < numberOfRows) {
                Row row = rowIterator.next();
                Map<String, String> data = new HashMap<>();
                data.put("username", row.getCell(0).getStringCellValue());
                data.put("firstName", row.getCell(1).getStringCellValue());
                data.put("lastName", row.getCell(2).getStringCellValue());
                data.put("email", row.getCell(3).getStringCellValue());
                data.put("phone", row.getCell(4).getStringCellValue());
                dataList.add(data);
                rowCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
