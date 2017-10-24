package test;

import main.Point;
import main.QuickHull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        final int SIZE = 40;
        final int BITS = 10;
        Random random = new Random();
        Window window = new Window(500, 500);
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            points.add(new Point(new BigInteger(BITS, random), new BigInteger(BITS, random)));
        }
        ArrayList<Integer> result = (ArrayList<Integer>) new QuickHull(points).execute();
        window.draw(window, points, result);
        points.forEach(System.out::println);
        result.forEach(System.out::println);
    }

}
