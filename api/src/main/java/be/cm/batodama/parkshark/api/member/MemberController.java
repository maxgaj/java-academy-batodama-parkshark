package be.cm.batodama.parkshark.api.member;


import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;
import be.cm.batodama.parkshark.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public MemberDto createMember(@RequestBody MemberDto memberDto) {
        Member member = memberService.saveAndFlushMember(MemberMapper.mapToMember(memberDto));
        logger.info("Member name: " + member.getFistName() + " " + member.getLastName() + " successfully created");
        return MemberMapper.mapToMemberDto(member);
    }

    // for getting members, use no authentification
    @GetMapping //GET Should the collection of members.
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public List<SmallMemberDto> getAllMembers() {
        return memberService.getAllMembers()
                .stream()
                .map(MemberMapper::mapToSmallMemberDto)
                .collect(Collectors.toList());
    }


    @PostMapping(path = "post", consumes = "application/json", produces = "application/json")
    //POST create and add a new member to the collection
    public void addMember(@RequestBody MemberDto memberDto) {

        memberService.addMember(MemberMapper.mapToMember(memberDto));
    }

    // usage localhost:8080/members/get?ID=1
    // in postman give in params key ID and value 1 or ....
    @GetMapping(path = "get") //GET Should the collection of members.
    @ResponseBody
    public MemberDto getMemberById(@RequestParam(required = true) long ID) {
        System.out.println("here in the post");
        MemberDto memberDto =
                MemberMapper.mapToMemberDto(memberService.getMember(ID));
        return memberDto;
    }


}

