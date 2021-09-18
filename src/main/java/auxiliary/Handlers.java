package auxiliary;

import static auxiliary.Printer.printError;
import static auxiliary.Printer.printGames;
import static common.Constants.*;
import static common.Constants.TOURNAMENT;
import static common.Shared.option;

public class Handlers {

    public static void handleInput(String input) {
        //
        if (option == 0 || !Character.isDigit(input.charAt(0))) {
            switch (input) {
                case "1": {
                    option = 1;
                    System.out.print(OPTION1);
                    break;
                }
                case "2": {
                    option = 2;
                    System.out.print(OPTION2);
                    break;
                }
                case "3": {
                    option = 3;
                    System.out.print(OPTION3);
                    break;
                }
                case "4": {
                    option = 4;
                    System.out.print(OPTION4);
                    break;
                }
                default: {
                    //information from the user about the relevant option
                    if (option != 0) {
                        handleCommand(input, option);
                        option = 0;
                    }
                    //the user enters invalid option
                    else {
                        System.out.println(INVALID_OPTION);
                    }
                    printOptions();
                }
            }
        }
        //the user enters a number instead of data
        else {
            option = 0;
            printError(input);
            printOptions();
        }
    }

    public static void handleCommand(String input, int option) {
        boolean onlyName = false;
        //option includes name only
        if (option == 1 || option == 3) {
            onlyName = true;
        }
        //print games according to type (team / tournament)
        if (option == 1 || option == 2) {
            printGames(input, TEAM, onlyName);
        } else {
            printGames(input, TOURNAMENT, onlyName);
        }
    }
}
