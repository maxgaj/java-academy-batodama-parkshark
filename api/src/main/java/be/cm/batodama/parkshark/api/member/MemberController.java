package be.cm.batodama.parkshark.api.member;


import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.service.member.MemberService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/member")
public class MemberController {
    MemberService memberService;
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
}

