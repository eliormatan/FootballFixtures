import static auxiliary.Printer.*;
import static auxiliary.Init.*;
import static common.Constants.*;
import static common.Shared.*;
import static common.Shared.playedList;

import components.*;
import org.junit.jupiter.api.*;

import javax.json.Json;
import java.util.ArrayList;
import java.util.HashMap;

//one instance of test class
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@DisplayName("Printer tests:")
class PrinterTest {

    @BeforeAll
    void setUp() {
        init("result_played.csv", "result_upcoming.csv");
    }

    @AfterAll
    void tearDown() {
        cleanup();
    }

    @Test
    @Order(1)
    void isLegalInputTest() {
        String name = "Chelsea";
        String missingName = "Chelsea Tel Aviv";
        String type = "team";
        String wrongType = "team tournament";
        String status = "played";
        String wrongStatus = "played well";

        if (!isLegalInput(name, type, status)) {
            Assertions.fail(name + " " + type + " " + status + " are valid");
        }
        if (isLegalInput(name, type, wrongStatus)) {
            Assertions.fail(name + " " + type + " " + status + " are not valid (incorrect status)");
        }
        if (isLegalInput(name, wrongType, status)) {
            Assertions.fail(name + " " + type + " " + status + " are not valid (incorrect type)");
        }
        if (isLegalInput(missingName, type, status)) {
            Assertions.fail(name + " " + type + " " + status + " are not valid (incorrect name)");
        }
        System.out.println("-----isLegalInput tests passed!-----");
    }

    @Test
    @Order(2)
    void printGameTest() {
        UpcomingGame game1 = new UpcomingGame(new Team("Manchester City", 1), new Team("Huddersfield Town", 2), new Tournament("fa", 1), "Wednesday 1st March 2017", "19:45", UPCOMING);
        PlayedGame game2 = new PlayedGame(new Team("Sutton United", 2), 0, new Team("Arsenal", 3), 0, new Tournament("premier-league", 2), "Monday 20th February 2017", PLAYED);
        String home = "Manchester City";
        String away = "Huddersfield Town";
        String missingName = "Chelsea";
        String tournament = "fa";
        String wrongTournament = "premier-league";
        String type1 = TOURNAMENT;
        String type2 = TEAM;

        String expected1 = Json.createObjectBuilder()
                .add("home_team", game1.homeTeam.getName())
                .add("away_team", game1.awayTeam.getName())
                .add("tournament", game1.tournament.getName())
                .add("start_time", game1.getStartDate())
                .add("kickoff", game1.getKickoff())
                .build()
                .toString();

        String expected2 = Json.createObjectBuilder()
                .add("home_team", game2.homeTeam.getName())
                .add("home_score", game2.getHomeScore())
                .add("away_team", game2.awayTeam.getName())
                .add("away_score", game2.getAwayScore())
                .add("tournament", game2.tournament.getName())
                .add("start_time", game2.getStartDate())
                .build()
                .toString();

        printGame(game1, home, type2);
        String actual1 = "{\"home_team\":\"Manchester City\",\"away_team\":\"Huddersfield Town\",\"tournament\":\"fa\",\"start_time\":\"Wednesday 1st March 2017\",\"kickoff\":\"19:45\"}";
        printGame(game1, away, type2);
        String actual2 = "{\"home_team\":\"Manchester City\",\"away_team\":\"Huddersfield Town\",\"tournament\":\"fa\",\"start_time\":\"Wednesday 1st March 2017\",\"kickoff\":\"19:45\"}";
        printGame(game1, tournament, type1);
        String actual3 = "{\"home_team\":\"Manchester City\",\"away_team\":\"Huddersfield Town\",\"tournament\":\"fa\",\"start_time\":\"Wednesday 1st March 2017\",\"kickoff\":\"19:45\"}";
        printGame(game1, missingName, type2);
        printGame(game1, missingName, type2);
        printGame(game1, wrongTournament, type1);

        printGame(game2, home, type2);
        String actual4 = "{\"home_team\":\"Sutton United\",\"home_score\":0,\"away_team\":\"Arsenal\",\"away_score\":0,\"tournament\":\"premier-league\",\"start_time\":\"Monday 20th February 2017\"}";
        printGame(game2, away, type2);
        String actual5 = "{\"home_team\":\"Sutton United\",\"home_score\":0,\"away_team\":\"Arsenal\",\"away_score\":0,\"tournament\":\"premier-league\",\"start_time\":\"Monday 20th February 2017\"}";
        printGame(game2, tournament, type1);
        String actual6 = "{\"home_team\":\"Sutton United\",\"home_score\":0,\"away_team\":\"Arsenal\",\"away_score\":0,\"tournament\":\"premier-league\",\"start_time\":\"Monday 20th February 2017\"}";
        printGame(game2, missingName, type2);
        printGame(game2, wrongTournament, type1);

        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected1, actual2);
        Assertions.assertEquals(expected1, actual3);
        Assertions.assertEquals(expected2, actual4);
        Assertions.assertEquals(expected2, actual5);
        Assertions.assertEquals(expected2, actual6);

        System.out.println("-----printGame tests passed!------");
    }

    @Test
    @Order(3)
    void printGamesTest() {

        updateLists();

        printGames("Arsenal", TEAM, true);
        printGames("Arsenal upcoming", TEAM, false);
        printGames("Arsenal played", TEAM, false);
        printGames("Brighton & Hove Albion", TEAM, true);
        printGames("fa", TOURNAMENT, true);
        printGames("fa played", TOURNAMENT, false);


        String[] expectedArs = new String[]{
                "{\"home_team\":\"Sutton United\",\"home_score\":0,\"away_team\":\"Arsenal\",\"away_score\":0,\"tournament\":\"premier-league\",\"start_time\":\"Monday 20th February 2017\"}",
                "{\"home_team\":\"Arsenal\",\"away_team\":\"Lincoln City\",\"tournament\":\"fa\",\"start_time\":\"Saturday 11th March 2017\",\"kickoff\":\"17:30\"}"
        };
        String expectedArsUp = "{\"home_team\":\"Arsenal\",\"away_team\":\"Lincoln City\",\"tournament\":\"fa\",\"start_time\":\"Saturday 11th March 2017\",\"kickoff\":\"17:30\"}";
        String expectedArsPl = "{\"home_team\":\"Sutton United\",\"home_score\":0,\"away_team\":\"Arsenal\",\"away_score\":0,\"tournament\":\"premier-league\",\"start_time\":\"Monday 20th February 2017\"}";
        String expectedBri = "{\"home_team\":\"Brighton & Hove Albion\",\"home_score\":2,\"away_team\":\"Milton Keynes Dons\",\"away_score\":2,\"tournament\":\"fa\",\"start_time\":\"Saturday 7th January 2017\"}";
        String[] expectedFa = new String[]{
                "{\"home_team\":\"Manchester City\",\"away_team\":\"Huddersfield Town\",\"tournament\":\"fa\",\"start_time\":\"Wednesday 1st March 2017\",\"kickoff\":\"19:45\"}",
                "{\"home_team\":\"Arsenal\",\"away_team\":\"Lincoln City\",\"tournament\":\"fa\",\"start_time\":\"Saturday 11th March 2017\",\"kickoff\":\"17:30\"}",
                "{\"home_team\":\"Brighton & Hove Albion\",\"home_score\":2,\"away_team\":\"Milton Keynes Dons\",\"away_score\":2,\"tournament\":\"fa\",\"start_time\":\"Saturday 7th January 2017\"}"
        };
        String expectedFaPl = "{\"home_team\":\"Brighton & Hove Albion\",\"home_score\":2,\"away_team\":\"Milton Keynes Dons\",\"away_score\":2,\"tournament\":\"fa\",\"start_time\":\"Saturday 7th January 2017\"}";
        String[] actualArs = new String[]{
                "{\"home_team\":\"Sutton United\",\"home_score\":0,\"away_team\":\"Arsenal\",\"away_score\":0,\"tournament\":\"premier-league\",\"start_time\":\"Monday 20th February 2017\"}",
                "{\"home_team\":\"Arsenal\",\"away_team\":\"Lincoln City\",\"tournament\":\"fa\",\"start_time\":\"Saturday 11th March 2017\",\"kickoff\":\"17:30\"}"
        };
        String actualArsUp = "{\"home_team\":\"Arsenal\",\"away_team\":\"Lincoln City\",\"tournament\":\"fa\",\"start_time\":\"Saturday 11th March 2017\",\"kickoff\":\"17:30\"}";
        String actualArsPl = "{\"home_team\":\"Sutton United\",\"home_score\":0,\"away_team\":\"Arsenal\",\"away_score\":0,\"tournament\":\"premier-league\",\"start_time\":\"Monday 20th February 2017\"}";
        String actualBri = "{\"home_team\":\"Brighton & Hove Albion\",\"home_score\":2,\"away_team\":\"Milton Keynes Dons\",\"away_score\":2,\"tournament\":\"fa\",\"start_time\":\"Saturday 7th January 2017\"}";
        String[] actualFa = new String[]{
                "{\"home_team\":\"Manchester City\",\"away_team\":\"Huddersfield Town\",\"tournament\":\"fa\",\"start_time\":\"Wednesday 1st March 2017\",\"kickoff\":\"19:45\"}",
                "{\"home_team\":\"Arsenal\",\"away_team\":\"Lincoln City\",\"tournament\":\"fa\",\"start_time\":\"Saturday 11th March 2017\",\"kickoff\":\"17:30\"}",
                "{\"home_team\":\"Brighton & Hove Albion\",\"home_score\":2,\"away_team\":\"Milton Keynes Dons\",\"away_score\":2,\"tournament\":\"fa\",\"start_time\":\"Saturday 7th January 2017\"}"
        };
        String actualFaPl = "{\"home_team\":\"Brighton & Hove Albion\",\"home_score\":2,\"away_team\":\"Milton Keynes Dons\",\"away_score\":2,\"tournament\":\"fa\",\"start_time\":\"Saturday 7th January 2017\"}";

        Assertions.assertArrayEquals(expectedArs, actualArs);
        Assertions.assertEquals(expectedArsPl, actualArsPl);
        Assertions.assertEquals(expectedArsUp, actualArsUp);
        Assertions.assertEquals(expectedBri, actualBri);
        Assertions.assertArrayEquals(expectedFa, actualFa);
        Assertions.assertEquals(expectedFaPl, actualFaPl);

        System.out.println("-----printGames tests passed!-----");
    }


    private void cleanup() {
        teams = null;
        tournaments = null;
        upcomingList = null;
        playedList = null;
    }

    private void updateLists() {
        upcomingList = new ArrayList<>();
        playedList = new ArrayList<>();
        teams = new HashMap<>();
        tournaments = new HashMap<>();

        tournaments.put("fa", 1);
        tournaments.put("premier-league", 2);
        teams.put("Manchester City", 1);
        teams.put("Huddersfield Town", 2);
        teams.put("Sutton United", 3);
        teams.put("Arsenal", 4);
        teams.put("Lincoln City", 5);
        teams.put("Brighton & Hove Albion", 6);
        teams.put("Milton Keynes Dons", 7);
        upcomingList.add(new UpcomingGame(new Team("Manchester City", 1), new Team("Huddersfield Town", 2), new Tournament("fa", 1), "Wednesday 1st March 2017", "19:45", UPCOMING));
        upcomingList.add(new UpcomingGame(new Team("Arsenal", 4), new Team("Lincoln City", 5), new Tournament("fa", 1), "Saturday 11th March 2017", "17:30", UPCOMING));
        playedList.add(new PlayedGame(new Team("Sutton United", 3), 0, new Team("Arsenal", 4), 0, new Tournament("premier-league", 2), "Monday 20th February 2017", PLAYED));
        playedList.add(new PlayedGame(new Team("Brighton & Hove Albion", 6), 2, new Team("Milton Keynes Dons", 7), 2, new Tournament("fa", 1), "Saturday 7th January 2017", PLAYED));

    }
}