# FootballFixtures

## Prerequisite

- [javax.json JAR 1.1.4](https://jar-download.com/artifacts/org.glassfish/javax.json/1.1.4/source-code)
- [java 8+](https://www.oracle.com/java/technologies/downloads/#java8)
- [JUnit 5](https://howtodoinjava.com/junit5/junit5-maven-dependency)

## How to Run:

Clone this repository : https://github.com/eliormatan/FootballFixtures.git

Then ...


```
$ javac ...
$ java ...
```

  
### Overflow:

At the beginning the app displays the user the following options:
1. Get list of matches by team.
2. Get list of matches by team filtered by status.
3. Get list of matches by tournament.
4. Get list of matches by tournament filtered by status.
5. exit 

After the user choose an option (1-5) , the user is asked to enter an information according to the option he chose. 
Valid options are: <br/>
Option 1:   team's name &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;  Example: Chelsea <br/>
Option 2:   team's name + status &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp; Example: Manchester United played <br/>
Option 3:   tournaments's name &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Example: fa <br/>
Option 4:   tournaments's name + status &emsp;&emsp;&emsp;&emsp; Example: premier-league upcoming <br/>

When the user is choosing option 5 (exit) the app is closed.
