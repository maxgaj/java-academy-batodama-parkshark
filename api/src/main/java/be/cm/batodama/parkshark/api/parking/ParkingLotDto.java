package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.division.Division;

public class ParkingLotDto {

    public final String parkingName;

    public final String parkingCategory;

    public final AddressDto address;

    public final long parkingMaxSize;

    public final long parkingLotContactPersonId;

    public final long allocationPricePerHour;

    public final Division division;

    public ParkingLotDto(String parkingName, String parkingCategory, AddressDto address, long parkingMaxSize, long parkingLotContactPersonId, long allocationPricePerHour, Division division) {
        this.parkingName = parkingName;
        this.parkingCategory = parkingCategory;
        this.address = address;
        this.parkingMaxSize = parkingMaxSize;
        this.parkingLotContactPersonId = parkingLotContactPersonId;
        this.allocationPricePerHour = allocationPricePerHour;
        this.division = division;
    }
}
