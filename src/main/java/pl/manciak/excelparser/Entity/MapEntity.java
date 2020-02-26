package pl.manciak.excelparser.Entity;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "map_of_single_lists")
public class MapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(targetEntity= LinesEntity.class)
    @MapKeyClass(Long.class)
    private Map<Long, LinesEntity> mapa;

    public MapEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, LinesEntity> getMapa() {
        return mapa;
    }

    public void setMapa(Map<Long, LinesEntity> mapa) {
        this.mapa = mapa;
    }
}
