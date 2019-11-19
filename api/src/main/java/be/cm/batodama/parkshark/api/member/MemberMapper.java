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

    public static SmallMemberDto mapToSmallMemberDto(Member member){
        return new SmallMemberDto(
                member.getId(),
                member.getFistName(),
                member.getLastName(),
                member.getLicencePlateNumber(),
                member.getEmail(),
                member.getRegistrationDate());
    }
}

/*
// JSON TEST DATA :
{
	"id" : "10",
	"firstName": "Danny",
	"lastName":"Doubbel",
	"streetAndNumber":"street",
    "zipCode": "8900",
    "city":"Esen",
    "country":"Belgium",
    "email":"danny@doubbel.be",
    "phone":"0123456789",
    "licencePlateNumber":"kra235",
    "licencePlateCountry":"be",
    "registrationDate":"2016-05-28T17:39:44.937"
}
 */
