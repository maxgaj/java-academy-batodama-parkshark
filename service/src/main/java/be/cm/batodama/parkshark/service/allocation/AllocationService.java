package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.allocation.AllocationRepository;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
public class AllocationService {

    private MemberRepository memberRepository;
    private AllocationRepository allocationRepository;
    private AllocationValidator allocationValidator;

    public AllocationService(MemberRepository memberRepository, AllocationRepository allocationRepository, AllocationValidator allocationValidator) {
        this.memberRepository = memberRepository;
        this.allocationRepository = allocationRepository;
        this.allocationValidator = allocationValidator;
    }

    @Transactional
    public Allocation stopParkingAllocation(String allocationId, String username) {
        Member member = getMemberByUsername(username);
        Allocation allocationToStop = getAllocationFromId(allocationId);
        allocationValidator.validateToStop(allocationToStop, member);
        allocationToStop.setStopTime(LocalDateTime.now());
        return allocationToStop;
    }

    private Allocation getAllocationFromId(String allocationId) {
        Allocation allocation = null;
        try {
            allocation = allocationRepository.findById(Long.parseLong(allocationId))
                    .orElseThrow(() -> new IllegalArgumentException(format("Invalid Allocation Id: no allocation for Id \"%s\"", allocationId)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(format("Invalid Allocation Id: %s is not a valid number", allocationId));
        }
        return allocation;
    }

    private Member getMemberByUsername(String username) {
        Member member = memberRepository.findOneByUsername(username);
        if (member == null){
            throw new IllegalArgumentException(format("Invalid username: username %s not found", username));
        }
        return member;
    }
}
