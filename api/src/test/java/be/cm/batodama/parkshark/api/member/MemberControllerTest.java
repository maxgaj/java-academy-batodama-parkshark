package be.cm.batodama.parkshark.api.member;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import be.cm.batodama.parkshark.ApiTestApplication;
import be.cm.batodama.parkshark.domain.member.Member;
import be.cm.batodama.parkshark.domain.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiTestApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class MemberControllerTest {

    @Resource
    private MemberController memberController;


    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(authorities = "ROLE_MANAGER")
    @Test
    void takeFirstMemberOutOfRepositoryDataEqualsMemberparkshark() throws Exception {
        mockMvc.perform(get("/members").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/members").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("member")))
                .andExpect(content().string(containsString("parkshark")));


//        SmallMemberDto smallMemberDto = memberController.getAllMembers().get(0);
//        Assertions.assertEquals(smallMemberDto.firstName, "member");
//        Assertions.assertEquals(smallMemberDto.lastName, "parkshark");
//        Assertions.assertEquals(smallMemberDto.id, 1);
//        Assertions.assertEquals(smallMemberDto.licencePlateNumber, "1ABC123");
//        Assertions.assertTrue(smallMemberDto.registrationDate.isBefore(LocalDateTime.now()));
//        Assertions.assertTrue(smallMemberDto.email.contains("local.be"));

    }
}
/*
@Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void whenGettingAllParkingLot_thenReturnedAllParkingLot() throws Exception {
        mockMvc.perform(get("/parkingLots")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
 */