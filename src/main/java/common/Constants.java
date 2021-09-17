package common;

public class Constants {

    public static final String UPCOMING = "upcoming";
    public static final String PLAYED = "played";
    public static final String TEAM = "team";
    public static final String TOURNAMENT = "tournament";

    public static final String OPTION1 = "Please enter a team: ";
    public static final String OPTION2 = "Please enter a team and a status: ";
    public static final String OPTION3 = "Please enter a tournament: ";
    public static final String OPTION4 = "Please enter a tournament and a status: ";
    public static final String OPTION5 = "5";

    public static final String WELCOME = "Welcome to Football Fixtures!";
    public static final String BYE = "See you next time ;)";
    public static final String INVALID_OPTION = "Please choose a valid option! (1-5)";

    public static void printOptions() {
        System.out.print("\nPlease choose an option:\n" +
                "1.Get list of matches by team\n" +
                "2.Get list of matches by team filtered by status\n" +
                "3.Get list of matches by tournament\n" +
                "4.Get list of matches by tournament filtered by status\n" +
                "5.exit\n" + "-> ");
    }


}
