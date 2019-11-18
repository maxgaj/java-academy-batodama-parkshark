package be.cm.batodama.parkshark.api.division;

import be.cm.batodama.parkshark.domain.division.Director;
import be.cm.batodama.parkshark.domain.division.Division;
import be.cm.batodama.parkshark.service.Division.DivisionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(DivisionController.class)
public class DivisionControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DivisionService divisionService;

    @Test
    public void givenDivisionDto_whenCreatingDivision_thenReturnedDivisionDtoEqualsOriginal() throws Exception {
        Mockito.when(divisionService.save(any(Division.class)))
                .thenReturn(new Division("Name", "Original Name", new Director("Seymour", "Skinner")));

        DivisionDto originalDivisionDto = new DivisionDto("Name",
                "Original Name",
                "Seymour",
                "Skinner");
        mockMvc.perform(post("/divisions")
                .content(asJsonString(originalDivisionDto))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}