# Room Occupancy Manager

## Overview
This project provides a room occupancy optimization tool for hotels. It calculates the optimal occupancy of premium and economy rooms based on guests' willingness to pay.

## Requirements
- Java 21
- Gradle
- Spring Boot

## Running the Project
1. Clone the repository.
2. Navigate to the project directory.
3. Run `./gradlew bootRun` to start the application.
4. Use a REST client to send requests to `[http://localhost:8080/api/room-occupancy/calculate](http://localhost:8080/api/room-occupancy/calculate?freePremiumRooms=<number>&freeEconomyRooms=<number>)`.

## Testing
Run `./gradlew test` to execute the tests.

## Example Request

**GET Request:**
http://localhost:8080/api/room-occupancy/calculate?freePremiumRooms=3&freeEconomyRooms=3

**Response:**
```json
{
    "premiumRoomsUsed": 3,
    "economyRoomsUsed": 3,
    "premiumRevenue": 738.0,
    "economyRevenue": 167.99
}
