package pl.manciak.excelparser.ParseAndSave;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.manciak.excelparser.Entity.MapEntity;

import java.io.*;
import java.util.ArrayList;

@Service
public class TakeFromDbMakeXlsx extends FileSaverParent {

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Data");

    public TakeFromDbMakeXlsx() {
    }

    @Override
    public void saveToFile(MapEntity mapEntity) {

        listOfLists = new ArrayList<>();

        mapEntity.getMapa().entrySet()
                .stream().map(entry -> entry.getValue().getSingleLine())
                .forEach(singleLine -> listOfLists.add(new ArrayList(singleLine)));

        Integer rowNum = 0;
        for(ArrayList<String> singleList : listOfLists)
        {
            Row row = sheet.createRow(rowNum);
            int cellNum = 0;

            for (String singleCell : singleList)
            {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(singleCell);
                cellNum++;
            }
            rowNum++;
        }

        try
        {
            FileOutputStream out = new FileOutputStream(new File("fromDb.xlsx"));
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
