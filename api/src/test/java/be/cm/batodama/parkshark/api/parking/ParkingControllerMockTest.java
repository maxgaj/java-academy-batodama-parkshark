package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.ApiTestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiTestApplication.class)
@AutoConfigureMockMvc
class ParkingControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void givenParkingLotDto_whenCreatingParkingLot_thenReturnedParkingLotDtoEqualsOriginal() throws Exception {

        ParkingLotDto originalParkingLotDto = new ParkingLotDto("Test",
                "UNDERGROUND",
                new AddressDto("Street Test", "1", new PostCodeDto("Post Test", "Leuven")),
                50,
                1,
                100);

        mockMvc.perform(post("/parkingLots")
                .content(asJsonString(originalParkingLotDto))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

//        Mockito.when(parkingLotMapper.mapToParkingLot(originalParkingLotDto)).

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}