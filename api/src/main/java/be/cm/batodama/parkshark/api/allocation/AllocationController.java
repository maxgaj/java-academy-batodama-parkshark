package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.api.allocation.dtos.StartedAllocationsDto;
import be.cm.batodama.parkshark.api.allocation.dtos.StoppedAllocationDto;
import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.allocation.AllocationRepository;
import be.cm.batodama.parkshark.service.allocation.AllocationCreator;
import be.cm.batodama.parkshark.service.allocation.AllocationService;
import be.cm.batodama.parkshark.service.allocation.AllocationValidator;
import be.cm.batodama.parkshark.service.allocation.exception.InvalidAllocationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Api(tags = "Parking spots allocations")
@RestController
@RequestMapping("/allocations")
public class AllocationController {
    private static final Logger logger = LoggerFactory.getLogger(AllocationController.class);
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    private AllocationRepository allocationRepository;
    private AllocationCreator allocationCreator;
    private AllocationValidator allocationValidator;
    private AllocationMapper allocationMapper;
    private AllocationService allocationService;

    @Autowired
    public AllocationController(AllocationRepository allocationRepository,
                                AllocationCreator allocationCreator,
                                AllocationValidator allocationValidator,
                                AllocationMapper allocationMapper,
                                AllocationService allocationService) {
        this.allocationRepository = allocationRepository;
        this.allocationCreator = allocationCreator;
        this.allocationValidator = allocationValidator;
        this.allocationMapper = allocationMapper;
        this.allocationService = allocationService;
    }

    @ApiOperation(value = "Starts Parking spot allocation")
    @PostMapping(params = {"parkingId", "licensePlate"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    public StartedAllocationsDto startAllocation(@RequestParam String parkingId, @RequestParam String licensePlate) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Allocation allocation = allocationCreator.create(username, parkingId, licensePlate);
        allocationValidator.validate(allocation);
        Allocation savedAllocation = allocationRepository.saveAndFlush(allocation);
        return allocationMapper.mapToStartedAllocationDto(savedAllocation);
    }

    @ApiOperation(value = "Stops Parking Lot Allocation")
    @PutMapping(params = {"allocationId"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    public StoppedAllocationDto stopAllocation(@RequestParam String allocationId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Allocation stoppedAllocation = allocationService.stopParkingAllocation(allocationId, username);
        return allocationMapper.mapToStoppedAllocationDto(stoppedAllocation);
    }

    @ExceptionHandler({IllegalArgumentException.class, InvalidAllocationException.class})
    private void illegalArgumentExceptionHandler(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
        logger.error("AllocationController: " + ex.getMessage());
    }
}

