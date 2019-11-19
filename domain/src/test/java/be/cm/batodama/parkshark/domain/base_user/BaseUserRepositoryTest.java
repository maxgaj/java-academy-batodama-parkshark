package be.cm.batodama.parkshark.domain.base_user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class BaseUserRepositoryTest {

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Test
    void findByUsernameAndPassword_givenInvalidData_returnEmptyList() {
        List<BaseUser> baseUsers = baseUserRepository.findByUsernameAndPassword("User", "1234");
        Assertions.assertThat(baseUsers).isEmpty();
    }

    @Test
    void findByUsernameAndPassword_givenValidData_returnValidBaseUser() {
        List<BaseUser> baseUsers = baseUserRepository.findByUsernameAndPassword("member", "1234");
        Assertions.assertThat(baseUsers).isNotEmpty();
    }
}