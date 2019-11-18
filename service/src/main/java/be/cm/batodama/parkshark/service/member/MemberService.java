package be.cm.batodama.parkshark.service.member;

import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
        private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Member saveAndFlushDivision(Member member){
        return memberRepository.saveAndFlush(member);
    }
}