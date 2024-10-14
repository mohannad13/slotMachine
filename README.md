# slotMachine
Prerequisites

To run the project, you'll need:

Java 17 (or higher)
    
Maven (to build the project)
    
Redis (running locally on port 6379 by default)


# for testing the api:

Create a new player:
curl -X POST "http://localhost:8080/api/slot-machine/create-player?playerId=player1&spins=10&points=0"

spin:

curl -X POST "http://localhost:8080/api/slot-machine/spin/player1"

missions:

curl -X GET "http://localhost:8080/api/slot-machine/missions"

