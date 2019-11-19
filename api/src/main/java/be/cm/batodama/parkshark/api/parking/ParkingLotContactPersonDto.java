package be.cm.batodama.parkshark.api.parking;

public class ParkingLotContactPersonDto {

    public long id;

    public final String name;

    public final String eMail;

    public final String phoneNumber;

    public final String telephoneNumber;

    public final AddressDto addressDto;

    public ParkingLotContactPersonDto(long id, String name, String eMail, String phoneNumber, String telephoneNumber, AddressDto addressDto) {
        this.id = id;
        this.name = name;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.telephoneNumber = telephoneNumber;
        this.addressDto = addressDto;
    }
}
