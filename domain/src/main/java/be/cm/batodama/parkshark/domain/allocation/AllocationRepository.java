package be.cm.batodama.parkshark.domain.allocation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    @Override
    <S extends Allocation> S saveAndFlush(S s);
}
