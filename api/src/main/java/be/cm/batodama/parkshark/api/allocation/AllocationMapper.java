package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.api.allocation.dtos.AllocationMemberDto;
import be.cm.batodama.parkshark.api.allocation.dtos.AllocationParkingLotDto;
import be.cm.batodama.parkshark.api.allocation.dtos.StartedAllocationsDto;
import be.cm.batodama.parkshark.domain.allocation.Allocation;
import org.springframework.stereotype.Component;

@Component
public class AllocationMapper {

    public StartedAllocationsDto mapToStartedAllocationDto(Allocation allocation){
        return new StartedAllocationsDto(
                allocation.getId(),
                new AllocationMemberDto(
                        allocation.getMember().getId(),
                        allocation.getMember().getUsername()),
                new AllocationParkingLotDto(
                        allocation.getParkingLot().getId(),
                        allocation.getParkingLot().getParkingName()),
                allocation.getLicencePlateNumber(),
                allocation.getStartTime().toString()
                );
    }


}
