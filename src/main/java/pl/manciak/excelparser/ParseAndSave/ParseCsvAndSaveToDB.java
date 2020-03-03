package pl.manciak.excelparser.ParseAndSave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manciak.excelparser.DataService;
import pl.manciak.excelparser.Entity.LinesEntity;

import java.io.*;

import java.util.ArrayList;

import java.util.Collections;

@Service
public class ParseCsvAndSaveToDB extends ParserParent{

    @Autowired
    public ParseCsvAndSaveToDB(DataService dataService) {
        super(dataService);
    }

    public void saveToDb() throws IOException {

        //Stream to Read Csv file
        FileReader fileReader = new FileReader("usda_sample.csv");
        BufferedReader br = new BufferedReader(fileReader);

        //read first line
        String line = br.readLine();



        while (line != null) {

            linesEntity = new LinesEntity(); // create a new LinesEntity for this loop execution
            list = new ArrayList<>();
            Collections.addAll(list, line.split(","));
            line = br.readLine();
            linesEntity.setSingleLine(new ArrayList<>(list));
            dataService.saveOne(linesEntity);
            xlsMapped.put(mapKey, linesEntity);

            mapKey++;
        }
        mapEntity.setMapa(xlsMapped);

        System.out.println(xlsMapped);

        dataService.save(mapEntity);

        fileReader.close();
        br.close();

    }

}