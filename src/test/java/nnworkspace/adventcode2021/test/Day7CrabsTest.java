package nnworkspace.adventcode2021.test;

import com.google.common.collect.ImmutableList;
import nnworkspace.adventcode2021.Day7Crabs;
import nnworkspace.adventcode2021.PuzzlesUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class Day7CrabsTest {

    private static final Logger logger = Logger.getLogger(Day7CrabsTest.class.getName());

    Day7Crabs day7;

    @BeforeEach
    void setUp() {
        day7 = new Day7Crabs("src/test/resources/day-7-crabs-test");
    }

    @Test
    void calc() {
        int result = day7.calc();

        assertEquals(37, result);
    }

    @Test
    void calcFuelFactor2() {
        int result = day7.calcFuelFactor2(1, 11);
        assertEquals(66, result);
    }

    @Test
    void calc2() {
        int result = day7.calc2();

        assertEquals(168, result);
    }


}
