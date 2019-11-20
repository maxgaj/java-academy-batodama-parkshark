package be.cm.batodama.parkshark.api.member;

import be.cm.batodama.parkshark.domain.member.Member;

import java.time.LocalDateTime;


public class SmallMemberDto {

    public final long id;
    public final String firstName;
    public final String lastName;
    public final String licencePlateNumber;
    public final String email;
    public final LocalDateTime registrationDate;


    public SmallMemberDto(long id, String firstName, String lastName, String licencePlateNumber,
                          String email, LocalDateTime registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licencePlateNumber = licencePlateNumber;
        this.email = email;
        this.registrationDate = registrationDate;
    }
}
