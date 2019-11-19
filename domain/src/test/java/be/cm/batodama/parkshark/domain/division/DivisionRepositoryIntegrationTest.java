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
    void makeADivisionAndSaveToTheRepositoryAndConfirmDataInsideRepositoryIsEqualToInputData() {
        // first part of the test create a division and save in the repository
        Division division1 = new Division("Name", "Original Name", new Director("Seymour", "Skinner"));
        divisionRepository.save(division1);

        // second part of the test get first division out of the repository through the get all method
        Division division2 = divisionRepository.findAll().get(0);

        Assertions.assertEquals("Name",division2.getName());
        Assertions.assertEquals("Original Name",division2.getOriginalName());
        Assertions.assertEquals("Seymour",division2.getDirector().getFirstName());
        Assertions.assertEquals("Skinner",division2.getDirector().getLastName());
    }

    @Test
    void getOneDivisionOutOfTheRepositoryByID() {
        Division division1 = new Division("Name", "Original Name", new Director("Seymour", "Skinner"));
        divisionRepository.save(division1);

        Division division2 = divisionRepository.findAll().get(0);

        Division division3 = divisionRepository.getOne(division2.getId());
        Assertions.assertTrue(division2.equals(division3));
    }
}