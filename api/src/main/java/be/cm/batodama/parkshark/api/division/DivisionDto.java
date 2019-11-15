package be.cm.batodama.parkshark.api.division;

public class DivisionDto {

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
}
