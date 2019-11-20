package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.division.Division;

public class ParkingLotDtoToReturn {

    public long id;

    public final String parkingName;

    public final String parkingCategory;

    public final AddressDto address;

    public final long parkingMaxSize;

    public final ParkingLotContactPersonDto parkingLotContactPersonDto;

    public final long allocationPricePerHour;

    public final Division division;

    public ParkingLotDtoToReturn(long id, String parkingName, String parkingCategory, AddressDto address, long parkingMaxSize, ParkingLotContactPersonDto parkingLotContactPersonDto, long allocationPricePerHour, Division division) {
        this.id = id;
        this.parkingName = parkingName;
        this.parkingCategory = parkingCategory;
        this.address = address;
        this.parkingMaxSize = parkingMaxSize;
        this.parkingLotContactPersonDto = parkingLotContactPersonDto;
        this.allocationPricePerHour = allocationPricePerHour;
        this.division = division;

    }
}
