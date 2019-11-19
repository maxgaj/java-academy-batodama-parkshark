package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.api.allocation.dtos.StartedAllocationsDto;
import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.parking.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AllocationMapperTest {

    private AllocationMapper allocationMapper = new AllocationMapper();
    private Member validMember;
    private ParkingLot validParkingLot;
    private Allocation validAllocation;

    @BeforeEach
    void setUp() {
        validMember = new Member("username", "", "", "", "","","","","", "", "", "", LocalDateTime.now());
        validParkingLot = new ParkingLot("parkingName", ParkingLotCategory.ABOVE_GROUND, new Address("", "", new PostCode("", "")), 0, new ParkingLotContactPerson("", "", "", "", new Address("", "", new PostCode("", ""))), 0);
        validAllocation = new Allocation(validMember, validParkingLot, "1ABC123");
    }

    @Test
    void mapToStartedAllocationDto_givenValidAllocation_thenReturnCorrectDto() {
        StartedAllocationsDto dto = allocationMapper.mapToStartedAllocationDto(validAllocation);
        Assertions.assertThat(dto.getId()).isEqualTo(0);
        Assertions.assertThat(dto.getLicensePlate()).isEqualTo("1ABC123");
        Assertions.assertThat(dto.getStartTime()).isNotNull();
        Assertions.assertThat(dto.getMemberDto().getId()).isEqualTo(0);
        Assertions.assertThat(dto.getMemberDto().getUsername()).isEqualTo("username");
        Assertions.assertThat(dto.getParkingLotDto().getId()).isEqualTo(0);
        Assertions.assertThat(dto.getParkingLotDto().getParkingName()).isEqualTo("parkingName");
    }
}