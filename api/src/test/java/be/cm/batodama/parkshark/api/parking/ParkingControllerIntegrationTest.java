package be.cm.batodama.parkshark.api.parking;

import be.cm.batodama.parkshark.ApiTestApplication;
import be.cm.batodama.parkshark.domain.parking.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiTestApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ParkingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void givenParkingLotDto_whenCreatingParkingLot_thenReturnedParkingLotDtoEqualsOriginal() throws Exception {
        entityManager.persist(new ParkingLotContactPerson("Niels", "niels@myemail.com", "484848484", "011848532",
                new Address("Street Test", "1", new PostCode("Post Test", "Leuven"))));

        ParkingLotDto originalParkingLotDto = new ParkingLotDto("Test", "UNDERGROUND",
                new AddressDto("Street Test", "1", new PostCodeDto("Post Test", "Leuven")),
                50,
                3,
                100);

        mockMvc.perform(post("/parkingLots")
                .content(asJsonString(originalParkingLotDto))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    /**
     * This test is very similar to the above (givenParkingLotDto_whenCreatingParkingLot_thenReturnedParkingLotDtoEqualsOriginal)
     * But, here we Mock the mapper and then program it to return us a ParkingLot object.
     * In the above test, we do not mock, but use the actual mapper (which uses the entityManager). Furthermore, we create
     * a new ParkingLotContactPerson which is then queried in our code.
     */

//    @MockBean
//    private ParkingLotMapper parkingLotMapper;
//
//    @Test
//    @WithMockUser(authorities = "ROLE_MANAGER")
//    void givenParkingLotDto_whenCreatingParkingLot_thenReturnedParkingLotDtoEqualsOriginal() throws Exception {
//        ParkingLotDto originalParkingLotDto = new ParkingLotDto("Test",
//                "UNDERGROUND",
//                new AddressDto("Street Test", "1", new PostCodeDto("Post Test", "Leuven")),
//                50,
//                1,
//                100);
//
//        Mockito.when(parkingLotMapper.mapToParkingLot(Mockito.any(ParkingLotDto.class)))
//                .thenReturn(
//                        new ParkingLot("Parksharky 1", ParkingLotCategory.UNDERGROUND,
//                                new Address("Street Test", "1", new PostCode("Post Test", "Leuven")),
//                                12, new ParkingLotContactPerson("Niels", "niels@myemail.com", "484848484", "011848532",
//                                        new Address("Street Test", "1",
//                                        new PostCode("Post Test", "Leuven"))), 120));
//
//        mockMvc.perform(post("/parkingLots")
//                .content(asJsonString(originalParkingLotDto))
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//
//    }
    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void whenGettingAllParkingLot_thenReturnedAllParkingLot() throws Exception {
        mockMvc.perform(get("/parkingLots")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
    void whenGettingParkingLotByID_thenReturnedParkingLot() throws Exception {
        entityManager.persist(new ParkingLot("Test", ParkingLotCategory.UNDERGROUND,
                new Address("test", "test", new PostCode("test", "test")), 10,
                new ParkingLotContactPerson("Niels", "niels@myemail.com", "484848484", "011848532",
                new Address("Street Test", "1", new PostCode("Post Test", "Leuven"))), 10));

        mockMvc.perform(get("/parkingLots/3")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}