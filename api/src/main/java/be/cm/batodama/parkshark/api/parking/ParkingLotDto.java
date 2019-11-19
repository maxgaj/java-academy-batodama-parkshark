package be.cm.batodama.parkshark.api.parking;

public class ParkingLotDto {

    public final String parkingName;

    public final String parkingCategory;

    public final AddressDto address;

    public final long parkingMaxSize;

    public final long parkingLotContactPersonId;

    public final long allocationPricePerHour;

    public ParkingLotDto(String parkingName, String parkingCategory, AddressDto address, long parkingMaxSize, long parkingLotContactPersonId, long allocationPricePerHour) {
        this.parkingName = parkingName;
        this.parkingCategory = parkingCategory;
        this.address = address;
        this.parkingMaxSize = parkingMaxSize;
        this.parkingLotContactPersonId = parkingLotContactPersonId;
        this.allocationPricePerHour = allocationPricePerHour;
    }
}
