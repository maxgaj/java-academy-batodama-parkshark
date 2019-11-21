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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiTestApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class AllocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    private ParkingLotDto validParkingLot;

    @BeforeEach
    void setUp() {
        validParkingLot = new ParkingLotDto("Test", "UNDERGROUND",
                new AddressDto("Street Test", "1", new PostCodeDto("Post Test", "Leuven")),
                50,
                1,
                100, new Division("Name", "Original Name", new Director("Seymour", "Skinner"), null));
    }

    @Test
    @WithMockUser(authorities = "ROLE_MANAGER")
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

    @Test
    void stopAllocation_givenValidCredentialAndValidData_thenReturnCorrectJSON() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        Long id = findIdFromResponse(result);

        mockMvc.perform(
                put("/allocations?allocationId=" + id)
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void stopAllocation_givenAlreadyStoppedAllocation_thenBadRequest() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        Long id = findIdFromResponse(result);

        mockMvc.perform(
                put("/allocations?allocationId=" + id)
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/allocations?allocationId=" + id)
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void stopAllocation_givenInvalidUser_thenReturnCorrectJSON() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        Long id = findIdFromResponse(result);

        mockMvc.perform(
                put("/allocations?allocationId=" + id)
                        .with(httpBasic("member2", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void stopAllocation_givenNonExistingData_thenReturnCorrectJSON() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(
                put("/allocations?allocationId=10000")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void stopAllocation_givenInvalidData_thenReturnCorrectJSON() throws Exception {
        mockMvc.perform(
                post("/allocations?parkingId=1&licensePlate=1ABC123")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(
                put("/allocations?allocationId=abcd")
                        .with(httpBasic("member", "1234"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private Long findIdFromResponse(MvcResult result) throws UnsupportedEncodingException {
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(result.getResponse().getContentAsString());
        matcher.find();
        return Long.parseLong(matcher.group());
    }
}