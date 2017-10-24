import java.util.ArrayList;
import java.util.List;

public class QuickHull {

    final List<Point> points;

    public QuickHull(List<Point> points) {
        this.points = points;
    }

    public List<Integer> execute() {
        Point leftmostPoint = points.get(0);
        Point rightmostPoint = points.get(0);
        for (Point point : points) {
            if (point.getX().compareTo(leftmostPoint.getX()) < 0) {
                leftmostPoint = point;
            } else if (point.getX().compareTo(rightmostPoint.getX()) > 0) {
                rightmostPoint = point;
            }
        }

        final List<Integer> indicesLeftOfLinePoints = new ArrayList<>();
        final List<Integer> indicesRightOfLinePoints = new ArrayList<>();
        for (Point point : points) {
            if (point.equals(leftmostPoint) || point.equals(rightmostPoint)) {
                continue;
            }
            if (point.isLeftOfLine(leftmostPoint, rightmostPoint)) {
                indicesLeftOfLinePoints.add(points.indexOf(point));
            } else {
                indicesRightOfLinePoints.add(points.indexOf(point));
            }
        }

        final List<Integer> result = new ArrayList<>();
        result.add(points.indexOf(leftmostPoint));
        result.addAll(recursion(indicesLeftOfLinePoints, leftmostPoint, rightmostPoint));
        result.add(points.indexOf(rightmostPoint));
        result.addAll(recursion(indicesRightOfLinePoints, rightmostPoint, leftmostPoint));
        return result;
    }

    private List<Integer> recursion(List<Integer> indecisPoints, Point leftmostPoint, Point rightmostPoint) {
        List<Integer> result = new ArrayList<>();
        if (indecisPoints.isEmpty())
            return result;
        if (indecisPoints.size() < 2) {
            result.add(indecisPoints.get(0));
            return result;
        }

        int farthestPointIndex = indecisPoints.stream().max((p1, p2) ->
                points.get(p1).furtherFromLine(leftmostPoint, rightmostPoint, points.get(p2))).get();
        indecisPoints.remove((Integer)farthestPointIndex);
        Point farthestPoint = points.get(farthestPointIndex);

        final List<Integer> leftOfLinePointsIndices = new ArrayList<>();
        final List<Integer> rightOfLinePointsIndices = new ArrayList<>();
        for (Integer index : indecisPoints) {
            if (points.get(index).isLeftOfLine(leftmostPoint, farthestPoint)) {
                leftOfLinePointsIndices.add(index);
            } else if (points.get(index).isLeftOfLine(farthestPoint, rightmostPoint)) {
                rightOfLinePointsIndices.add(index);
            }
        }

        result.addAll(recursion(leftOfLinePointsIndices, leftmostPoint, farthestPoint));
        result.add(farthestPointIndex);
        result.addAll(recursion(rightOfLinePointsIndices, farthestPoint, rightmostPoint));
        return result;
    }

}
