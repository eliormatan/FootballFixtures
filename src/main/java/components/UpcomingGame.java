package components;

import javax.json.Json;

public class UpcomingGame extends Game {
    private String kickoff;

    public UpcomingGame(Team homeTeam, Team awayTeam, Tournament tournament, String startDate, String kickoff, String status) {
        super(homeTeam, awayTeam, tournament, startDate, status);
        this.kickoff = kickoff;
    }

    public String getKickoff() {
        return kickoff;
    }

    @Override
    public String toString() {
        return Json.createObjectBuilder()
                .add("home_team", this.homeTeam.getName())
                .add("away_team", this.awayTeam.getName())
                .add("tournament", this.tournament.getName())
                .add("start_time", this.startDate)
                .add("kickoff", this.kickoff)
                .build()
                .toString();
    }
}
