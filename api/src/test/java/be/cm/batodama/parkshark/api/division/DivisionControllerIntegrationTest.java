package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.ApiTestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiTestApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DivisionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private String validDivisionJson;
    private String validDivisionJson2;
    private String invalidDivisionJson;
    private String subdivisionJson;

    @BeforeEach
    void setUp() {
        validDivisionJson = asJsonString(new DivisionDto(
                "Name",
                "Original Name",
                "Seymour",
                "Skinner",
                null));
        validDivisionJson2 = asJsonString(new DivisionDto(
                "Name2",
                "Original Name",
                "Seymour",
                "Skinner",
                null));
        invalidDivisionJson = "  {\n" +
                "    \"name\": \"Invalid\",\n";
        subdivisionJson = "  {\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"id\": 0,\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"name\": \"string\",\n" +
                "    \"originalName\": \"string\",\n" +
                "    \"parent\": {\n" +
                "      \"director\": {\n" +
                "        \"firstName\": \"string\",\n" +
                "        \"lastName\": \"string\"\n" +
                "      },\n" +
                "      \"id\": 1\n" +
                "    }\n" +
                "  }";
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

    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void givenValidDivisionJson_whenCreatingAndGettingAllDivisions_thenReturnCollectionWithProvidedValidDivisions() throws Exception {
        mockMvc.perform(post("/divisions")
                .content(validDivisionJson2)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/divisions")
                .content(subdivisionJson)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/divisions"))
                .andExpect(content().string(containsString("string")))
                .andExpect(content().string(containsString("Original Name")));

    }

    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void givenInvalidDivisionDto_whenCreatingADivision_thenConstraintViolationExceptionIsThrown() throws Exception {
        mockMvc.perform(post("/divisions")
                .content(invalidDivisionJson)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}