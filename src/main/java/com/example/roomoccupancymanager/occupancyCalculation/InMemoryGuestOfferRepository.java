package com.example.roomoccupancymanager.occupancyCalculation;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class InMemoryGuestOfferRepository implements IGuestOfferRepository {
    @Override
    public List<Double> getGuestOffersSortedByPriceDesc() {
        return Stream.of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0)
                .sorted(Collections.reverseOrder())
                .toList();
    }
}
