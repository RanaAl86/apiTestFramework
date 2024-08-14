package com.examples.utilities;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDataReader {

    public static Map<String, Object> readDataFromWord(String filePath) {
        Map<String, Object> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {

                String text = para.getText();
                String[] keyValue = para.getText().split(":");
                if (keyValue.length == 2) {
                    data.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
