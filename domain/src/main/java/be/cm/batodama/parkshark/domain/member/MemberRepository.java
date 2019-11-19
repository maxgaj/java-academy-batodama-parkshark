package be.cm.batodama.parkshark.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    @Override
    <S extends  Member> S saveAndFlush(S entity);

    @Override
    Member getOne(Long aLong);
}


