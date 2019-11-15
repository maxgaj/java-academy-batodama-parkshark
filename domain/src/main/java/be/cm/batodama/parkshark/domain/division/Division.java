package be.cm.batodama.parkshark.domain.division;

import javax.persistence.*;

@Entity
@Table(name = "DIVISION")
public class Division {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "div_seq")
    @SequenceGenerator(sequenceName = "DIVISION_SEQUENCE", name = "DIV_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Embedded
    private Director director;

    public Division() {
    }

    public Division(String name, String originalName, Director director) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }


}
