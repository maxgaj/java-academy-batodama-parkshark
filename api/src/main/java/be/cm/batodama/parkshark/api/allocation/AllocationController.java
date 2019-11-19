package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.api.allocation.dtos.StartedAllocationsDto;
import be.cm.batodama.parkshark.api.division.DivisionController;
import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.allocation.AllocationRepository;
import be.cm.batodama.parkshark.service.allocation.AllocationCreator;
import be.cm.batodama.parkshark.service.allocation.AllocationValidator;
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

    @Autowired
    public AllocationController(AllocationRepository allocationRepository,
                                AllocationCreator allocationCreator,
                                AllocationValidator allocationValidator,
                                AllocationMapper allocationMapper) {
        this.allocationRepository = allocationRepository;
        this.allocationCreator = allocationCreator;
        this.allocationValidator = allocationValidator;
        this.allocationMapper = allocationMapper;

    }

    @ApiOperation(value="Starts Parking spot allocation")
    @PostMapping(params = {"parkingId", "licensePlate"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    public StartedAllocationsDto startAllocation(@RequestParam String parkingId, @RequestParam String licensePlate){
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Allocation allocation = allocationCreator.create(username, parkingId, licensePlate);
        allocationValidator.validate(allocation);
        Allocation savedAllocation = allocationRepository.saveAndFlush(allocation);
        return allocationMapper.mapToStartedAllocationDto(savedAllocation);

    }
}
