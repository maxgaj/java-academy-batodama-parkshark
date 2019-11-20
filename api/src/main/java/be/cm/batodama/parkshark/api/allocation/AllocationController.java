package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.api.allocation.dtos.AllocationDto;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @ApiOperation(value="Get all parking spot allocation")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public List<AllocationDto> getAllAllocations(){
        List<AllocationDto> allocationDtos = allocationRepository
                .findAll()
                .stream()
                .filter(allocation -> allocation.getStartTime() != null)
                .sorted(Comparator.comparing(Allocation::getStartTime))
                .map(allocation -> allocationMapper.mapToStartedAllocationDto(allocation))
                .collect(Collectors.toList());
       return  allocationDtos;
    }

    @ApiOperation(value="Get all parking spot allocation filtered on amount to show")
    @GetMapping(params = {"amountToShow"},produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public List<AllocationDto> getAllAllocationsFilteredOnAmountToShow(){
        List<AllocationDto> allocationDtos = allocationRepository
                .findAll()
                .stream()
                .filter(allocation -> allocation.getStartTime() != null)
                .sorted(Comparator.comparing(Allocation::getStartTime))
                .limit(100)
                .map(allocation -> allocationMapper.mapToStartedAllocationDto(allocation))
                .collect(Collectors.toList());
        return  allocationDtos;
    }

    @ApiOperation(value="Get all parking spot allocation filtered on amount to show")
    @GetMapping(params = {"Status", "ordering"},produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public List<AllocationDto> getAllAllocationsFilteredOnStatusAscOrDesc(@RequestParam String status, @RequestParam String ordering){
        List<AllocationDto> allocationDtos = allocationRepository
                .findAll()
                .stream()
                .filter(allocation -> allocation.getStartTime() != null)
                .sorted(Comparator.comparing(Allocation::getStartTime))
                .map(allocation -> allocationMapper.mapToStartedAllocationDto(allocation))
                .collect(Collectors.toList());
        return  allocationDtos;
    }
}
