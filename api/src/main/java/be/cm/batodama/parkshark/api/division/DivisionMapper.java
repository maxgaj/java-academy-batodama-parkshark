package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.domain.division.Director;
import be.cm.batodama.parkshark.domain.division.Division;

public class DivisionMapper {

    public static Division mapToDivision(DivisionDto divisionDto){
        return new Division(divisionDto.name, divisionDto.originalName, new Director(divisionDto.firstName,divisionDto.lastName));
    }

    public static DivisionDto mapToDivisionDto(Division division){
        return new DivisionDto(division);
    }
}
