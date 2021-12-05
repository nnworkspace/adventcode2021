package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day5Vents;
import nnworkspace.adventcode2021.PuzzlesUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class Day5VentsTest {
  private static final Logger logger = Logger.getLogger(Day5VentsTest.class.getName());

  @Test
  void readData() {
    Day5Vents day5 = new Day5Vents(10, 10);
    day5.readData("/day-5-vents-test");
    List<int[]> ventsConfig = day5.getVentPosConfigs();

    assertEquals(10, ventsConfig.size());

    assertEquals(0, ventsConfig.get(0)[0]);
    assertEquals(9, ventsConfig.get(0)[1]);
    assertEquals(5, ventsConfig.get(0)[2]);
    assertEquals(9, ventsConfig.get(0)[3]);

    assertEquals(5, ventsConfig.get(9)[0]);
    assertEquals(5, ventsConfig.get(9)[1]);
    assertEquals(8, ventsConfig.get(9)[2]);
    assertEquals(2, ventsConfig.get(9)[3]);
  }

  @Test
  void calc() {
    Day5Vents day5 = new Day5Vents(10, 10);
    day5.readData("/day-5-vents-test");
    int result = day5.calc();
    int[][] oceanFloorMap = day5.getOceanFloorMap();

    PuzzlesUtil.printMatrix(oceanFloorMap);

    assertEquals(5, result);
  }

  @Test
  void calc2() {
    Day5Vents day5 = new Day5Vents(10, 10);
    day5.readData("/day-5-vents-test");
    int result = day5.calc2();
    int[][] oceanFloorMap = day5.getOceanFloorMap();

    PuzzlesUtil.printMatrix(oceanFloorMap);

    assertEquals(12, result);
  }
}
