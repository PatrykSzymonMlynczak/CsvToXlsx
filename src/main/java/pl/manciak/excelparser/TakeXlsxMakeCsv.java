package pl.manciak.excelparser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class TakeXlsxMakeCsv  {

    public static void main(String [] args) throws IOException {

        FileInputStream file = new FileInputStream(new File("converted.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        ArrayList<String> list = new ArrayList<>();

        while(rowIterator.hasNext()){
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while(cellIterator.hasNext()){
                Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            list.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case Cell.CELL_TYPE_STRING:
                            list.add(cell.getStringCellValue());
                            break;
                    }
            }
            list.add("\n");
        }

        File csvOutputFile = new File("converted.csv");
        PrintWriter printWriter = new PrintWriter(csvOutputFile);


        String collect2 = list.stream().collect(Collectors.joining(","));
        System.out.println("xlsx file written successfully on disk.");

        printWriter.write(collect2);
        printWriter.close();



    }

}



