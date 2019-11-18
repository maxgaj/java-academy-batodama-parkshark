package be.cm.batodama.parkshark.domain.parking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    @Override
    <S extends ParkingLot> S save(S entity);

}
