package nnworkspace.adventcode2021.test;

import com.google.common.collect.ImmutableList;
import nnworkspace.adventcode2021.PuzzlesUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzlesUtilTest {

  @Test
  void parseIntsInFirstLine() {
    ImmutableList<Integer> crabsHPosList =
        PuzzlesUtil.parseIntsInFirstLine("src/test/resources/day-7-crabs-test");

    assertEquals(10, crabsHPosList.size());
    assertEquals(16, crabsHPosList.get(0));
    assertEquals(14, crabsHPosList.get(9));
  }

  @Test
  void parseMatrix() {
    int[][] matrix = PuzzlesUtil.parseMatrix("src/test/resources/day-9-basin-test", true);

    assertEquals(5, matrix.length);
    assertEquals(10, matrix[0].length);
    assertEquals(2, matrix[0][0]);
    assertEquals(8, matrix[4][9]);

    matrix = PuzzlesUtil.parseMatrix("src/main/resources/day-9-basin", true);
    assertEquals(100, matrix.length);
    assertEquals(100, matrix[0].length);
    assertEquals(9, matrix[0][0]);
    assertEquals(9, matrix[99][99]);
  }

  @Test
  void getLineCounts() {
    assertEquals(5, PuzzlesUtil.getLineCounts("src/test/resources/day-9-basin-test", true));
  }
}
