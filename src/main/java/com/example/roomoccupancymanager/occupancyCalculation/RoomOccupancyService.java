package com.example.roomoccupancymanager.occupancyCalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomOccupancyService {
    private final InMemoryGuestOfferRepository guestOfferRepository;

    public RoomOccupancyResult calculateOccupancyAndRevenue(Integer premiumRoomsCount, Integer economyRoomsCount) {
        List<Double> offers = guestOfferRepository.getGuestOffersSortedByPriceDesc();

        int premiumRoomsUsed = 0;
        int economyRoomsUsed = 0;
        double premiumRevenue = 0;
        double economyRevenue = 0;
        var economyIndex = 0;

        for (int i = 0; i < offers.size(); i++) {
            if (offers.get(i) >= 100 && premiumRoomsUsed < premiumRoomsCount) {
                premiumRoomsUsed++;
                premiumRevenue += offers.get(i);
            } else if (offers.get(i) < 100 && economyRoomsUsed < economyRoomsCount) {
                if (economyIndex == 0) {
                    economyIndex = i;
                }
                economyRoomsUsed++;
                economyRevenue += offers.get(i);
            }
        }

        // Upgrade economy to premium
        if (economyRoomsUsed == economyRoomsCount && premiumRoomsUsed < premiumRoomsCount) {
            var roomsToFill = premiumRoomsCount - premiumRoomsUsed;
            for (int j = 0; j < roomsToFill; j++) {
                for (int i = economyIndex; i < offers.size(); i++) {
                    if (economyRoomsUsed == economyRoomsCount && premiumRoomsUsed < premiumRoomsCount) {
                        premiumRoomsUsed++;
                        premiumRevenue += offers.get(i);
                        economyRoomsUsed--;
                        economyRevenue -= offers.get(i);
                    } else if ( economyRoomsUsed < economyRoomsCount) {
                        economyRoomsUsed++;
                        economyRevenue += offers.get(i);
                        economyIndex++;
                        break;
                    }
                }
            }
        }

        return new RoomOccupancyResult(premiumRoomsUsed, premiumRevenue, economyRoomsUsed, economyRevenue);
    }
}
