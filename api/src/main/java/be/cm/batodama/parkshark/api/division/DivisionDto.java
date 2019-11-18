package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.domain.division.Division;

public class DivisionDto {

    public long id;
    public final String name;
    public final String originalName;
    public final String firstName;
    public final String lastName;

    public DivisionDto(String name, String originalName, String firstName, String lastName) {
        this.name = name;
        this.originalName = originalName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public DivisionDto(Division division){
        this.name = division.getName();
        this.originalName = division.getOriginalName();
        this.firstName = division.getDirector().getFirstName();
        this.lastName = division.getDirector().getLastName();
        this.id = division.getId();
    }
}
