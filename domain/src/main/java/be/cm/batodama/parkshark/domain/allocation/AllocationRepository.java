package be.cm.batodama.parkshark.domain.allocation;

import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    @Override
    <S extends Allocation> S saveAndFlush(S s);

    @Query("SELECT COUNT(a) FROM Allocation a WHERE a.parkingLot=:parkingLot AND a.status=:status")
    Long countAllByParkingAndStatus(@Param("parking") ParkingLot parking, @Param("status") AllocationStatus status);



}
