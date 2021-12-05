package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day4Bingo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4BingoTest {

  @Test
  void readData() {
    Day4Bingo day4Bingo = new Day4Bingo( 5);
    day4Bingo.readData("src/test/resources/day-4-bingo-test");

    List<Integer> randoms = day4Bingo.getRandomInts();
    assertEquals(7, randoms.get(0));
    assertEquals(4, randoms.get(1));

    List<int[][]> boards = day4Bingo.getBoards();
    assertEquals(3, boards.size());

    int[][] board1 = boards.get(0);
    assertEquals(22, board1[0][0]);

    int[][] board3 = boards.get(2);
    assertEquals(14, board3[0][0]);
  }

  @Test
  void calcBingoScore() {
    int[][] board = {
      {-1, -1, -1, -1, -1},
      {10, 16, 15, -1, 19},
      {18, 8, -1, 26, 20},
      {22, -1, 13, 6, -1},
      {-1, -1, 12, 3, -1}
    };

    int random = 24;

    Day4Bingo day4 = new Day4Bingo(5);
    assertEquals(4512, day4.calcBingoScore(board, random));
  }

  @Test
  void calc() {
    Day4Bingo day4Bingo = new Day4Bingo( 5);
    day4Bingo.readData("src/test/resources/day-4-bingo-test");
    int[] result = day4Bingo.calc();

    assertEquals(24, result[0]);
    assertEquals(4512, result[1]);
  }

  @Test
  void calc2() {
    Day4Bingo day4Bingo = new Day4Bingo( 5);
    day4Bingo.readData("src/test/resources/day-4-bingo-test");
    int[] result = day4Bingo.calc2();

    assertEquals(13, result[0]);
    assertEquals(1924, result[1]);
  }
}
