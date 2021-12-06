package nnworkspace.adventcode2021.test;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import nnworkspace.adventcode2021.Day6Lanternfish;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day6LanternfishTest {

  @Test
  void readData() {
    Day6Lanternfish day6 = new Day6Lanternfish((short) 7, (short) 9);

    day6.readData("src/test/resources/day-6-lanternfish-test");

    short[] expected = {3, 4, 3, 1, 2};
    List<Short> result = day6.getInitialTimers();

    assertEquals(Shorts.asList(expected), result);
  }

  @Test
  void calc() {
    Day6Lanternfish day6 = new Day6Lanternfish((short) 7, (short) 9);

    day6.readData("src/test/resources/day-6-lanternfish-test");
    assertEquals(26, day6.calc( 18));
    assertEquals(5934, day6.calc( 80));
  }

  @Test
  void calc2() {
    Day6Lanternfish day6 = new Day6Lanternfish((short) 7, (short) 9);

    day6.readData("src/test/resources/day-6-lanternfish-test");

    assertEquals(26, day6.calc2( 18));
    assertEquals(5934, day6.calc2( 80));
    assertEquals(26984457539L, day6.calc2(256));
  }
}
