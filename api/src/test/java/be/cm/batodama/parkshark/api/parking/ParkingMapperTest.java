package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.ApiTestApplication;
import be.cm.batodama.parkshark.api.division.DivisionDto;
import be.cm.batodama.parkshark.api.division.DivisionMapper;
import be.cm.batodama.parkshark.domain.division.Director;
import be.cm.batodama.parkshark.domain.division.Division;
import be.cm.batodama.parkshark.domain.parking.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

class ParkingMapperTest {

    @Test
    void givenParkingLot_whenMappingToParkingLotDto_thenAllFieldsAreEqual() {

        ParkingLotMapper parkingLotMapper = new ParkingLotMapper();

        ParkingLot parkingLot =
                new ParkingLot("Test", ParkingLotCategory.UNDERGROUND, new Address("Street Test", "1",
                        new PostCode("Post Test", "Leuven")), 50, new ParkingLotContactPerson("Niels", "niels@myemail.com", "484848484", "011848532",
                            new Address("Street Test", "1",
                                new PostCode("Post Test", "Leuven"))), 100);

        ParkingLotDtoToReturn parkingLotDtoToReturn = parkingLotMapper.mapToParkingLotDtoToReturn(parkingLot);
        Assertions.assertEquals(parkingLotDtoToReturn.id, parkingLot.getId());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingName, parkingLot.getParkingName());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingCategory, parkingLot.getParkingCategory());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingMaxSize, parkingLot.getParkingMaxSize());
        Assertions.assertEquals(parkingLotDtoToReturn.address.streetName, parkingLot.getAddress().getStreetName());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.name, parkingLot.getParkingLotContactPerson().getName());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.phoneNumber, parkingLot.getParkingLotContactPerson().getPhoneNumber());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.eMail, parkingLot.getParkingLotContactPerson().geteMail());
        Assertions.assertEquals(parkingLotDtoToReturn.parkingLotContactPersonDto.telephoneNumber, parkingLot.getParkingLotContactPerson().getTelephoneNumber());
    }
}