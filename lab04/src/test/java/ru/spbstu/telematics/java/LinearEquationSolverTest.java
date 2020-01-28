package ru.spbstu.telematics.java;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class LinearEquationSolverTest {

    double[] EXPECTED_RESULT = {-1, 2, 2};

    @Test
    public void testEquationSolver() {
        double[][] a = {{ 0, 1,  1 }, { 2, 4, -2 }, { 0, 3, 15 }};
        double[] b = { 4, 2, 36 };

        double[] result = LinearEquationSolver.solve(a, b);
        Assert.assertArrayEquals(EXPECTED_RESULT, result, 0.01);
    }

    @Test
    public void testParallelSolver() {
        double[][] a = {{ 0, 1,  1 }, { 2, 4, -2 }, { 0, 3, 15 }};
        double[] b = { 4, 2, 36 };

        double[] result = LinearEquationSolver.solveParallel(a, b, 3);
        Assert.assertArrayEquals(EXPECTED_RESULT, result, 0.01);
    }
}

