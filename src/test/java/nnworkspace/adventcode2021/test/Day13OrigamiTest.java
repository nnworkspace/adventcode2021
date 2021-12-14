package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day13Origami;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13OrigamiTest {

    private Day13Origami origami;

    @BeforeEach
    void setUp() {
        this.origami = new Day13Origami("src/test/resources/day-13-origami-test");
    }

    @Test
    void calc() {
        assertEquals(17, origami.calc(1));
    }

    @Test
    void calc2() {
    }
}
