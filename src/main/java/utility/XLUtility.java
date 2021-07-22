package utility;

import org.apache.log4j.Level;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private String path;

    public XLUtility(String pathOfExcelFile){
        this.path=pathOfExcelFile;
    }

    public int getRowCount(String sheetName) throws IOException {
        LogsUtility.logger.setLevel(Level.INFO);
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();
        LogsUtility.logger.info("Row Count is: " + rowCount);
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        LogsUtility.logger.setLevel(Level.INFO);
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();
        LogsUtility.logger.info("Cell Count is: " + cellCount);
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNumber, int columnNumber) throws IOException {
        LogsUtility.logger.setLevel(Level.INFO);
        String data;
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(columnNumber);

        DataFormatter formatter = new DataFormatter();
        try {
            data = formatter.formatCellValue(cell);
        }catch (Exception e){
            data ="";
        }
        workbook.close();
        fileInputStream.close();
        LogsUtility.logger.info("Data is: " + data);
        return data;
    }
}
