package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.ApiTestApplication;
import be.cm.batodama.parkshark.api.parking.AddressDto;
import be.cm.batodama.parkshark.api.parking.ParkingLotDto;
import be.cm.batodama.parkshark.api.parking.PostCodeDto;
import be.cm.batodama.parkshark.domain.division.Director;
import be.cm.batodama.parkshark.domain.division.Division;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiTestApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AllocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private ParkingLotDto validParkingLot;

//    @BeforeEach
//    void setUp() {
//        validParkingLot = new ParkingLotDto("Test", "UNDERGROUND",
//                new AddressDto("Street Test", "1", new PostCodeDto("Post Test", "Leuven")),
//                50,
//                1,
//                100,
//                new Division("Name", "Original Name", new Director("Seymour", "Skinner"), null));
//    }

    @Test
    void startAllocation_givenValidCredentialAndValidData_thenReturnCorrectJSON() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void startAllocation_givenInvalidParkingLotId_thenReturnBadRequest() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=abcd&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void startAllocation_givenNonExistingParkingLotId_thenReturnBadRequest() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=10000&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void startAllocation_givenNonMatchingLicensePlate_thenReturnBadRequest() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=4fgh789")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void startAllocation_givenInvalidCredentials_thenReturnBadRequest() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=4fgh789")
                        .with(httpBasic("manager", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}