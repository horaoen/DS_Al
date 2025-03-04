package com.horaoen.spto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionPart2Test {
    @Test
    public void countThresholdTest() {
        SolutionPart2 solutionPart2 = new SolutionPart2();
        assertEquals(1, solutionPart2.countThreshold(0) + solutionPart2.countThreshold(1));
    }
}
