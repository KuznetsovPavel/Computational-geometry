package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Client {

    public static void main(String[] args) {
        final List<Point> points = readListPoint(args[0]);
        final List<Integer> result = new QuickHull(points).execute();
        write(args[0], result);
    }

    private static void write(String arg, List<Integer> result) {
        try (final BufferedWriter writer = new BufferedWriter(
                new FileWriter("res-" + arg))) {
            result.stream()
                    .map(i -> i + 1)
                    .map(Object::toString)
                    .map(s -> s + "\n")
                    .forEach(c -> {
                try {
                    writer.write(c);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Point> readListPoint(final String s) {
        List<Point> points = null;
        try {
            points = Files.lines(Paths.get(s), StandardCharsets.UTF_8)
                    .map(Point::parsePoint)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Incorrect file");
            e.printStackTrace();
        }
        return points;
    }
}
