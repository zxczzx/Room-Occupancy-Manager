package com.example.roomoccupancymanager.occupancyCalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room-occupancy")
@RequiredArgsConstructor
public class RoomOccupancyController {

    private final RoomOccupancyService service;

    @GetMapping("/calculate")
    public RoomOccupancyResult calculateOccupancy(@RequestParam int freePremiumRooms,
                                                  @RequestParam int freeEconomyRooms) {
        return service.calculateOccupancyAndRevenue(freePremiumRooms, freeEconomyRooms);
    }
}
