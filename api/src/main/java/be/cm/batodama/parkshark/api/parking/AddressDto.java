package be.cm.batodama.parkshark.api.parking;

public class AddressDto {

    public String streetName;

    public String streetNumber;

    public PostCodeDto postCode;

    public AddressDto(String streetName, String streetNumber, PostCodeDto postCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
    }
}
