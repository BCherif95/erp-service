package com.tuwindi.erp.erpservice.helper;

import com.tuwindi.erp.erpservice.entities.BudgetLine;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    static String[] HEADERS = {"Section", "Activité", "Catégorie", "Nombre1", "Unité1", "Nombre2", "Unité2", "Prix unitaire XOF", "Total XOF"};
    static String SHEET = "FormatStandard";

    public static ByteArrayInputStream budgetToExcel(List<BudgetLine> budgetLines) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            // Creating header
            Row headerRow = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (BudgetLine line : budgetLines) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(line.getSection().getTitle());
                row.createCell(1).setCellValue(line.getTitle());
                row.createCell(2).setCellValue(line.getCategory().getName());
                row.createCell(3).setCellValue(line.getQuantity1());
                row.createCell(4).setCellValue(line.getUnity1().getTitle());
                row.createCell(5).setCellValue(line.getQuantity2());
                row.createCell(6).setCellValue(line.getUnity2().getTitle());
                row.createCell(7).setCellValue(line.getUnitPrice());
                row.createCell(8).setCellValue(line.getTotal());
            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Echec de l'importation de données dans un fichier Excel: " + e.getMessage());
        }
    }
}

