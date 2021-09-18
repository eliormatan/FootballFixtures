package auxiliary;

import java.util.Arrays;

import components.Game;

import static common.Constants.*;
import static common.Shared.*;

public class Printer {

    public static void printGames(String input, String type, boolean onlyName) {
        String[] inputArr = input.split(" ");
        //input includes status. split name and status
//        if (!onlyName && inputArr.length > 1) {
        if(!onlyName){
            String name = String.join(" ", Arrays.copyOf(inputArr, inputArr.length - 1));
            String status = inputArr[inputArr.length - 1];
            //validate input
            if (isLegalInput(name, type, status)) {
                //print games by status (played / upcoming)
                printGamesByStatus(name, status, type);
            }
            //input is illegal
            else {
                printError(input);
            }
        }
        //input includes name only
        else {
            //validate input
            if (isLegalInput(input, type, null)) {
                //print games for both statuses
                printGamesByStatus(input, PLAYED, type);
                printGamesByStatus(input, UPCOMING, type);
            }
            //input is illegal
            else{
                printError(input);
            }
        }

    }

    public static void printGamesByStatus(String name, String status, String type) {
        //print games according to the relevant status list
        if (status.equals(UPCOMING)) {
            for (Game g : upcomingList) {
                printGame(g, name, type);
            }
        } else {
            for (Game g : playedList) {
                printGame(g, name, type);
            }
        }
    }

    public static void printGame(Game g, String name, String type) {
        String[] currNames;
        //create an array of names
        if (type.equals(TOURNAMENT)) {
            currNames = g.tournament.getName().split(" ");
        } else {
            currNames = (g.homeTeam.getName() + "#" + g.awayTeam.getName()).split("#");
        }
        //search relevant names in the game
        for (String curr : currNames) {
            //if the name exists, print game in json format
            if (curr.equals(name)) {
                System.out.println(g.toString());
                break;
            }
        }
    }

    public static boolean isLegalInput(String name, String type, String status) {
        //status exists
        if (status != null) {
            //validate status
            if (!status.equals(UPCOMING) && !status.equals(PLAYED)) {
                return false;
            }
        }
        //validate name of the team/tournament
        if (type.equals(TEAM)) {
            return teams.containsKey(name);
        } else {
            return tournaments.containsKey(name);
        }
    }

    public static void printError(String input){
        System.out.println(input+INVALID_INFO);
    }
}