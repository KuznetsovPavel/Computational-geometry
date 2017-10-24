import java.util.ArrayList;
import java.util.List;

public class QuickHull {

    public static List<Integer> execute(final List<Point> pointValues) {
        Point leftmostPoint = pointValues.get(0);
        Point rightmostPoint = pointValues.get(0);
        for (Point point : pointValues) {
            if (point.getX().compareTo(leftmostPoint.getX()) < 0) {
                leftmostPoint = point;
            } else if (point.getX().compareTo(rightmostPoint.getX()) > 0) {
                rightmostPoint = point;
            }
        }

        final List<Integer> indicesLeftOfLinePoints = new ArrayList<>();
        final List<Integer> indicesRightOfLinePoints = new ArrayList<>();
        for (int i = 0; i < pointValues.size(); i++) {
            final Point point = pointValues.get(i);
            if (point.equals(leftmostPoint) || point.equals(rightmostPoint)) {
                continue;
            }
            if (point.isLeftOfLine(leftmostPoint, rightmostPoint)) {
                indicesLeftOfLinePoints.add(i);
            } else {
                indicesRightOfLinePoints.add(i);
            }
        }

        final List<Integer> resultIndices = new ArrayList<>();
        resultIndices.add(pointValues.indexOf(leftmostPoint));
        final List<Integer> leftResult = recursion(pointValues, indicesLeftOfLinePoints, leftmostPoint, rightmostPoint);
        if (leftResult != null)
            resultIndices.addAll(leftResult);
        resultIndices.add(pointValues.indexOf(rightmostPoint));
        final List<Integer> rightResult = recursion(pointValues, indicesRightOfLinePoints, rightmostPoint, leftmostPoint);
        if (rightResult != null)
            resultIndices.addAll(rightResult);
        return resultIndices;
    }

    private static List<Integer> recursion(List<Point> pointValues, List<Integer> indicesLeftOfLinePoints,
                                           Point leftmostPoint, Point rightmostPoint) {
        return null;
    }

}
