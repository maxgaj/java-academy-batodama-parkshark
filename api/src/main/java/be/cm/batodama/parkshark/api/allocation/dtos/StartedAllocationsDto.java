package be.cm.batodama.parkshark.api.allocation.dtos;

import be.cm.batodama.parkshark.domain.allocation.AllocationStatus;

public class StartedAllocationsDto implements AllocationDto{

    private long id;
    private AllocationMemberDto memberDto;
    private AllocationParkingLotDto parkingLotDto;
    private String licensePlate;
    private String status;
    private String startTime;

    public StartedAllocationsDto(long id, AllocationMemberDto memberDto, AllocationParkingLotDto parkingLotDto, String licensePlate, AllocationStatus status, String startTime) {
        this.id = id;
        this.memberDto = memberDto;
        this.parkingLotDto = parkingLotDto;
        this.licensePlate = licensePlate;
        this.status = status.toString();
        this.startTime = startTime;
        this.status = status.toString();
    }

    public long getId() {
        return id;
    }

    public AllocationMemberDto getMemberDto() {
        return memberDto;
    }

    public AllocationParkingLotDto getParkingLotDto() {
        return parkingLotDto;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getStatus() {
        return status;
    }

    public String getStartTime() {
        return startTime;
    }
}
