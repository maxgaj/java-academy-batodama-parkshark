package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.TestApiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestApiApplication.class)
@AutoConfigureMockMvc
class DivisionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private String validDivisionJson;

    @BeforeEach
    void setUp() {
        validDivisionJson = asJsonString(new DivisionDto(
                "Name",
                "Original Name",
                "Seymour",
                "Skinner"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void givenDivisionDto_whenCreatingDivision_thenReturnedDivisionDtoEqualsOriginal() throws Exception {
        mockMvc.perform(post("/divisions")
                .content(validDivisionJson)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

//    @Test
//    void givenValidDivisionJson_whenCreatingAndGettinAllDivision_thenReturnCollectionWithProvidedValidDivision() throws Exception {
//        MvcResult result = mockMvc.perform(post("/divisions")
//                .content(validDivisionJson)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//
//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}