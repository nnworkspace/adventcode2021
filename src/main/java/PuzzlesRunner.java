public class PuzzlesRunner {
    public static void main(String[] args) {
        Day1SonarSweep day1 = new Day1SonarSweep();
        System.out.printf("Day 1 Q1: %d, Q2: %d.", day1.calc(), day1.calc2());

        System.out.println();

        Day2Piloting day2 = new Day2Piloting();
        System.out.printf("Day 2 Q1: %d, Q2: %d.", day2.calc(), day2.calc2());

        System.out.println();
    }
}
