import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public static void main(String[] args) {
        final List<Point> points = readListPoint(args[0]);
        final List<Point> result = QuickHull.execute(points);
        result.forEach(System.out::println);
    }

    private static List<Point> readListPoint(final String s) {
        List<Point> points = null;
        try {
            points = Files.lines(Paths.get(s), StandardCharsets.UTF_8)
                    .map(Point::parsePoint)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Can not read file");
            e.printStackTrace();
        }
        return points;
    }
}
