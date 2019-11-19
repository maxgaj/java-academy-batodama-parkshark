package be.cm.batodama.parkshark.domain.parking;

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

    @Column(name = "PARKING_CATEGORY")
    private String parkingCategory;

    @Embedded
    private Address address;

    @Column(name = "PARKING_MAX_SIZE")
    private long parkingMaxSize;

    @OneToOne
    @JoinColumn(name="CONTACT_PERSON_ID")
    private ParkingLotContactPerson parkingLotContactPerson;

    @Column(name = "ALLOCATION_PRICE_PER_HOUR")
    private long allocationPricePerHour;

    public ParkingLot() {
    }

    public ParkingLot(String parkingName, ParkingLotCategory parkingCategory, Address address, long parkingMaxSize, ParkingLotContactPerson parkingLotContactPerson, long allocationPricePerHour) {
        this.parkingName = parkingName;
        this.parkingCategory = parkingCategory.toString();
        this.address = address;
        this.parkingMaxSize = parkingMaxSize;
        this.parkingLotContactPerson = parkingLotContactPerson;
        this.allocationPricePerHour = allocationPricePerHour;
    }

    public String getParkingName() {
        return parkingName;
    }

    public String getParkingCategory() {
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
