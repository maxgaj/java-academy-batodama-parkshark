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
                parkingLotDto.parkingMaxSize ,getParkingLotContactPerson(parkingLotDto.parkingLotContactPersonId), parkingLotDto.allocationPricePerHour);
    }


    public ParkingLotDto mapToParkingLotDto(ParkingLot parkingLot) {
        return new ParkingLotDto(parkingLot.getParkingName(), parkingLot.getParkingCategory(),
                new AddressDto(parkingLot.getAddress().getStreetName(), parkingLot.getAddress().getStreetNumber(),
                        new PostCodeDto(parkingLot.getAddress().getPostCode().getPostCode(), parkingLot.getAddress().getPostCode().getPostLabel())), parkingLot.getParkingMaxSize(),
                parkingLot.getParkingLotContactPerson().getId(), parkingLot.getAllocationPricePerHour());
    }

    private ParkingLotContactPerson getParkingLotContactPerson(Long id){
            return entityManager
                    .createQuery("SELECT p FROM ParkingLotContactPerson p where p.id = :id", ParkingLotContactPerson.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

