# FootballFixtures

## Prerequisite

- [javax.json 1.1.4](https://jar-download.com/artifacts/org.glassfish/javax.json/1.1.4/source-code)
- [java 8](https://www.oracle.com/java/technologies/downloads/#java8)
- [JUnit 5](https://howtodoinjava.com/junit5/junit5-maven-dependency)

## How to Run:

Clone this repository : https://github.com/eliormatan/FootballFixtures.git

Then on Mac/Linux/Windows:

```
$ java -jar FootballFixtures.jar result_played.csv result_upcoming.csv
```

  
### Overflow:

At the beginning the app displays the following options:
1. Get list of matches by team.
2. Get list of matches by team filtered by status.
3. Get list of matches by tournament.
4. Get list of matches by tournament filtered by status.
5. exit 

After the user chooses an option (1-5) , the user is asked to enter the required information according to the option they chose. 
#### Valid options are: <br/>

Option | Expected Input | Example
------------ | ------------- | -------------
1 | team's name | Manchester United
2 | team's name + status | Chelsea played
3 | tournaments's name | premier-league
4 | tournaments's name + status | fa upcoming

Then the relevant games will be printed to the console and after that, the options will display again so the app will be ready for a new request from the user.

These procedures continue repeating themseleves until the user is choosing option 5 (exit) and the app closes.
