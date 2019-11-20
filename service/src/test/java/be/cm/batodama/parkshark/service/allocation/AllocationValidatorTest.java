package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.parking.*;
import be.cm.batodama.parkshark.service.allocation.exception.InvalidAllocationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AllocationValidatorTest {

    private AllocationValidator allocationValidator = new AllocationValidator();
    private Member validMember;
    private ParkingLot validParkingLot;
    private Allocation validAllocation;


    @BeforeEach
    void setUp() {
        validMember = new Member("username", "", "", "", "","","","","coucou@hello.be", "", "1ABC123", "", LocalDateTime.now());
        validParkingLot = new ParkingLot("parkingName", ParkingLotCategory.ABOVE_GROUND, new Address("", "", new PostCode("", "")), 0, new ParkingLotContactPerson("", "coucou@hello.be", "", "", new Address("", "", new PostCode("", ""))), 0);
        validAllocation = new Allocation(validMember, validParkingLot, "1ABC123");

    }

    @Test
    void validate_givenNullMember_thenReturnFalse() {
        Allocation allocation = new Allocation(null, validParkingLot, "1ABC123");
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validate_givenNullParkingLot_thenReturnFalse() {
        Allocation allocation = new Allocation(validMember, null, "1ABC123");
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validate_givenNullLicensePlate_thenReturnFalse() {
        Allocation allocation = new Allocation(validMember, validParkingLot, null);
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validate_givenNonMatchingLicensePlate_thenReturnFalse() {
        Allocation allocation = new Allocation(validMember, validParkingLot, "9XYZ789");
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }
}