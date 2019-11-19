package be.cm.batodama.parkshark.api.allocation.dtos;

import be.cm.batodama.parkshark.domain.allocation.Allocation;

public class StartedAllocationsDto {

    private long id;
    private AllocationMemberDto memberDto;
    private AllocationParkingLotDto parkingLotDto;
    private String licensePlate;
    private String startTime;

    public StartedAllocationsDto(long id, AllocationMemberDto memberDto, AllocationParkingLotDto parkingLotDto, String licensePlate, String startTime) {
        this.id = id;
        this.memberDto = memberDto;
        this.parkingLotDto = parkingLotDto;
        this.licensePlate = licensePlate;
        this.startTime = startTime;
    }
}
