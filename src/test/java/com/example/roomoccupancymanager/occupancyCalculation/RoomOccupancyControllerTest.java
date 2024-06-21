package com.example.roomoccupancymanager.occupancyCalculation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RoomOccupancyController.class)
class RoomOccupancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomOccupancyService roomOccupancyService;

    @Test
    void calculateOccupancy() throws Exception {
        var expectedResponse = """
{
  "premiumRoomsUsed": 0,
  "premiumRoomsRevenue": 0.0,
  "economyRoomsUsed": 0,
  "economyRoomsRevenue": 0.0
}
""";

        when(roomOccupancyService.calculateOccupancyAndRevenue(any(), any()))
                .thenReturn(new RoomOccupancyResult(0, 0.0, 0, 0.0));

        var response = mockMvc.perform(get("/api/room-occupancy/calculate")
                .param("freePremiumRooms", "0")
                .param("freeEconomyRooms", "0"));

        response
                .andExpect(content().json(expectedResponse))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}