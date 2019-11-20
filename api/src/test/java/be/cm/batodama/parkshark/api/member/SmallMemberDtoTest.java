package be.cm.batodama.parkshark.api.member;

import be.cm.batodama.parkshark.ApiTestApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

class SmallMemberDtoTest {

    @Test
    void makeMemberDtoTestFieldsEqualsConstructorParameters() {
        SmallMemberDto smallMemberDto = new SmallMemberDto(
                3, "daddy", "doebi", "kra235", "daddy@doebi.heaven", LocalDateTime.now());
        Assertions.assertEquals(smallMemberDto.id, 3);
        Assertions.assertTrue(smallMemberDto.email.contains("doebi.heaven"));
        Assertions.assertEquals(smallMemberDto.firstName, "daddy");
        Assertions.assertEquals(smallMemberDto.lastName, "doebi");
        Assertions.assertEquals(smallMemberDto.licencePlateNumber, "kra235");
        Assertions.assertTrue(smallMemberDto.registrationDate.isBefore(LocalDateTime.now()));
    }

}