package be.cm.batodama.parkshark.api.member;

import be.cm.batodama.parkshark.domain.member.Member;

public class MemberMapper {
    public static Member mapToMember(MemberDto memberDto) {
        return new Member(
                memberDto.username,
                memberDto.password,
                memberDto.firstName,
                memberDto.lastName,
                memberDto.streetAndNumber,
                memberDto.zipCode,
                memberDto.city,
                memberDto.country,
                memberDto.email,
                memberDto.phone,
                memberDto.licencePlateNumber,
                memberDto.licencePlateCountry,
                memberDto.registrationDate);
    }

    public static MemberDto mapToMemberDto(Member member) {
        return new MemberDto(
                member.getUsername(),
                member.getPassword(),
                member.getFistName(),
                member.getLastName(),
                member.getStreetAndNumber(),
                member.getZipCode(),
                member.getCity(),
                member.getCountry(),
                member.getEmail(),
                member.getPhone(),
                member.getLicencePlateNumber(),
                member.getLicencePlateCountry(),
                member.getRegistrationDate());
    }
}
