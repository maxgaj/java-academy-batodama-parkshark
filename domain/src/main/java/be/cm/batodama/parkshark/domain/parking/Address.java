package be.cm.batodama.parkshark.domain.parking;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {

    @Column(name = "STREET_NAME")
    private String streetName;

    @Column(name = "STREET_NUMBER")
    private String streetNumber;

    @Embedded
    private PostCode postCode;

    public Address() {
    }

    public Address(String streetName, String streetNumber, PostCode postCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public PostCode getPostCode() {
        return postCode;
    }

    @Override
    public String toString() {
        return "Address: " + streetName + " " + streetNumber + " " + postCode;
    }
}
