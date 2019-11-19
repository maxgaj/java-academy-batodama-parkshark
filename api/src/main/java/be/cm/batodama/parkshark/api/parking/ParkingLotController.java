package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import be.cm.batodama.parkshark.service.parking.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingLotDtoToReturn> getAllParkingLots() {
        List<ParkingLot> parkingLots = parkingLotService.findAll();
        logger.info(parkingLots.size() + " where found");
        List<ParkingLotDtoToReturn> parkingLotDtos = parkingLots
                .stream()
                .map(parkingLotMapper::mapToParkingLotDtoToReturn)
                .collect(Collectors.toList());
        return parkingLotDtos;
    }

    @GetMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ParkingLotDtoToReturn getOneParkingLotById(@PathVariable long id) {
        try {
            ParkingLotDtoToReturn parkingLotDtoToReturn = parkingLotMapper.mapToParkingLotDtoToReturn(parkingLotService.findById(id));
            return parkingLotDtoToReturn;
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            return null;
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotDtoToReturn createParkingLot(@RequestBody ParkingLotDto parkingLotDto) {
        ParkingLot parkingLot = parkingLotService.save(parkingLotMapper.mapToParkingLot(parkingLotDto));
        logger.info("Parking lot with name: " + parkingLot.getParkingName() + " successfully created");
        return parkingLotMapper.mapToParkingLotDtoToReturn(parkingLot);
    }
}
