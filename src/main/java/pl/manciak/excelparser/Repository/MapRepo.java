package pl.manciak.excelparser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.manciak.excelparser.Entity.MapEntity;

import java.util.List;


@Repository
public interface MapRepo extends JpaRepository<MapEntity, Long> {


}
