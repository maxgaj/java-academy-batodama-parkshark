package be.cm.batodama.parkshark.domain.allocation;

import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ALLOCATION")
public class Allocation {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALL_SEQUENCE")
    @SequenceGenerator(sequenceName = "ALLOCATION_SEQUENCE", name = "ALL_SEQUENCE", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @NotNull
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PARKING_LOT_ID")
    @NotNull
    private ParkingLot parkingLot;

    @Column(name = "LICENCE_PLATE_NUMBER")
    @NotNull
    private String licencePlateNumber;

    @Column(name = "START_TIME")
    @NotNull
    private LocalDateTime startTime;

    public Allocation() {
    }


    public Allocation(Member member, ParkingLot parkingLot, String licencePlateNumber) {
        this.member = member;
        this.parkingLot = parkingLot;
        this.licencePlateNumber = licencePlateNumber;
        this.startTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
