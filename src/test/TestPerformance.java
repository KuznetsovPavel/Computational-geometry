package test;

import main.Point;
import main.QuickHull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class TestPerformance {

    public static void main(String[] args) {
        final int SIZE = 1000;
        final int BITS = 12;
        final int COUNT = 10;
        Random random = new Random();
        for (int i = 1; i < 15; i++) {
            long sum = 0;
            final int currentSize = SIZE * i;
            for (int j = 0; j < COUNT; j++) {
                ArrayList<Point> points = new ArrayList<>();
                for (int k = 0; k < currentSize; k++) {
                    points.add(new Point(new BigInteger(BITS, random), new BigInteger(BITS, random)));
                }
                final long start = System.currentTimeMillis();
                ArrayList<Integer> result = (ArrayList<Integer>) new QuickHull(points).execute();
                final long finish = System.currentTimeMillis();
                final long elapsed = (finish - start);
                sum += elapsed;
            }
            sum /= COUNT;
            System.out.format("for %6d elements: %5d msec. Perform: %f \n", currentSize, sum,
                    (currentSize * Math.log(currentSize) / sum));
        }
    }
}
