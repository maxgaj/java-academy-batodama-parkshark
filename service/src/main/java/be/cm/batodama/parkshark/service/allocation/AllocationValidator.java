package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import org.springframework.stereotype.Service;

@Service
public class AllocationValidator {

    public boolean validate(Allocation allocation){
        return memberIsNotNull(allocation) &&
                parkingLotIsNotNull(allocation) &&
                licensePlateIsNotNull(allocation) &&
                licensePlateIsAccepted(allocation) &&
                parkingLotIsNotFull(allocation) &&
                startingTimeIsNotNull(allocation);
    }

    private boolean memberIsNotNull(Allocation allocation) {
        return allocation.getMember() != null;
    }

    private boolean parkingLotIsNotNull(Allocation allocation) {
        return allocation.getParkingLot() != null;
    }

    private boolean licensePlateIsNotNull(Allocation allocation) {
        return allocation.getLicencePlateNumber() != null;
    }

    private boolean licensePlateIsAccepted(Allocation allocation) {
        return allocation.getLicencePlateNumber().equals(allocation.getMember().getLicencePlateNumber());
        //TODO add level
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
