package pl.alansystems;

public class Main {
    public static void main(String[] args) {


        EventStreamReader esr = new EventStreamReader(new StatisticsService());
        esr.readEventStream();
    }
}