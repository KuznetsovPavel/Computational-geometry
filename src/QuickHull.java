import java.util.ArrayList;
import java.util.List;

public class QuickHull {

    public static List<Point> execute(final List<Point> pointValues) {
        Point leftmostPoint = pointValues.get(0);
        Point rightmostPoint = pointValues.get(0);
        for (Point point : pointValues) {
            if (point.getX().compareTo(leftmostPoint.getX()) < 0) {
                leftmostPoint = point;
            } else if (point.getX().compareTo(rightmostPoint.getX()) > 0) {
                rightmostPoint = point;
            }
        }

        final List<Point> leftOfLinePoints = new ArrayList<>();
        final List<Point> rightOfLinePoints = new ArrayList<>();
        for (Point point : pointValues) {
            if (point.equals(leftmostPoint) || point.equals(rightmostPoint)) {
                continue;
            }
            if (point.isLeftOfLine(leftmostPoint, rightmostPoint)) {
                leftOfLinePoints.add(point);
            } else {
                rightOfLinePoints.add(point);
            }
        }

        final List<Point> result = new ArrayList<>();
        result.add(leftmostPoint);
        result.addAll(recursion(leftOfLinePoints, leftmostPoint, rightmostPoint));
        result.add(rightmostPoint);
        result.addAll(recursion(rightOfLinePoints, rightmostPoint, leftmostPoint));
        return result;
    }


    private static List<Point> recursion(List<Point> points, Point leftmostPoint, Point rightmostPoint) {
        List<Point> result = new ArrayList<>();
        if (points.isEmpty())
            return result;
        if (points.size() < 2) {
            result.add(points.get(0));
            return result;
        }

        Point farthestPoint = points.stream().max((p1, p2) ->
                p1.furtherFromLine(leftmostPoint, rightmostPoint, p2)).get();
        points.remove(farthestPoint);

        final List<Point> leftOfLinePoints = new ArrayList<>();
        final List<Point> rightOfLinePoints = new ArrayList<>();
        for (Point point : points) {
            if (point.isLeftOfLine(leftmostPoint, farthestPoint)) {
                leftOfLinePoints.add(point);
            } else if (point.isLeftOfLine(farthestPoint, rightmostPoint)){
                rightOfLinePoints.add(point);
            }
        }

        result.addAll(recursion(leftOfLinePoints, leftmostPoint, farthestPoint));
        result.add(farthestPoint);
        result.addAll(recursion(rightOfLinePoints, farthestPoint, rightmostPoint));
        return result;
    }

}
