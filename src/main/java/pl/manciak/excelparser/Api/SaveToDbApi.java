package pl.manciak.excelparser.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.manciak.excelparser.DataService;
import pl.manciak.excelparser.Entity.LinesEntity;
import pl.manciak.excelparser.Entity.MapEntity;
import pl.manciak.excelparser.ParseAndSave.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class SaveToDbApi {

    private ParseCsvAndSaveToDB parseCsvAndSaveToDB;
    private ParseXlsxAndSaveToDb parseXlsxAndSaveToDb;
    private TakeFromDbMakeCsv takeFromDbMakeCsv;
    private TakeFromDbMakeXlsx takeFromDbMakeXlsx;
    private DataService dataService;
    private FileSaverParent fileSaverParent;

    @Autowired
    public SaveToDbApi(ParseCsvAndSaveToDB parseCsvAndSaveToDB, ParseXlsxAndSaveToDb parseXlsxAndSaveToDb, TakeFromDbMakeCsv takeFromDbMakeCsv, TakeFromDbMakeXlsx takeFromDbMakeXlsx, DataService dataService, FileSaverParent fileSaverParent) {
        this.parseCsvAndSaveToDB = parseCsvAndSaveToDB;
        this.parseXlsxAndSaveToDb = parseXlsxAndSaveToDb;
        this.takeFromDbMakeCsv = takeFromDbMakeCsv;
        this.takeFromDbMakeXlsx = takeFromDbMakeXlsx;
        this.dataService = dataService;
        this.fileSaverParent = fileSaverParent;
    }

    private ParserParent setParser(ParseCsvAndSaveToDB parseCsvAndSaveToDB,
                                  ParseXlsxAndSaveToDb parseXlsxAndSaveToDb,
                                  String whichParser) {
        ParserParent parserParent = null;

        if (whichParser.equals("csv")) {
             return parserParent = parseCsvAndSaveToDB;
        }else if(whichParser.equals("xlsx")) {
             return parserParent = parseXlsxAndSaveToDb;
        }

        return parserParent;
    }

    private FileSaverParent setSaver(TakeFromDbMakeCsv takeFromDbMakeCsv,
                                     TakeFromDbMakeXlsx takeFromDbMakeXlsx,
                                     String whichSaver){
        FileSaverParent fileSaverParent = null;

        if (whichSaver.equals("csv")) {
            return fileSaverParent = takeFromDbMakeCsv;
        }else if(whichSaver.equals("xlsx")) {
            return fileSaverParent = takeFromDbMakeXlsx;
        }

        return fileSaverParent;

    }

    //@PostMapping("/saveToDb/{whichParser}")
    @GetMapping("/saveToDb/{whichParser}")
    public String save(@PathVariable String whichParser) throws IOException {

        ParserParent parserParent = setParser( parseCsvAndSaveToDB, parseXlsxAndSaveToDb, whichParser);
        parserParent.saveToDb();

        return "data saved";
    }


    @GetMapping("/get/{z}/{whichSaver}")
    public Iterator<Map.Entry<Long, LinesEntity>> getAll(@PathVariable String z, @PathVariable String whichSaver) throws IOException {

        FileSaverParent fileSaverParent = setSaver(takeFromDbMakeCsv, takeFromDbMakeXlsx, whichSaver);
        List<MapEntity> listOfmapEntity = dataService.findAllSaveToList();
        MapEntity mapEntity = listOfmapEntity.get(Integer.valueOf(z));

        fileSaverParent.saveToFile(mapEntity);

        return listOfmapEntity.get(Integer.valueOf(z)).getMapa().entrySet().iterator();
    }


}
