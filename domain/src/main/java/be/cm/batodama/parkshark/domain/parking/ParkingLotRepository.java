package be.cm.batodama.parkshark.domain.parking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    @Override
    <S extends ParkingLot> S save(S entity);

    @Override
    List<ParkingLot> findAll();

    @Override
    Optional<ParkingLot> findById(Long aLong);
}
