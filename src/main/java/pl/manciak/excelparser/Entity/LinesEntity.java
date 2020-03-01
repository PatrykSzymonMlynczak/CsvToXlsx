package pl.manciak.excelparser.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "single_line")
public class LinesEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> singleLine;


    public LinesEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getSingleLine() {
        return singleLine;
    }

    public void setSingleLine(List<String> singleLine) {
        this.singleLine = singleLine;
    }
}
