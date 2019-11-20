package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;
import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import be.cm.batodama.parkshark.domain.parking.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class AllocationCreator {

    private MemberRepository memberRepository;
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public AllocationCreator(MemberRepository memberRepository, ParkingLotRepository parkingLotRepository) {
        this.memberRepository = memberRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Allocation create(String username, long parkingId, String licensePlate){
        Member member = memberRepository.findOneByUsername(username);
        if (member == null){
            throw new IllegalArgumentException(format("Invalid username: username %s not found", username));
        }
        ParkingLot parkingLot = parkingLotRepository.findById(parkingId)
                    .orElseThrow(() -> new IllegalArgumentException(format("Invalid Parking lot Id: no parking lot for Id \"%s\"", parkingId)));
        return new Allocation(member, parkingLot, licensePlate);
    }
}
