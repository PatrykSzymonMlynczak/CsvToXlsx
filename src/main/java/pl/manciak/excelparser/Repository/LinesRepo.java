package pl.manciak.excelparser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.manciak.excelparser.Entity.LinesEntity;

@Repository
public interface LinesRepo extends JpaRepository<LinesEntity, Long> {
}
