package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.domain.division.Division;
import be.cm.batodama.parkshark.service.division.DivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/division")
public class DivisionController {

    DivisionService divisionService;
    private static final Logger logger = LoggerFactory.getLogger(DivisionController.class);
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;


    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DivisionDto createDivision(@RequestBody DivisionDto divisionDto) {
        Division division = divisionService.saveAndFlushDivision(DivisionMapper.mapToDivision(divisionDto));
        logger.info("division with name: " + division.getName() + ", for director with first name: " + division.getDirector().getFirstName() + " was successfully created");
        return DivisionMapper.mapToDivisionDto(division);
    }
}
