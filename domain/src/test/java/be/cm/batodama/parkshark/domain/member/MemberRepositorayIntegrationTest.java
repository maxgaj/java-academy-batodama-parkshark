package be.cm.batodama.parkshark.domain.member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class MemberRepositorayIntegrationTest {
    @Resource
    private MemberRepository memberRepository;

    @Test
    void onCreateMemberInRepositoryCheckAllFieldnamesWhenGettingTheMemberOutOfTheReposisitory() {
        Member member1 =
                new Member(
                        "myUserName",
                        "myPassWord",
                        "myFirstName",
                        "myLastName",
                        "myStreetAndNumber",
                        "myZipCode",
                        "myCity",
                        "myCountry",
                        "myemail@heaven.hell",
                        "0471727341",
                        "kra235",
                        "belgium",
                        LocalDateTime.of(2019,11,19,23,59)
                );
        memberRepository.save(member1);

        Member member2 = memberRepository.findAll().get(0);
        Assertions.assertEquals("myFirstName",member2.getFistName());
        Assertions.assertEquals("myLastName",member2.getLastName());
        Assertions.assertEquals("myStreetAndNumber",member2.getStreetAndNumber());
        Assertions.assertEquals("myZipCode",member2.getZipCode());
        Assertions.assertEquals("myCity",member2.getCity());
        Assertions.assertEquals("myCountry",member2.getCountry());
        Assertions.assertTrue(member2.getEmail().contains("heaven.hell"));
        Assertions.assertEquals("0471727341",member2.getPhone());
        Assertions.assertEquals("kra235",member2.getLicencePlateNumber());
        Assertions.assertEquals("belgium",member2.getLicencePlateCountry());
        assertEquals(11L, member2.getRegistrationDate().getMonthValue());
        assertEquals(2019L, member2.getRegistrationDate().getYear());

    }
}
