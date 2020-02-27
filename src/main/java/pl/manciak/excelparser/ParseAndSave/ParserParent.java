package pl.manciak.excelparser.ParseAndSave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manciak.excelparser.DataService;
import pl.manciak.excelparser.Entity.LinesEntity;
import pl.manciak.excelparser.Entity.MapEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ParserParent {

    protected DataService dataService;
    protected LinesEntity linesEntity;
    protected ArrayList<String> list;

    protected HashMap<Long, LinesEntity> xlsMapped = new HashMap<>();
    protected MapEntity mapEntity = new MapEntity();
    long mapKey = 0;

    @Autowired
    public ParserParent(DataService dataService ) {
        this.dataService = dataService;
    }

    public void save() throws IOException {}

}
