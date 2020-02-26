package pl.manciak.excelparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manciak.excelparser.Entity.LinesEntity;
import pl.manciak.excelparser.Entity.MapEntity;

import java.io.*;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;

@Service
public class ParseCsvAndSaveToDB {

    private DataService dataService;

    @Autowired
    public ParseCsvAndSaveToDB(DataService dataService ) {
        this.dataService = dataService;
    }

    public void save() throws IOException {

        LinesEntity linesEntity;
        ArrayList<String> list;

        HashMap<Long, LinesEntity> xlsMapped = new HashMap<>();
        MapEntity mapEntity = new MapEntity();

        //Stream to Read Csv file
        FileReader fileReader = new FileReader("usda_sample.csv");
        BufferedReader br = new BufferedReader(fileReader);

        //read first line
        String line = br.readLine();
        String array[];
        long mapKey = 0;

            while (line != null) {

                linesEntity = new LinesEntity(); // create a new LinesEntity for this loop execution
                list = new ArrayList<String>();
                array = line.split(",");
/*

                for (String cell : array) {

                    list.add(cell);
                }*/
                Collections.addAll(list, array);

                line = br.readLine();

                linesEntity.setSingleLine(new ArrayList<>(list));

                dataService.saveOne(linesEntity);

                xlsMapped.put(mapKey, linesEntity);

                mapKey++;
            }
           mapEntity.setMapa(xlsMapped);

        System.out.println(xlsMapped);

        dataService.save(mapEntity);

    }

}
