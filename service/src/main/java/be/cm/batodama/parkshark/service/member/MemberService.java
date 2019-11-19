package be.cm.batodama.parkshark.service.member;

import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {
        private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Member saveAndFlushMember(Member member){
        return memberRepository.saveAndFlush(member);
    }


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }




    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public Member getMember(long ID) {
        return memberRepository.getOne(ID);
    }
}