package be.cm.batodama.parkshark.service.division;

import be.cm.batodama.parkshark.domain.division.Division;
import be.cm.batodama.parkshark.domain.division.DivisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DivisionService {

    private DivisionRepository divisionRepository;

    public DivisionService(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    public Division save(Division division) {
        return divisionRepository.save(division);
    }

    public DivisionRepository getDivisionRepository() {
        return divisionRepository;
    }

    public Division getDivision(long id) {
        return divisionRepository.getOne(id);
    }
}
