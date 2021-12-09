package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day9Basin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9BasinTest {

    private Day9Basin day9;

    @BeforeEach
    void setUp() {
        day9 = new Day9Basin("src/test/resources/day-9-basin-test");
    }

    @Test
    void calc() {
        assertEquals(15, day9.calc());
    }

    @Test
    void calc2() {
        assertEquals(1134, day9.calc2());
    }
}
