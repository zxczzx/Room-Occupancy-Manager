package com.example.roomoccupancymanager.occupancyCalculation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomOccupancyServiceTest {

    @Mock
    private InMemoryGuestOfferRepository roomOccupancyRepository;

    @InjectMocks
    private RoomOccupancyService roomOccupancyService;

    @ParameterizedTest
    @MethodSource("testOccupancyResultParams")
    void calculateOccupancyAndRevenue(int premiumRooms, int economyRooms, RoomOccupancyResult roomOccupancyResult) {
        when(roomOccupancyRepository.getGuestOffersSortedByPriceDesc()).thenReturn(List.of(374.0, 209.0, 155.0, 115.0, 101.0, 100.0, 99.99, 45.0, 23.0, 22.0));

        var result = roomOccupancyService.calculateOccupancyAndRevenue(premiumRooms, economyRooms);

        assertThat(result).isEqualTo(roomOccupancyResult);
    }

    private static Stream<Arguments> testOccupancyResultParams() {
        return Stream.of(
                Arguments.of(3, 3, new RoomOccupancyResult(3, 738.0, 3, 167.99)),
                Arguments.of(7, 5, new RoomOccupancyResult(6, 1054.0, 4, 189.99)),
                Arguments.of(2, 7, new RoomOccupancyResult(2, 583.0, 4, 189.99)),
                Arguments.of(7, 1, new RoomOccupancyResult(7, 1153.99, 1, 45.0)),
                Arguments.of(8, 1, new RoomOccupancyResult(8, 1198.99, 1, 23.0))
        );
    }
}