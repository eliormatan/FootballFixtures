package components;

public abstract class Game {
    public Team homeTeam;
    public Team awayTeam;
    public Tournament tournament;
    protected String startDate;
    protected String status;

    public Game(Team homeTeam, Team awayTeam, Tournament tournament, String startDate, String status) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.tournament = tournament;
        this.startDate = startDate;
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStatus() {
        return status;
    }

    public abstract String toString();

}
