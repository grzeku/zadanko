package pl.alansystems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamStatistics {

    private int playedMatches;
    private int gainedPoints;
    private int goalsScored;
    private int goalsConceded;

    public static TeamStatistics beforeFistMatch() {
        return new TeamStatistics();
    }

    public TeamStatistics registerMatchScore(int teamGoals, int opponentGoals) {
        int points = 0;

        if (teamGoals == opponentGoals) {
            points = 1;
        } else if (teamGoals > opponentGoals) {
            points = 3;
        }

        return new TeamStatistics(
                this.playedMatches + 1,
                this.gainedPoints + points,
                this.goalsScored + teamGoals,
                this.goalsConceded + opponentGoals);
    }
}
