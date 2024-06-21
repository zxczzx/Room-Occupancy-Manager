package com.example.roomoccupancymanager.occupancyCalculation;

import java.util.List;

public interface IGuestOfferRepository {
    List<Double> getGuestOffersSortedByPriceDesc();
}

