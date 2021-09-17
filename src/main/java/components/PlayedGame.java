package components;

import javax.json.Json;

public class PlayedGame extends Game {

    private int homeScore;
    private int awayScore;

    public PlayedGame(Team homeTeam, int homeScore, Team awayTeam, int awayScore, Tournament tournament, String startDate, String status) {
        super(homeTeam, awayTeam, tournament, startDate, status);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    @Override
    public String toString() {
        return Json.createObjectBuilder()
                .add("home_team", this.homeTeam.getName())
                .add("home_score", this.homeScore)
                .add("away_team", this.awayTeam.getName())
                .add("away_score", awayScore)
                .add("tournament", this.tournament.getName())
                .add("start_time", this.startDate)
                .build()
                .toString();
    }
}
