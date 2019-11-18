package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.domain.division.Division;
import be.cm.batodama.parkshark.service.division.DivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/divisions")
public class DivisionController {

    private static final Logger logger = LoggerFactory.getLogger(DivisionController.class);
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public DivisionDto createDivision(@RequestBody DivisionDto divisionDto) {
        Division division = divisionService.save(DivisionMapper.mapToDivision(divisionDto));
        logger.info("Division with name: " + division.getName() + ", for director with first name: " + division.getDirector().getFirstName() + " successfully created");
        return DivisionMapper.mapToDivisionDto(division);
    }
}
