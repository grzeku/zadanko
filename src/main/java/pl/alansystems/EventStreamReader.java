package pl.alansystems;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class EventStreamReader {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private StatisticsService statisticsService;

    public void readEventStream() {
        Map<String, TeamStatistics> prevResults = new HashMap<>();


        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/json/messages.txt"))) {
            for (String oneNewEventToMap; (oneNewEventToMap = br.readLine()) != null; ) {
                System.out.println(oneNewEventToMap);

                SportEventWrapper oneNewEventWrapper = objectMapper.readValue(oneNewEventToMap, SportEventWrapper.class);
                SportEvent oneNewEvent = oneNewEventWrapper.getResult();

                Map<String, TeamStatistics> newResults = statisticsService.countStatistics(oneNewEvent, prevResults);
                printTeamStatistics(newResults, oneNewEvent.getHomeTeam(), oneNewEvent.getAwayTeam());
                prevResults = newResults;
            }
        } catch (IOException e) {
            System.out.println("Failed to read from file");
            System.out.println(e.toString());
            System.exit(2);
        }
    }

    private void printTeamStatistics(Map<String, TeamStatistics> newResults, String home, String away) {
        System.out.println(home + " " + newResults.get(home).getPlayedMatches() + " " + newResults.get(home).getGainedPoints() + " " + newResults.get(home).getGoalsScored() + " " + newResults.get(home).getGoalsConceded());
        System.out.println(away + " " + newResults.get(away).getPlayedMatches() + " " + newResults.get(away).getGainedPoints() + " " + newResults.get(away).getGoalsScored() + " " + newResults.get(away).getGoalsConceded());


    }
}
