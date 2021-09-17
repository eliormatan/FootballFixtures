package auxiliary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import components.*;

import static common.Constants.UPCOMING;
import static common.Shared.*;

public class CSVReader {
    private static List<String> currLine;

    public static List<Game> readCSV(String path, String status) {
        List<Game> list = new ArrayList<>();
        boolean firstLine = true;

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                //read new line
                currLine = readLine(scanner.nextLine());
                if (!firstLine) {
                    //create game by status and add it to the list
                    Game game=createGame(status);
                    list.add(game);
                }
                //skip first line
                else {
                    firstLine = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static ArrayList<String> readLine(String line) {
        ArrayList<String> records = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                records.add(rowScanner.next());
            }
        }
        return records;
    }

    public static Game createGame(String status){
        Game game;

        String home_team = currLine.get(0);
        String away_team = status.equals(UPCOMING) ? currLine.get(1) : currLine.get(2);
        String tour = status.equals(UPCOMING) ? currLine.get(2) : currLine.get(4);
        String start_time = status.equals(UPCOMING) ? currLine.get(3) : currLine.get(5);

        int home_id = getUniqueId(true, teams, home_team);
        int away_id = getUniqueId(true, teams, away_team);
        int tournament_id = getUniqueId(false, tournaments, tour);
        Team home = new Team(home_team, home_id);
        Team away = new Team(away_team, away_id);
        Tournament tournament = new Tournament(tour, tournament_id);

        //create new upcoming game
        if (status.equals(UPCOMING)) {
            String kickoff = currLine.get(4);
            game = new UpcomingGame(home, away, tournament, start_time, kickoff, status);
        }
        //create new played game
        else {
            int home_score = Integer.parseInt(currLine.get(1));
            int away_score = Integer.parseInt(currLine.get(3));
            game = new PlayedGame(home, home_score, away, away_score, tournament, start_time, status);
        }
        return game;
    }

    private static int getUniqueId(boolean isTeam, HashMap<String, Integer> map, String object) {
        int res;
        //add team/tournament with unique id (if it's not already in the map)
        if (!map.containsKey(object)) {
            if (isTeam) {
                uniqueTeamId++;
                res = uniqueTeamId;
            } else {
                uniqueTournamentId++;
                res = uniqueTournamentId;
            }
            map.put(object, res);
        }
        //get the id of the team/tournament from the map
        else {
            res = map.get(object);
        }
        return res;
    }
}
