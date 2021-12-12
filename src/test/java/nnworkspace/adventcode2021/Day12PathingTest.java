package nnworkspace.adventcode2021;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12PathingTest {

    @BeforeEach
    void setUp() {
    }

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
