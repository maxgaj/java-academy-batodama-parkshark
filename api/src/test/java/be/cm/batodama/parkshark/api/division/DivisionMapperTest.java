package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.domain.division.Director;
import be.cm.batodama.parkshark.domain.division.Division;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionMapperTest {

    @Test
    void givenDivisionDto_whenMappingToDivision_thenAllFieldsAreEqual() {
        DivisionDto divisionDto = new DivisionDto("Name",
                "Original Name",
                "Seymour",
                "Skinner",
                null);
        Division division = DivisionMapper.mapToDivision(divisionDto);
        Assertions.assertEquals(division.getName(), divisionDto.name);
        Assertions.assertEquals(division.getOriginalName(), divisionDto.originalName);
        Assertions.assertEquals(division.getDirector().getFirstName(), divisionDto.firstName);
        Assertions.assertEquals(division.getDirector().getLastName(), divisionDto.lastName);
        Assertions.assertEquals(division.getParent(), divisionDto.parent);
    }

    @Test
    void givenDivision_whenMappingToDivisionDto_thenAllFieldsAreEqual() {
        Division division = new Division("Name",
                "Original Name",
                new Director("Seymour", "Skinner"),
                null);
        DivisionDto divisionDto = DivisionMapper.mapToDivisionDto(division);
        Assertions.assertEquals(division.getName(), divisionDto.name);
        Assertions.assertEquals(division.getOriginalName(), divisionDto.originalName);
        Assertions.assertEquals(division.getDirector().getFirstName(), divisionDto.firstName);
        Assertions.assertEquals(division.getDirector().getLastName(), divisionDto.lastName);
        Assertions.assertEquals(division.getParent(), divisionDto.parent);
    }
}