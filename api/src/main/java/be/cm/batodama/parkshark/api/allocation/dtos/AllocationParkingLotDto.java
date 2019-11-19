package be.cm.batodama.parkshark.api.allocation.dtos;

public class AllocationParkingLotDto {
    private long id;
    private long parkingName;

    public AllocationParkingLotDto(long id, long parkingName) {
        this.id = id;
        this.parkingName = parkingName;
    }
}
