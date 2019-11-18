package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import be.cm.batodama.parkshark.service.parking.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;
    private final ParkingLotMapper parkingLotMapper;
    private static final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    public ParkingLotController(ParkingLotService parkingLotService, ParkingLotMapper parkingLotMapper) {
        this.parkingLotService = parkingLotService;
        this.parkingLotMapper = parkingLotMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotDto createDivision(@RequestBody ParkingLotDto parkingLotDto) {
        ParkingLot parkingLot = parkingLotService.save(parkingLotMapper.mapToParkingLot(parkingLotDto));
        logger.info("Parking lot with name: " + parkingLot.getParkingName() + " successfully created");
        return parkingLotMapper.mapToParkingLotDto(parkingLot);
    }
}
