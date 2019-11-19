package be.cm.batodama.parkshark.api.parking;

public class ParkingLotDtoToReturn {

    public long id;

    public final String parkingName;

    public final String parkingCategory;

    public final AddressDto address;

    public final long parkingMaxSize;

    public final ParkingLotContactPersonDto parkingLotContactPersonDto;

    public final long allocationPricePerHour;

    public ParkingLotDtoToReturn(long id, String parkingName, String parkingCategory, AddressDto address, long parkingMaxSize, ParkingLotContactPersonDto parkingLotContactPersonDto, long allocationPricePerHour) {
        this.id = id;
        this.parkingName = parkingName;
        this.parkingCategory = parkingCategory;
        this.address = address;
        this.parkingMaxSize = parkingMaxSize;
        this.parkingLotContactPersonDto = parkingLotContactPersonDto;
        this.allocationPricePerHour = allocationPricePerHour;
    }
}
