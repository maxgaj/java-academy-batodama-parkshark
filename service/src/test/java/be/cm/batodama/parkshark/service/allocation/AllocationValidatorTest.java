package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.membershiplevel.MembershipLevel;
import be.cm.batodama.parkshark.domain.parking.*;
import be.cm.batodama.parkshark.service.allocation.exception.InvalidAllocationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AllocationValidatorTest {

    private AllocationValidator allocationValidator = new AllocationValidator();
    private Member validBronzeMember;
    private Member validGoldMember;
    private ParkingLot validParkingLot;
    private Allocation validAllocation;


    @BeforeEach
    void setUp() {
        validBronzeMember = new Member("username", "", "", "", "","","","","coucou@hello.be", "", "1ABC123", "", LocalDateTime.now(), MembershipLevel.BRONZE);
        validGoldMember = new Member("username", "", "", "", "","","","","coucou@hello.be", "", "1ABC123", "", LocalDateTime.now(), MembershipLevel.GOLD);
        validParkingLot = new ParkingLot("parkingName", ParkingLotCategory.ABOVE_GROUND, new Address("", "", new PostCode("", "")), 100, new ParkingLotContactPerson("", "coucou@hello.be", "", "", new Address("", "", new PostCode("", ""))), 0);
        validAllocation = new Allocation(validBronzeMember, validParkingLot, "1ABC123");

    }

    @Test
    void validate_givenNullMember_thenThrowException() {
        Allocation allocation = new Allocation(null, validParkingLot, "1ABC123");
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validate_givenNullParkingLot_thenThrowException() {
        Allocation allocation = new Allocation(validBronzeMember, null, "1ABC123");
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validate_givenNullLicensePlate_thenThrowException() {
        Allocation allocation = new Allocation(validBronzeMember, validParkingLot, null);
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validate_givenNonMatchingLicensePlateAndMemberShipLevelBronze_thenThrowException() {
        Allocation allocation = new Allocation(validBronzeMember, validParkingLot, "9XYZ789");
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validate_givenNonMatchingLicensePlateAndMemberShipLevelGold_thenThrowException() {
        Allocation allocation = new Allocation(validGoldMember, validParkingLot, "9XYZ789");
        org.junit.jupiter.api.Assertions.assertAll(()->allocationValidator.validate(allocation));
    }

    @Test
    void validate_givenFullParking_thenThrowException() {
        ParkingLot fullParkingLot = new ParkingLot("parkingName", ParkingLotCategory.ABOVE_GROUND, new Address("", "", new PostCode("", "")), 0, new ParkingLotContactPerson("", "coucou@hello.be", "", "", new Address("", "", new PostCode("", ""))), 0);
        Allocation allocation = new Allocation(validGoldMember, fullParkingLot, "9XYZ789");
        Assertions.assertThatThrownBy(() ->allocationValidator.validate(allocation)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validateToStop_givenNull_thenThrowsException() {
        Allocation allocation = null;
        Assertions.assertThatThrownBy(() ->allocationValidator.validateToStop(allocation, validBronzeMember)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validateToStop_givenNullMember_thenThrowsException() {
        Assertions.assertThatThrownBy(() ->allocationValidator.validateToStop(validAllocation, null)).isInstanceOf(InvalidAllocationException.class);
    }

    @Test
    void validateToStop_givenStoppedException_thenThrowsException() {
        validAllocation.setStopTime(LocalDateTime.now());
        Assertions.assertThatThrownBy(() ->allocationValidator.validateToStop(validAllocation, validBronzeMember)).isInstanceOf(InvalidAllocationException.class);
    }
}