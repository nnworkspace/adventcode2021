package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day11Octopus;
import nnworkspace.adventcode2021.PuzzlesUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day11OctopusTest {

    private static final Logger logger = Logger.getLogger(Day11OctopusTest.class.getName());

    private Day11Octopus day11;

    @BeforeEach
    void setUp() {
        day11 = new Day11Octopus("src/test/resources/day-11-octopus-test");
    }

    @Test
    void calc() {
        int result = day11.calc(100);

        assertEquals(1656,result);
    }

    @Test
    void calc2() {

        int result = day11.calc2();

        assertEquals(195,result);
    }

  @Test
  void runOneStep() {
        int[][] init = PuzzlesUtil.parseMatrix("src/test/resources/day-11-octopus-test", true);
        int[][] init1 = PuzzlesUtil.parseMatrix("src/test/resources/day-11-octopus-test-1", true);
        int[][] init2 = PuzzlesUtil.parseMatrix("src/test/resources/day-11-octopus-test-2", true);


        int[][] afterSteps1 = day11.runOneStep(init);
        int[][] afterSteps2 = day11.runOneStep(init1);

        assertTrue(Arrays.deepEquals(init1, afterSteps1));
        assertTrue(Arrays.deepEquals(init2, afterSteps2));

  }
}
