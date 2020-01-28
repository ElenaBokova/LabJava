package ru.spbstu.telematics.java;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinearEquationSolver {

    private static final Random RANDOM = new Random();

    public static double[] solve(double[][] values, double[] res) {
        gauss(values, res, 0, res.length);
        return produceResult(values, res);
    }

    public static double[] solveParallel(double[][] values, double[] res, int threadCount) {
        int n = res.length;
        List<Thread> threads = new ArrayList<Thread>();

        int threadStep = n / threadCount;
        int from = 0, to;

        for (int th = 0; th < threadCount; ++th) {
            if (n - threadStep > from + threadStep) {
                to = n;
            } else {
                to = from + threadStep;
            }
            Thread solverThread = new Solver(values, res, from, to);
            solverThread.start();
            threads.add(solverThread);
            from += threadStep;
        }

        try {
            for (Thread th : threads) {
                th.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return produceResult(values, res);
    }

    private static void gauss(double[][] values, double[] res, int from, int to) {
        int n = res.length;

        for (int p = from; p < to; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(values[i][p]) > Math.abs(values[max][p])) {
                    max = i;
                }
            }
            double[] temp = values[p];
            values[p] = values[max];
            values[max] = temp;
            double t = res[p];
            res[p] = res[max];
            res[max] = t;

            if (Math.abs(values[p][p]) <= 0.0000000001) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }

            for (int i = p + 1; i < n; i++) {
                double alpha = values[i][p] / values[p][p];
                res[i] -= alpha * res[p];
                for (int j = p; j < n; j++) {
                    values[i][j] -= alpha * values[p][j];
                }
            }
        }
    }

    private static double[] produceResult(double[][] values, double[] res) {
        int n = res.length;
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += values[i][j] * x[j];
            }
            x[i] = (res[i] - sum) / values[i][i];
        }
        return x;
    }

    static class Solver extends Thread {

        private double[][] values;
        private double[] res;
        private int from;
        private int to;

        public Solver(double[][] values, double[] res, int from, int to) {
            this.values = values;
            this.res = res;
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            gauss(values, res, from, to);
        }

    }

    public static void main(String[] args) {
        int size = 500;
        double[][] values = new double[size][size];
        double[] res = new double[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                values[i][j] = RANDOM.nextInt(100);
            }
        }
        for (int i = 0; i < size; i++) {
            res[i] = RANDOM.nextInt(100);
        }

        long nanos = System.nanoTime();
        double[] result = LinearEquationSolver.solve(values, res);
        System.out.println("Result for single thread: " + ((double)System.nanoTime() - nanos) / 1000 + " microseconds");

        nanos = System.nanoTime();
        result = LinearEquationSolver.solveParallel(values, res, 2);
        System.out.println("Result for 2 threads: " + ((double)System.nanoTime() - nanos) / 1000 + " microseconds");

        nanos = System.nanoTime();
        result = LinearEquationSolver.solveParallel(values, res, 5);
        System.out.println("Result for 5 threads: " + ((double)System.nanoTime() - nanos) / 1000 + " microseconds");

        nanos = System.nanoTime();
        result = LinearEquationSolver.solveParallel(values, res, 10);
        System.out.println("Result for 10 threads: " + ((double)System.nanoTime() - nanos) / 1000 + " microseconds");

        nanos = System.nanoTime();
        result = LinearEquationSolver.solveParallel(values, res, 50);
        System.out.println("Result for 50 threads: " + ((double)System.nanoTime() - nanos) / 1000 + " microseconds");
    }

}
