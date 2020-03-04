package pl.manciak.excelparser.SingleSimpleParsingClass;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TakeCsvMakeXlsx {

    public static void main (String [] args) throws IOException {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Data");

        //Stream to Read Csv file
        FileReader fileReader = new FileReader("converted.csv");
        BufferedReader br = new BufferedReader(fileReader);

        //read first line
        String line = br.readLine();
        String array[];
        List<String> list = new ArrayList<>();

        int rownum = 0;
        while (line != null)
        {
            array = line.split(",");

            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            for (String obj : array)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue(obj);
            }
            line = br.readLine();
        }

        try
        {
            FileOutputStream out = new FileOutputStream(new File("converted2.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("xlsx file written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
