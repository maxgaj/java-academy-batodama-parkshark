package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import be.cm.batodama.parkshark.service.parking.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Api(tags = "Parking lots")
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

    @ApiOperation(value = "Get all parking lots")
    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public List<ParkingLotDtoToReturn> getAllParkingLots() {
        List<ParkingLot> parkingLots = parkingLotService.findAll();
        logger.info(parkingLots.size() + " where found");
        return parkingLots
                .stream()
                .map(parkingLotMapper::mapToParkingLotDtoToReturn)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get parking lot by id")
    @GetMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ParkingLotDtoToReturn getOneParkingLotById(@PathVariable long id) {
        return parkingLotMapper.mapToParkingLotDtoToReturn(parkingLotService.findById(id));
    }

    @ApiOperation(value = "Create parking lot")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ParkingLotDtoToReturn createParkingLot(@RequestBody ParkingLotDto parkingLotDto) {
        ParkingLot parkingLot = parkingLotService.save(parkingLotMapper.mapToParkingLot(parkingLotDto));
        logger.info("Parking lot with name: " + parkingLot.getParkingName() + " successfully created");
        return parkingLotMapper.mapToParkingLotDtoToReturn(parkingLot);
    }

    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class})
    private void exceptionHandler(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
        logger.error(ex.getMessage());
    }

}
