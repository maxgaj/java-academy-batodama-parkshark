package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.domain.division.Division;
import be.cm.batodama.parkshark.service.division.DivisionService;
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
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Api(tags = "Divisions")
@RestController
@RequestMapping("/divisions")
public class DivisionController {

    private static final Logger logger = LoggerFactory.getLogger(DivisionController.class);
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @ApiOperation(value = "Create a division")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public DivisionDto createDivision(@RequestBody DivisionDto divisionDto) {
        Division division = divisionService.save(DivisionMapper.mapToDivision(divisionDto));
        logger.info("Division with name: " + division.getName() + ", for director with first name: " + division.getDirector().getFirstName() + " successfully created");
        return DivisionMapper.mapToDivisionDto(division);
    }

    @ApiOperation(value = "Get all divisions")
    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public List<DivisionDto> getOverviewOfAllDivisions() {
        return divisionService
                .getDivisionRepository()
                .findAll()
                .stream()
                .map(DivisionMapper::mapToDivisionDto)
                .collect(Collectors.toList());
    }


    // usage localhost:8080/divisions/get?ID=1
    // in postman give in params key ID and value 1 or ....
    @GetMapping(path = "get") //GET Should the collection of members.
    @ResponseBody
    public DivisionDto getDivision(@RequestParam(required = true) long ID) {
        System.out.println("here in the post");
        DivisionDto divisionDto =
                DivisionMapper.mapToDivisionDto(divisionService.getDivision(ID));
        return divisionDto;
    }


    @ExceptionHandler(ConstraintViolationException.class)
    private void constraintViolationExceptionHandler(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
        logger.error(ex.getMessage());
    }
}
