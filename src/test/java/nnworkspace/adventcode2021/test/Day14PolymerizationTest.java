package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day14Polymerization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14PolymerizationTest {

    @Test
    void calc() {
        Day14Polymerization poly = new Day14Polymerization("src/test/resources/day-14-polymerization-test");

        assertEquals(1588L, poly.calc(10));
    }

    @Test
    void calc2() {
        Day14Polymerization poly = new Day14Polymerization("src/test/resources/day-14-polymerization-test");

        assertEquals(2188189693529L, poly.calc(40));
    }
}
