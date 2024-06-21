package com.example.roomoccupancymanager.occupancyCalculation;

public record RoomOccupancyResult(Integer premiumRoomsUsed,
                                  Double premiumRoomsRevenue,
                                  Integer economyRoomsUsed,
                                  Double economyRoomsRevenue) {
}
