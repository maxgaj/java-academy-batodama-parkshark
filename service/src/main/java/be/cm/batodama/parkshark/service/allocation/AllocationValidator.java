package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import org.springframework.stereotype.Service;

@Service
public class AllocationValidator {

    public boolean validate(Allocation allocation){
        return memberIsNotNull(allocation) &&
                memberhasId(allocation) &&
                parkingLotIsNotNull(allocation) &&
                parkingLotHasId(allocation) &&
                licensePlateIsNotNull(allocation) &&
                licensePlateIsAccepted(allocation) &&
                parkingLotIsNotFull(allocation) &&
                startingTimeIsNotNull(allocation);
    }

    private boolean memberIsNotNull(Allocation allocation) {
        return allocation.getMember() != null;
    }

    private boolean memberhasId(Allocation allocation) {
        return allocation.getMember().getId() != 0;
    }

    private boolean parkingLotIsNotNull(Allocation allocation) {
        return allocation.getParkingLot() != null;
    }

    private boolean parkingLotHasId(Allocation allocation) {
        return true;
        //TODO
//        return allocation.getParkingLot().getId();
    }

    private boolean licensePlateIsNotNull(Allocation allocation) {
        return allocation.getLicencePlateNumber() != null;
    }

    private boolean licensePlateIsAccepted(Allocation allocation) {
    }

    private boolean parkingLotIsNotFull(Allocation allocation) {
//        return !allocation.getParkingLot().isFull();
        //TODO
        return true;
    }

    private boolean startingTimeIsNotNull(Allocation allocation) {
        return allocation.getStartTime() != null;
    }



}
