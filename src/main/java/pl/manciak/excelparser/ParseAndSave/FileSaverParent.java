package pl.manciak.excelparser.ParseAndSave;

import org.springframework.stereotype.Service;
import pl.manciak.excelparser.Entity.MapEntity;

import java.io.IOException;
import java.util.ArrayList;


@Service
public class FileSaverParent {

    protected ArrayList<ArrayList<String>> listOfLists;

    public void saveToFile(MapEntity mapEntity) throws IOException {

    }

}
