package be.cm.batodama.parkshark.api.allocation.dtos;

public class AllocationParkingLotDto {
    private long id;
    private String parkingName;

    public AllocationParkingLotDto(long id, String parkingName) {
        this.id = id;
        this.parkingName = parkingName;
    }

    public long getId() {
        return id;
    }

    public String getParkingName() {
        return parkingName;
    }
}
