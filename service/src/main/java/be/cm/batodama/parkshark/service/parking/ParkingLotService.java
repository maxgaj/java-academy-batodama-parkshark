package be.cm.batodama.parkshark.service.parking;

import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import be.cm.batodama.parkshark.domain.parking.ParkingLotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
