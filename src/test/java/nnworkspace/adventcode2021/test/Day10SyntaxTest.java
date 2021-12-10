package nnworkspace.adventcode2021.test;

import nnworkspace.adventcode2021.Day10Syntax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day10SyntaxTest {

    private static final Logger logger = Logger.getLogger(Day10SyntaxTest.class.getName());
    private Day10Syntax day10;

    @BeforeEach
    void setUp() {
        day10 = new Day10Syntax("/day-10-syntax-test");
    }

    @Test
    void calc() {
        assertEquals(26397, day10.calc());
    }

    @Test
    void calc2() {
        assertEquals(288957, day10.calc2());
    }

  @Test
  void calcScore() {
        StringBuilder sb = new StringBuilder("(((([[(((([[[[");
        assertTrue(day10.calcScore(sb) > 0);
  }
}
