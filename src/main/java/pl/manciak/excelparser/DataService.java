package pl.manciak.excelparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manciak.excelparser.Entity.LinesEntity;
import pl.manciak.excelparser.Entity.MapEntity;
import pl.manciak.excelparser.Repository.LinesRepo;
import pl.manciak.excelparser.Repository.MapRepo;

@Service
public  class DataService {

    private MapRepo mapRepo;
    private LinesRepo linesRepo;

    @Autowired
    public DataService(MapRepo mapRepo, LinesRepo linesRepo) {
        this.mapRepo = mapRepo;
        this.linesRepo = linesRepo;
    }

    public MapEntity save(MapEntity file){
       return mapRepo.save(file);
    }

    public LinesEntity saveOne(LinesEntity linesEntity){
        return linesRepo.save(linesEntity);
    }
}
