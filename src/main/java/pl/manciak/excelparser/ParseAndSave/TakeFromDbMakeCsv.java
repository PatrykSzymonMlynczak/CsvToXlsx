package pl.manciak.excelparser.ParseAndSave;

import org.springframework.stereotype.Service;
import pl.manciak.excelparser.Entity.MapEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
public class TakeFromDbMakeCsv extends FileSaverParent{

    String s = new String();
    File csvOutputFile = new File("fromDb.csv");
    PrintWriter printWriter = new PrintWriter(csvOutputFile);

    public TakeFromDbMakeCsv() throws FileNotFoundException {
    }

    @Override
    public void saveToFile(MapEntity mapEntity) throws IOException {

        listOfLists = new ArrayList<>();

        mapEntity.getMapa().entrySet()
                .stream().map(y -> y.getValue().getSingleLine())
                .forEach(y -> listOfLists.add(new ArrayList(y)));

        listOfLists.forEach(y -> {
            y.add("\n");
            String s = y.stream()
                        .collect(Collectors.joining(","));
            printWriter.write(s);
        });

        listOfLists.forEach(System.out::println);
        System.out.println("xlsx file written successfully on disk.");
        printWriter.close();


    }

}
