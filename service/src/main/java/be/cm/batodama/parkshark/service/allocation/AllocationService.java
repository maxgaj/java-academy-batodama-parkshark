package be.cm.batodama.parkshark.service.allocation;

import be.cm.batodama.parkshark.domain.allocation.Allocation;
import be.cm.batodama.parkshark.domain.allocation.AllocationRepository;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public Allocation stopParkingAllocation(long allocationId, String username) {
        Member member = getMemberByUsername(username);
        Allocation allocationToStop = getAllocationFromId(allocationId);
        allocationValidator.validateToStop(allocationToStop, member);
        allocationToStop.setStopTime(LocalDateTime.now());
        return allocationToStop;
    }

    private Allocation getAllocationFromId(long allocationId) {
        return allocationRepository.findById(allocationId)
                .orElseThrow(() -> new IllegalArgumentException(format("Invalid Allocation Id: no allocation for Id \"%s\"", allocationId)));
    }

    private Member getMemberByUsername(String username) {
        Member member = memberRepository.findOneByUsername(username);
        if (member == null) {
            throw new IllegalArgumentException(format("Invalid username: username %s not found", username));
        }
        return member;
    }

    public List<Allocation> filterAllocations(Long amountToShow, String status, String ordering, List<Allocation> allocation) {
        List<Allocation> allocationsToReturn = allocation;

        if (ordering.toUpperCase().equals("DESCENDING")) {
            Collections.reverse(allocationsToReturn);
        }
        if (!status.equals("")) {
            allocationsToReturn = allocationsToReturn.stream()
                    .filter(allocationDto -> allocationDto.getStatus().equals(status))
                    .collect(Collectors.toList());
        }
        if (amountToShow != null) {
            allocationsToReturn = allocationsToReturn.stream()
                    .limit(amountToShow)
                    .collect(Collectors.toList());
        }
        return allocationsToReturn;
    }
}
