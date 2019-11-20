package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.parking.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ParkingLotMapper {

    @PersistenceContext
    private EntityManager entityManager;


    public ParkingLot mapToParkingLot(ParkingLotDto parkingLotDto) {
        return new ParkingLot(parkingLotDto.parkingName, ParkingLotCategory.valueOf(parkingLotDto.parkingCategory), new Address(parkingLotDto.address.streetName, parkingLotDto.address.streetNumber, new PostCode(parkingLotDto.address.postCode.postCode, parkingLotDto.address.postCode.postLabel)),
                parkingLotDto.parkingMaxSize, getParkingLotContactPerson(parkingLotDto.parkingLotContactPersonId), parkingLotDto.allocationPricePerHour, parkingLotDto.division);
    }


    public ParkingLotDtoToReturn mapToParkingLotDtoToReturn(ParkingLot parkingLot) {
        return new ParkingLotDtoToReturn(parkingLot.getId(), parkingLot.getParkingName(), parkingLot.getParkingCategory().toString(),
                new AddressDto(parkingLot.getAddress().getStreetName(), parkingLot.getAddress().getStreetNumber(),
                        new PostCodeDto(parkingLot.getAddress().getPostCode().getPostCode(), parkingLot.getAddress().getPostCode().getPostLabel())), parkingLot.getParkingMaxSize(),
                new ParkingLotContactPersonDto(parkingLot.getParkingLotContactPerson().getId(), parkingLot.getParkingLotContactPerson().getName(), parkingLot.getParkingLotContactPerson().getEmail(),
                        parkingLot.getParkingLotContactPerson().getPhoneNumber(), parkingLot.getParkingLotContactPerson().getTelephoneNumber(),
                        new AddressDto(parkingLot.getParkingLotContactPerson().getAddress().getStreetName(), parkingLot.getParkingLotContactPerson().getAddress().getStreetNumber(),
                                new PostCodeDto(parkingLot.getParkingLotContactPerson().getAddress().getPostCode().getPostCode(), parkingLot.getParkingLotContactPerson().getAddress().getPostCode().getPostLabel()))), parkingLot.getAllocationPricePerHour(),
                parkingLot.getDivision());
    }

    public ParkingLotContactPerson getParkingLotContactPerson(Long id) {
        return entityManager
                .createQuery("SELECT p FROM ParkingLotContactPerson p where p.id = :id", ParkingLotContactPerson.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

