package pl.manciak.excelparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.manciak.excelparser.Entity.LinesEntity;
import pl.manciak.excelparser.Entity.MapEntity;
import pl.manciak.excelparser.ParseAndSave.ParseCsvAndSaveToDB;
import pl.manciak.excelparser.ParseAndSave.ParseXlsxAndSaveToDb;
import pl.manciak.excelparser.ParseAndSave.ParserParent;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class RestClient {

    private ParseCsvAndSaveToDB parseCsvAndSaveToDB;
    private ParseXlsxAndSaveToDb parseXlsxAndSaveToDb;
    private DataService dataService;


    @Autowired
    public RestClient(ParseCsvAndSaveToDB parseCsvAndSaveToDB, ParseXlsxAndSaveToDb parseXlsxAndSaveToDb, DataService dataService) {
        this.parseCsvAndSaveToDB = parseCsvAndSaveToDB;
        this.parseXlsxAndSaveToDb = parseXlsxAndSaveToDb;
        this.dataService = dataService;
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

    @GetMapping("/save/{whichParser}")
    public String save(@PathVariable String whichParser) throws IOException {

        ParserParent parent = setParser( parseCsvAndSaveToDB, parseXlsxAndSaveToDb, whichParser);
        parent.save();

        return "data saved";
    }

    @GetMapping("/get/")///{y}/{z}   @PathVariable String x, @PathVariable String y, @PathVariable String z
    public Iterator<Map.Entry<Long, LinesEntity>> getAll(){
        List<MapEntity> mapEntity = dataService.findAllSaveToList();

        mapEntity.get(0).getMapa().entrySet()
                .stream().map(x -> x.getValue()).map(y -> y.getSingleLine()).forEach(System.out::println);

        return mapEntity.get(0).getMapa().entrySet().iterator();
    }

}
