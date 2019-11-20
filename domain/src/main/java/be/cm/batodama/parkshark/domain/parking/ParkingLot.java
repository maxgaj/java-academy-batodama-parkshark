package be.cm.batodama.parkshark.domain.parking;

import be.cm.batodama.parkshark.domain.division.Division;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PARKING_LOT")
public class ParkingLot {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARKING_LOT_SEQUENCE")
    @SequenceGenerator(sequenceName = "PARKING_LOT_SEQUENCE", name = "PARKING_LOT_SEQUENCE", allocationSize = 1)
    private long id;

    @Column(name = "PARKING_NAME")
    private String parkingName;

    @Enumerated( EnumType.STRING)
    @Column(name = "PARKING_CATEGORY")
    private ParkingLotCategory parkingCategory;

    @Embedded
    private Address address;

    @Column(name = "PARKING_MAX_SIZE")
    private long parkingMaxSize;

    @OneToOne
    @JoinColumn(name="CONTACT_PERSON_ID")
    private ParkingLotContactPerson parkingLotContactPerson;

    @Column(name = "ALLOCATION_PRICE_PER_HOUR")
    private long allocationPricePerHour;

    @ManyToOne
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    public ParkingLot() {
    }

    public ParkingLot(String parkingName, ParkingLotCategory parkingCategory, Address address, long parkingMaxSize, ParkingLotContactPerson parkingLotContactPerson, long allocationPricePerHour, Division division) {
        this.parkingName = parkingName;
        this.parkingCategory = parkingCategory;
        this.address = address;
        this.parkingMaxSize = parkingMaxSize;
        this.parkingLotContactPerson = parkingLotContactPerson;
        this.allocationPricePerHour = allocationPricePerHour;
        this.division = division;
    }

    public long getId() {
        return id;
    }

    public String getParkingName() {
        return parkingName;
    }

    public ParkingLotCategory getParkingCategory() {
        return parkingCategory;
    }

    public Address getAddress() {
        return address;
    }

    public long getParkingMaxSize() {
        return parkingMaxSize;
    }

    public ParkingLotContactPerson getParkingLotContactPerson() {
        return parkingLotContactPerson;
    }

    public long getAllocationPricePerHour() {
        return allocationPricePerHour;
    }

    public Division getDivision() {
        return division;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Parking lot: " + id + ", " + parkingName + ", " + parkingCategory + ", " + parkingMaxSize + ", " + parkingLotContactPerson + ", " + address + ", " + allocationPricePerHour;
    }
}

