package pl.manciak.excelparser;

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
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        //Stream to Read Csv file
        FileReader fileReader = new FileReader("grocery_sample.csv");
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
                    cell.setCellValue((String)obj);

            }
            line = br.readLine();

        }

        try
        {
            //Write new file in file system
            FileOutputStream out = new FileOutputStream(new File("converted.xlsx"));
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
