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

        Day6Lanternfish day6 = new Day6Lanternfish((short) 7, (short) 9);
        day6.readData("src/main/resources/day-6-lanternfish");
        System.out.printf("Day 6 Q1: %d, Q2: %d.", day6.calc(80), day6.calc2(256));

        addVSpace();

        Day7Crabs day7 = new Day7Crabs("src/main/resources/day-7-crabs");
        System.out.printf("Day 7 Q1: %d, Q2: %d.", day7.calc(), day7.calc2());

        addVSpace();

        Day8Segments day8 = new Day8Segments();
        day8.readData("/day-8-segments");
        System.out.printf("Day 8 Q1: %d, Q2: %d.", day8.calc(), day8.calc2());

        addVSpace();

        Day9Basin day9 = new Day9Basin("src/main/resources/day-9-basin");
        System.out.printf("Day 9 Q1: %d, Q2: %d.", day9.calc(), day9.calc2());

        addVSpace();

        Day10Syntax day10 = new Day10Syntax("/day-10-syntax");
        System.out.printf("Day 10 Q1: %d, Q2: %d.", day10.calc(), day10.calc2());

        addVSpace();

        Day11Octopus day11 = new Day11Octopus("src/main/resources/day-11-octopus");
        System.out.printf("Day 11 Q1: %d, Q2: %d.", day11.calc(100), day11.calc2());

        addVSpace();

        Day12Pathing day12 = new Day12Pathing("/day-12-pathing");
        System.out.printf("Day 12 Q1: %d, Q2: %d.", day12.calc(), day12.calc2());

        addVSpace();

        Day13Origami day13 = new Day13Origami("src/main/resources/day-13-origami");
        System.out.printf("Day 13 Q1: %d, Q2: %d.", day13.calc(1), day13.calc2());

        addVSpace();

        Day14Polymerization day14 = new Day14Polymerization("src/main/resources/day-14-polymerization");
        System.out.printf("Day 14 Q1: %d, Q2: %d.", day14.calc(10), day13.calc2());

        addVSpace();
    }

    private static void addVSpace() {
        System.out.println();
        System.out.println();
    }
}
