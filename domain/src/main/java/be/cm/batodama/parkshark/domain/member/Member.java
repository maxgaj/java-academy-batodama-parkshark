package be.cm.batodama.parkshark.domain.member;

import be.cm.batodama.parkshark.domain.base_user.BaseUser;
import be.cm.batodama.parkshark.domain.base_user.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Member extends BaseUser {
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQUENCE")
//    @SequenceGenerator(sequenceName = "MEMBER_SEQUENCE", name = "MEMBER_SEQUENCE", allocationSize = 1)
//    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "STREET_AND_NUMBER")
    private String streetAndNumber;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "LICENCE_PLATE_NUMBER")
    private String licencePlateNumber;
    @Column(name = "LICENCE_PLATE_COUNTRY")
    private String licencePlateCountry;
    @Column(name = "REGISTRATION_DATE",columnDefinition = "DATE")
    private LocalDateTime registrationDate;




    public Member() {
    }

    public Member(String username, String password, String firstName, String lastName, String streetAndNumber, String zipCode, String city, String country, String email,String phone, String licencePlateNumber, String licencePlateCountry, LocalDateTime registrationDate) {
        super(username, password, Role.ROLE_MEMBER);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return getId() == member.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return  getId() + ": " + firstName + " " + lastName;
    }


    public String getFistName() {
        return firstName;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public String getLicencePlateCountry() {
        return licencePlateCountry;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public String getLastName() {
        return lastName;
    }
}



