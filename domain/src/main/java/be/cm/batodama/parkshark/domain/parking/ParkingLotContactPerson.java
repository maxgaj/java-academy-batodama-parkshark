package be.cm.batodama.parkshark.domain.parking;

import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.*;

@Entity
@Table(name = "PARKING_LOT_CONTACT_PERSON")
public class ParkingLotContactPerson {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARKING_LOT_CONTACT_PERSON_SEQUENCE")
    @SequenceGenerator(sequenceName = "PARKING_LOT_CONTACT_PERSON_SEQUENCE", name = "PARKING_LOT_CONTACT_PERSON_SEQUENCE", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "E_MAIL")
    private String eMail;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;

    @Embedded
    private Address address;

    public ParkingLotContactPerson() {
    }

    public ParkingLotContactPerson(String name, String eMail, String phoneNumber, String telephoneNumber, Address address) {
        this.name = name;
        if (!isValid(eMail)) { throw new IllegalArgumentException(eMail + " is not a valid e-mail");} else {
            this.eMail = eMail;
        }
        this.phoneNumber = phoneNumber;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Address getAddress() {
        return address;
    }


    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @Override
    public String toString() {
        return "Contact person: " + id + ", " + name + ", " + eMail + ", " + phoneNumber + ", " + telephoneNumber + ", " + address;
    }
}
