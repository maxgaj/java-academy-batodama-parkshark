package be.cm.batodama.parkshark.domain.parking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    @Override
    <S extends ParkingLot> S save(S entity);

    @Override
    List<ParkingLot> findAll();

}
