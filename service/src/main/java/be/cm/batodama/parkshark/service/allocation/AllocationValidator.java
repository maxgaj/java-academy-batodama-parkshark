package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.membershiplevel.MembershipLevel;
import be.cm.batodama.parkshark.service.allocation.exception.InvalidAllocationException;
import org.springframework.stereotype.Service;

@Service
public class AllocationValidator {

    public void validate(Allocation allocation){
        memberIsNotNull(allocation);
        parkingLotIsNotNull(allocation);
        licensePlateIsNotNull(allocation);
        licensePlateIsAccepted(allocation);
        parkingLotIsNotFull(allocation);
        startingTimeIsNotNull(allocation);
    }

    private void memberIsNotNull(Allocation allocation) {
        if (allocation.getMember() == null){
            throw new InvalidAllocationException("Member cannot be null");
        }
    }

    private void parkingLotIsNotNull(Allocation allocation) {
       if (allocation.getParkingLot() == null){
           throw new InvalidAllocationException("Parking Lot cannot be null");
       }
    }

    private void licensePlateIsNotNull(Allocation allocation) {
        if (allocation.getLicencePlateNumber() == null){
            throw new InvalidAllocationException("License plate cannot be null");
        }
    }

    private void licensePlateIsAccepted(Allocation allocation) {
        if (!allocation.getMember().getMembershipLevel().equals(MembershipLevel.GOLD)){
            if (!allocation.getLicencePlateNumber().equals(allocation.getMember().getLicencePlateNumber())) {
                throw new InvalidAllocationException("Provided License Plate is not authorize for this member");
            }
        }
    }

    private void parkingLotIsNotFull(Allocation allocation) {
//        if (allocation.getParkingLot().isFull()){
//            throw new InvalidAllocationException("This parking lot is full");
//        }
        //TODO
    }

    private void startingTimeIsNotNull(Allocation allocation) {
        if (allocation.getStartTime() == null){
            throw new InvalidAllocationException("Starting time cannot be null");
        }
    }



}
