package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.division.Director;
import be.cm.batodama.parkshark.domain.division.Division;
import be.cm.batodama.parkshark.domain.parking.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingMapperTest {

    @Test
    void givenParkingLot_whenMappingToParkingLotDto_thenAllFieldsAreEqual() {
        ParkingLotMapper parkingLotMapper = new ParkingLotMapper();
        ParkingLot parkingLot =
                new ParkingLot("Test", ParkingLotCategory.UNDERGROUND, new Address("Street Test", "1",
                        new PostCode("Post Test", "Leuven")), 50, new ParkingLotContactPerson("Niels", "niels@myemail.com", "484848484", "011848532",
                        new Address("Street Test", "1",
                                new PostCode("Post Test", "Leuven"))), 100, new Division("Name", "Original Name", new Director("Seymour", "Skinner"), null));

        ParkingLotDtoToReturn parkingLotDtoToReturn = parkingLotMapper.mapToParkingLotDtoToReturn(parkingLot);

        Assertions.assertEquals(parkingLotDtoToReturn.id, parkingLot.getId());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingName, parkingLot.getParkingName());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingCategory, parkingLot.getParkingCategory().toString());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingMaxSize, parkingLot.getParkingMaxSize());
        Assertions.assertEquals(parkingLotDtoToReturn.address.streetName, parkingLot.getAddress().getStreetName());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.name, parkingLot.getParkingLotContactPerson().getName());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.phoneNumber, parkingLot.getParkingLotContactPerson().getPhoneNumber());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.eMail, parkingLot.getParkingLotContactPerson().getEmail());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.telephoneNumber, parkingLot.getParkingLotContactPerson().getTelephoneNumber());
        Assertions.assertEquals(parkingLotDtoToReturn.division, parkingLot.getDivision());

    }
}