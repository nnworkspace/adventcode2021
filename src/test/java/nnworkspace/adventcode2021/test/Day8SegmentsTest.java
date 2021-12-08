package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day8Segments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8SegmentsTest {
  Day8Segments day8;

  @BeforeEach
  void setUp() {
    day8 = new Day8Segments();
    day8.readData("/day-8-segments-test");
  }

  @Test
  void readData() {
    assertEquals(10, day8.getInputsList().size());
    assertEquals(10, day8.getOutputsList().size());
    assertEquals("be", day8.getInputsList().get(0).get(0));
    assertEquals("fdgacbe", day8.getOutputsList().get(0).get(0));
  }

  @Test
  void calc() {
    assertEquals(26, day8.calc());
  }

  @Test
  void calc2() {
    assertEquals(61229, day8.calc2());
  }
}
