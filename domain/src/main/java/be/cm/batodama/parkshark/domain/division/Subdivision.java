package be.cm.batodama.parkshark.domain.division;

import javax.persistence.Column;

public class Subdivision extends Division {

    @Column(name = "PARENT")
    private Division parent;

    public Subdivision(){}

    public Subdivision(String name, String originalName, Director director, Division parent) {
        super(name, originalName, director);
        this.parent = parent;
    }




}