package auxiliary;

import java.util.HashMap;

import static auxiliary.CSVReader.readCSV;
import static common.Constants.PLAYED;
import static common.Constants.UPCOMING;
import static common.Shared.*;

public class Init {

    public static void init(String pathPlayed, String pathUpcoming) {
        option=0;
        uniqueTeamId = 0;
        uniqueTournamentId = 0;
        teams = new HashMap<>();
        tournaments = new HashMap<>();
        upcomingList = readCSV(pathUpcoming, UPCOMING);
        playedList = readCSV(pathPlayed, PLAYED);
    }
}
