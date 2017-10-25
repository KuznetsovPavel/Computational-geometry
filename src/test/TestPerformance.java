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
        for (int i = 1; i < 10; i++) {
            ArrayList<Point> points = new ArrayList<>();
            final int currentSize = SIZE * i;
            for (int j = 0; j < currentSize; j++) {
                points.add(new Point(new BigInteger(BITS, random), new BigInteger(BITS, random)));
            }
            final long start = System.currentTimeMillis();
            for (int j = 0; j < COUNT; j++) {
                ArrayList<Integer> result = (ArrayList<Integer>) new QuickHull(points).execute();
            }
            final long finish = System.currentTimeMillis();
            final long elapsed = (finish - start)/COUNT;
            System.out.format("for %d elements: %4d msec. Perform: %f \n", currentSize, elapsed,
                    (currentSize * Math.log(currentSize) / elapsed));
        }
    }
}
