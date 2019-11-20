package be.cm.batodama.parkshark.api.allocation.dtos;

public class StartedAllocationsDto implements AllocationDto {

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

    public String getStartTime() {
        return startTime;
    }
}
