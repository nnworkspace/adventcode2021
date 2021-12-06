package nnworkspace.adventcode2021.test;

import com.google.common.primitives.Ints;
import nnworkspace.adventcode2021.Day6Lanternfish;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day6LanternfishTest {

  @Test
  void readData() {
    Day6Lanternfish day6 = new Day6Lanternfish(7, 9);

    day6.readData("src/test/resources/day-6-lanternfish-test");

    int[] expected = {3, 4, 3, 1, 2};
    List<Integer> result = day6.getInitialTimers();

    assertEquals(Ints.asList(expected), result);
  }

  @Test
  void calc() {
    Day6Lanternfish day6 = new Day6Lanternfish(7, 9);

    day6.readData("src/test/resources/day-6-lanternfish-test");
    assertEquals(26, day6.calc(18));
    assertEquals(5934, day6.calc(80));
  }

  @Test
  void calc2() {
    Day6Lanternfish day6 = new Day6Lanternfish(7, 9);

    day6.readData("src/test/resources/day-6-lanternfish-test");
  }
}
