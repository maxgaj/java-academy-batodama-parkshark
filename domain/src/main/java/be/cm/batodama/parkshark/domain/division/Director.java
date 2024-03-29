package be.cm.batodama.parkshark.domain.division;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Director {

    @Column(name = "DIRECTOR_FIRST_NAME")
    private String firstName;

    @Column(name = "DIRECTOR_LAST_NAME")
    private String lastName;

    public Director() {
    }

    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Director first name: " + firstName + " last name: " + lastName;
    }
}
