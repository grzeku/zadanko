package pl.alansystems;

import java.util.Map;

public class StatisticsService {

    public Map<String, TeamStatistics> countStatistics(SportEvent result, Map<String, TeamStatistics> stats) {

        TeamStatistics homeTeamStats = stats.getOrDefault(result.getHomeTeam(), TeamStatistics.beforeFistMatch());
        TeamStatistics awayTeamStats = stats.getOrDefault(result.getAwayTeam(), TeamStatistics.beforeFistMatch());

        TeamStatistics homeTeamModifiedStatistics = homeTeamStats.registerMatchScore(result.getHomeScore(), result.getAwayScore());
        stats.putIfAbsent(result.getHomeTeam(), homeTeamModifiedStatistics);
        stats.replace(result.getHomeTeam(), homeTeamStats, homeTeamModifiedStatistics);

        TeamStatistics awayTeamModifiedStatistics = awayTeamStats.registerMatchScore(result.getAwayScore(), result.getHomeScore());
        stats.putIfAbsent(result.getAwayTeam(), awayTeamModifiedStatistics);
        stats.replace(result.getAwayTeam(), awayTeamStats, awayTeamModifiedStatistics);

        return stats;
    }
}
