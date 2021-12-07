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
}
