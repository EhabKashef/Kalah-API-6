
# Kalah API Technical assessment 
Java RESTful Web Service that runs a game of 6-stone Kalah.
 
# How to run
To run the project type the following command from the root directory:

## Build

    mvn clean install
    
    docker build -t kalah-technical-assessment .

## Run

    mvn clean spring-boot:run

OR

    docker run -d -p 8080:8080 --name kalah-technicalAssessment kalah-technical-assessment



## Usage

Documentation URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Start a new game

This will create a new Kalah(6, 6) game 

- Create Game: 

```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:8080/games'
```

- Make a move:

```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:8080/games/{gameId}/pits/{pitId}'
```
