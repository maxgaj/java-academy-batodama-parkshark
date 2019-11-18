package be.cm.batodama.parkshark.api.member;

import java.time.LocalDateTime;

public class MemberDto {
    public final String username;
    public final String password;
    public final String firstName;
    public final String lastName;
    public final String streetAndNumber;
    public final String zipCode;
    public final String city;
    public final String country;
    public final String phone;
    public final String email;
    public final String licencePlateNumber;
    public final String licencePlateCountry;
    public final LocalDateTime registrationDate;

    public MemberDto(
            String username, String password,
            String firstName, String lastName,
            String streetAndNumber,
            String zipCode, String city,
            String country,
            String email, String phone,
            String licencePlateNumber, String licencePlateCountry,
            LocalDateTime registrationDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAndNumber = streetAndNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.licencePlateNumber = licencePlateNumber;
        this.licencePlateCountry = licencePlateCountry;
        this.registrationDate = registrationDate;
    }
}
