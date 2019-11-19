package be.cm.batodama.parkshark.api.allocation;

import be.cm.batodama.parkshark.ApiTestApplication;
import be.cm.batodama.parkshark.api.parking.AddressDto;
import be.cm.batodama.parkshark.api.parking.ParkingLotDto;
import be.cm.batodama.parkshark.api.parking.PostCodeDto;
import be.cm.batodama.parkshark.domain.parking.Address;
import be.cm.batodama.parkshark.domain.parking.ParkingLot;
import be.cm.batodama.parkshark.domain.parking.ParkingLotContactPerson;
import be.cm.batodama.parkshark.domain.parking.PostCode;
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

    @PersistenceContext
    private EntityManager entityManager;

    private ParkingLotDto validParkingLot;

    @BeforeEach
    void setUp() {
        validParkingLot =  new ParkingLotDto("Test", "UNDERGROUND",
                new AddressDto("Street Test", "1", new PostCodeDto("Post Test", "Leuven")),
                50,
                1,
                100);
    }

    @Test
    void startAllocation_givenValidCredentialAndValidData_thenReturnCorrectJSON() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    }
}