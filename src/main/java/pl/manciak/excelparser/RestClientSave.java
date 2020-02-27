package pl.manciak.excelparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.manciak.excelparser.ParseAndSave.ParseCsvAndSaveToDB;
import pl.manciak.excelparser.ParseAndSave.ParseXlsxAndSaveToDb;
import pl.manciak.excelparser.ParseAndSave.ParserParent;

import java.io.IOException;

@RestController
public class RestClientSave {

    private ParseCsvAndSaveToDB parseCsvAndSaveToDB;
    private ParseXlsxAndSaveToDb parseXlsxAndSaveToDb;

    @Autowired
    public RestClientSave(ParseCsvAndSaveToDB parseCsvAndSaveToDB, ParseXlsxAndSaveToDb parseXlsxAndSaveToDb) {
        this.parseCsvAndSaveToDB = parseCsvAndSaveToDB;
        this.parseXlsxAndSaveToDb = parseXlsxAndSaveToDb;
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
}
