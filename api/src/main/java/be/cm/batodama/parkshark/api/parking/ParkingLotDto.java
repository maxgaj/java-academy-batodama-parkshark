package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.parking.ParkingLotCategory;

public class ParkingLotDto {

    public String parkingName;

    public String parkingCategory;

    public AddressDto address;

    public long parkingMaxSize;

    public long parkingLotContactPersonId;

    public long allocationPricePerHour;


    public ParkingLotDto(String parkingName, String parkingCategory, AddressDto address, long parkingMaxSize, long parkingLotContactPersonId, long allocationPricePerHour) {
        this.parkingName = parkingName;
        this.parkingCategory = parkingCategory;
        this.address = address;
        this.parkingMaxSize = parkingMaxSize;
        this.parkingLotContactPersonId = parkingLotContactPersonId;
        this.allocationPricePerHour = allocationPricePerHour;
    }
}
