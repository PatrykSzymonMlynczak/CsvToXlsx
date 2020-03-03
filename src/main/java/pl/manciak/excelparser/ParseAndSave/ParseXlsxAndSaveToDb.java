package pl.manciak.excelparser.ParseAndSave;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manciak.excelparser.DataService;
import pl.manciak.excelparser.Entity.LinesEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class ParseXlsxAndSaveToDb extends ParserParent {

    @Autowired
    public ParseXlsxAndSaveToDb(DataService dataService) {
        super(dataService);
    }

    public void saveToDb() throws IOException
    {

        FileInputStream file = new FileInputStream(new File("xxx.xlsx"));


        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            linesEntity = new LinesEntity(); // create a new LinesEntity for this loop execution
            list = new ArrayList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType())
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        list.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case Cell.CELL_TYPE_STRING:
                        list.add(cell.getStringCellValue());
                        break;
                }
            }
            linesEntity.setSingleLine(new ArrayList<>(list));
            dataService.saveOne(linesEntity);
            xlsMapped.put(mapKey, linesEntity);

            mapKey++;
        }

        mapEntity.setMapa(xlsMapped);

        System.out.println(xlsMapped);

        dataService.save(mapEntity);

        file.close();

    }
}


