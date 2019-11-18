package be.cm.batodama.parkshark.domain.division;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DivisionRepository extends JpaRepository<Division, Long> {

    @Override
    <S extends Division> S saveAndFlush(S entity);

    @Override
    List<Division> findAll();
}
