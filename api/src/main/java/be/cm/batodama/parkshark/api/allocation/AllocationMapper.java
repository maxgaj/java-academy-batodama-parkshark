package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.api.allocation.dtos.AllocationMemberDto;
import be.cm.batodama.parkshark.api.allocation.dtos.AllocationParkingLotDto;
import be.cm.batodama.parkshark.api.allocation.dtos.StartedAllocationsDto;
import be.cm.batodama.parkshark.api.allocation.dtos.StoppedAllocationDto;
import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.allocation.AllocationStatus;
import org.springframework.stereotype.Component;

@Component
public class AllocationMapper {

    public StartedAllocationsDto mapToDto(Allocation allocation){
        if (allocation.getStatus() == AllocationStatus.ACTIVE){
            return mapToStartedAllocationDto(allocation);
        }
        return mapToStoppedAllocationDto(allocation);
    }

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
                allocation.getStatus(),
                allocation.getStartTime().toString()
                );
    }


    public StoppedAllocationDto mapToStoppedAllocationDto(Allocation stoppedAllocation) {
        return new StoppedAllocationDto(
                stoppedAllocation.getId(),
                new AllocationMemberDto(
                        stoppedAllocation.getMember().getId(),
                        stoppedAllocation.getMember().getUsername()),
                new AllocationParkingLotDto(
                        stoppedAllocation.getParkingLot().getId(),
                        stoppedAllocation.getParkingLot().getParkingName()),
                stoppedAllocation.getLicencePlateNumber(),
                stoppedAllocation.getStatus(),
                stoppedAllocation.getStartTime().toString(),
                stoppedAllocation.getStopTime().toString()
        );
    }
}
