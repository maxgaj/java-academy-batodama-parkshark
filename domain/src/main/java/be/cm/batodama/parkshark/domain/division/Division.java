package be.cm.batodama.parkshark.domain.division;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DIVISION")
public class Division {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQUENCE")
    @SequenceGenerator(sequenceName = "DIVISION_SEQUENCE", name = "DIVISION_SEQUENCE", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Embedded
    private Director director;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Division parent;


    public Division() {
    }

    public Division(String name, String originalName, Director director,Division parent) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public Director getDirector() {
        return director;
    }

    public long getId() {
        return id;
    }

    public Division getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return id == division.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  id + ": " + name + " " + originalName + " " + director;
    }
}
