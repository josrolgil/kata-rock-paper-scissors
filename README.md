# kata-rock-paper-scissors
Rock Paper Scissors game kata

This web app implements the rock-paper-scissors game
It is developed using Java and Spring framework. The project is managed using maven.

## How to run
### Requirements:
Java 11
Maven 3.6.3
### Steps
``` 
mvn package  
java -jar target/game-0.1.0.jar
```
### The game
The user can open a web broser and access to
```
    localhost:8080/game
```
To access the landing page of the game. The user can click on start button to play a new game round or clean to 
remove all rounds from the list.

Alternatively, the user can access to
```
    localhost:8080/stats/list
```
And access to the game statistics
