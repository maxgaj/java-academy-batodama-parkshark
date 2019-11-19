package be.cm.batodama.parkshark.api.member;

import be.cm.batodama.parkshark.domain.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MemberMapperTest {

    @Test
    void givenMemberDto_whenMappingToMember_thenAllFieldsAreEqual() {

        MemberDto memberDto = new MemberDto(
                "jc",
                "omg",
                "Jesus",
                "Chirst",
                "Heavenlystreet 69",
                "6666",
                "Bethleham",
                "Jerusalem",
                "jezus@heaven.hell",
                "0123456789",
                "987654321",
                "JZE",
                LocalDateTime.now());

        Member member = MemberMapper.mapToMember(memberDto);
        Assertions.assertEquals(member.getFistName(), memberDto.firstName);
        Assertions.assertEquals(member.getLastName(), memberDto.lastName);
        Assertions.assertEquals(member.getStreetAndNumber(), memberDto.streetAndNumber);
        Assertions.assertEquals(member.getZipCode(), memberDto.zipCode);
        Assertions.assertEquals(member.getCity(), memberDto.city);
        Assertions.assertEquals(member.getCountry(), memberDto.country);
        Assertions.assertEquals(member.getEmail(), memberDto.email);
        Assertions.assertEquals(member.getPhone(), memberDto.phone);
        Assertions.assertEquals(member.getLicencePlateNumber(), memberDto.licencePlateNumber);
        Assertions.assertEquals(member.getLicencePlateCountry(), memberDto.licencePlateCountry);
    }
    @Test
    void givenMember_whenMappingToMemberDto_thenAllFieldsAreEqual() {
        Member member = new Member(
                "jc",
                "omg",
                "Jesus",
                "Chirst",
                "Heavenlystreet 69",
                "6666",
                "Bethleham",
                "Jerusalem",
                "jezus@heaven.hell",
                "0123456789",
                "987654321",
                "JZE",
                LocalDateTime.now());


        MemberDto memberDto = MemberMapper.mapToMemberDto(member);
        Assertions.assertEquals(member.getFistName(), memberDto.firstName);
        Assertions.assertEquals(member.getLastName(), memberDto.lastName);
        Assertions.assertEquals(member.getStreetAndNumber(), memberDto.streetAndNumber);
        Assertions.assertEquals(member.getZipCode(), memberDto.zipCode);
        Assertions.assertEquals(member.getCity(), memberDto.city);
        Assertions.assertEquals(member.getCountry(), memberDto.country);
        Assertions.assertEquals(member.getEmail(), memberDto.email);
        Assertions.assertEquals(member.getPhone(), memberDto.phone);
        Assertions.assertEquals(member.getLicencePlateNumber(), memberDto.licencePlateNumber);
        Assertions.assertEquals(member.getLicencePlateCountry(), memberDto.licencePlateCountry);
    }


}
/*
{
	"lastName":"Doubbel",
	"firstName":"Danny",
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
