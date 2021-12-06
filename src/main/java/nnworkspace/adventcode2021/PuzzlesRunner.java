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

        addVSpace();

        Day4Bingo day4 = new Day4Bingo(5);
        day4.readData("src/main/resources/day-4-bingo");
        System.out.printf("Day 4 Q1: %d, Q2: %d.", day4.calc()[1], day4.calc2()[1]);

        addVSpace();

        Day5Vents day5 = new Day5Vents(1000, 1000);
        day5.readData("/day-5-vents");
        System.out.printf("Day 5 Q1: %d, Q2: %d.", day5.calc(), day5.calc2());

        addVSpace();

        Day6Lanternfish day6 = new Day6Lanternfish(7, 9);
        day6.readData("src/main/resources/day-6-lanternfish");
        System.out.printf("Day 6 Q1: %d, Q2: %d.", day6.calc(80), day6.calc2(80));

        addVSpace();
    }

    private static void addVSpace() {
        System.out.println();
        System.out.println();
    }
}
