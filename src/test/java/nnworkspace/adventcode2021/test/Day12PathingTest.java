package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day12Pathing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12PathingTest {

    @Test
    void calc() {
        Day12Pathing p1 = new Day12Pathing("/day-12-pathing-test");
        int result = p1.calc();

        assertEquals(10, result);
    }

    @Test
    void calc2() {
        Day12Pathing p1 = new Day12Pathing("/day-12-pathing-test");
        int result = p1.calc2();

        assertEquals(36, result);
    }
}
