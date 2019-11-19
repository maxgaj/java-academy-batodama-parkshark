package be.cm.batodama.parkshark.service.parking;

import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import be.cm.batodama.parkshark.domain.parking.ParkingLotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot save(ParkingLot parkingLot){
        return parkingLotRepository.save(parkingLot);
    }

    public List<ParkingLot> findAll(){
        return parkingLotRepository.findAll();
    }

    public ParkingLot findById(long id) throws Exception {
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(id);
        return parkingLot.orElseThrow(Exception::new);
    }

}
