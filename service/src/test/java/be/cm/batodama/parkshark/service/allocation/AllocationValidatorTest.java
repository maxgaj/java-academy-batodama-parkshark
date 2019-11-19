package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.parking.*;
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
        validMember = new Member("username", "", "", "", "","","","","", "", "1ABC123", "", LocalDateTime.now());
        validParkingLot = new ParkingLot("parkingName", ParkingLotCategory.ABOVE_GROUND, new Address("", "", new PostCode("", "")), 0, new ParkingLotContactPerson("", "", "", "", new Address("", "", new PostCode("", ""))), 0);
        validAllocation = new Allocation(validMember, validParkingLot, "1ABC123");

    }

    @Test
    void validate_givenValidAllocation_thenReturnFalse() {
        Assertions.assertThat(allocationValidator.validate(validAllocation)).isTrue();
    }

    @Test
    void validate_givenNullMember_thenReturnFalse() {
        Allocation allocation = new Allocation(null, validParkingLot, "1ABC123");
        Assertions.assertThat(allocationValidator.validate(allocation)).isFalse();
    }

    @Test
    void validate_givenNullParkingLot_thenReturnFalse() {
        Allocation allocation = new Allocation(validMember, null, "1ABC123");
        Assertions.assertThat(allocationValidator.validate(allocation)).isFalse();
    }

    @Test
    void validate_givenNullLicensePlate_thenReturnFalse() {
        Allocation allocation = new Allocation(validMember, validParkingLot, null);
        Assertions.assertThat(allocationValidator.validate(allocation)).isFalse();
    }

    @Test
    void validate_givenNonMatchingLicensePlate_thenReturnFalse() {
        Allocation allocation = new Allocation(validMember, validParkingLot, "9XYZ789");
        Assertions.assertThat(allocationValidator.validate(allocation)).isFalse();
    }
}