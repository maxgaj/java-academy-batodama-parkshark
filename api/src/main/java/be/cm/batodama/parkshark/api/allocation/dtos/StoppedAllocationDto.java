package be.cm.batodama.parkshark.api.allocation.dtos;

import be.cm.batodama.parkshark.domain.allocation.AllocationStatus;

public class StoppedAllocationDto extends StartedAllocationsDto {

    private String stopTime;

    public StoppedAllocationDto(long id, AllocationMemberDto memberDto, AllocationParkingLotDto parkingLotDto, String licensePlate, AllocationStatus status, String startTime, String stopTime) {
        super(id, memberDto, parkingLotDto, licensePlate, status, startTime);
        this.stopTime = stopTime;
    }

    public String getStopTime() {
        return stopTime;
    }
}
