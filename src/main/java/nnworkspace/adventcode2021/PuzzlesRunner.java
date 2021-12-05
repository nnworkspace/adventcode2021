package nnworkspace.adventcode2021;

public class PuzzlesRunner {
    public static void main(String[] args) {
        Day1SonarSweep day1 = new Day1SonarSweep();
        System.out.printf("Day 1 Q1: %d, Q2: %d.", day1.calc(), day1.calc2());

        addVSpace();

        Day2Piloting day2 = new Day2Piloting();
        System.out.printf("Day 2 Q1: %d, Q2: %d.", day2.calc(), day2.calc2());

        addVSpace();

        Day3Diagnostic day3 = new Day3Diagnostic();
        System.out.printf("Day 3 Q1: %d, Q2: %d.", day3.calc(), day3.calc2());

        Day4Bingo day4 = new Day4Bingo(5);
        day4.readData("src/main/resources/day-4-bingo");
        System.out.printf("Day 4 Q1: %d, Q2: %d.", day4.calc()[1], day4.calc2()[1]);

        addVSpace();
    }

    private static void addVSpace() {
        System.out.println();
        System.out.println();
    }
}
