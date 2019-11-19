package be.cm.batodama.parkshark.domain.division;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class DivisionRepositoryIntegrationTest {

    @Resource
    private DivisionRepository divisionRepository;

    @Test
    void trueIsTrue() {
        Assertions.assertTrue(true);
    }

    @Test
    void name() {
        Division division1 = new Division("Name", "Original Name", new Director("Seymour", "Skinner"),null);
        divisionRepository.save(division1);

        Division division2 = divisionRepository.findAll().get(0);
        Assertions.assertEquals("Name",division2.getName());

    }
}