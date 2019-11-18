package be.cm.batodama.parkshark.api.member;


import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;
import be.cm.batodama.parkshark.service.member.MemberService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto createDivision(@RequestBody MemberDto memberDto) {
        Member member = memberService.saveAndFlushMember(MemberMapper.mapToMember(memberDto));
        logger.info("Member name: " + member.getFistName() + " " + member.getLastName() + " successfully created");
        return MemberMapper.mapToMemberDto(member);
    }


    @GetMapping(path = "") //GET Should the collection of members.
    public List<MemberDto> getAllMembers() {


        List<MemberDto> memberDtoList =
                memberService.getAllMembers()
                        .stream()
                            .map(MemberMapper::mapToMemberDto).collect(Collectors.toList());
        return memberDtoList;
    }
    @PostMapping(path = "post",consumes = "application/json", produces = "application/json")
    //POST create and add a new member to the collection
    public void addMember(@RequestBody MemberDto memberDto) {
        System.out.println("here in the post");
        memberService.addMember(MemberMapper.mapToMember(memberDto));
    }
    /*
    @GetMapping(path = "") //GET Should the collection of members.
    public MemberDto getMembers() {
        MemberDto memberDto =
                MemberMapper.mapToMemberDto(memberService.getMember());
        return memberDto;
    }

     */

}

