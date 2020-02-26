package pl.manciak.excelparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RestClientSave {

    private ParseCsvAndSaveToDB parseCsvAndSaveToDB;


    @Autowired
    public RestClientSave(ParseCsvAndSaveToDB parseCsvAndSaveToDB) {
        this.parseCsvAndSaveToDB = parseCsvAndSaveToDB;

    }

    @GetMapping("/save")
    public String save() throws IOException {
        parseCsvAndSaveToDB.save();
        return "data saved";
    }

}
