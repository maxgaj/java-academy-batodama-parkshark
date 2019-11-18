package be.cm.batodama.parkshark.domain.base_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {

    @Query("SELECT u FROM BASE_USER u WHERE u.USERNAME=(:uUsername) AND u.PASSWORD=(:uPassword)")
    List<BaseUser> findByUsernameAndPassword(@Param("uUsername") String username, @Param("uPassword") String Password);
}
