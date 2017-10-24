package main;

import java.math.BigInteger;

public class Point {

    private final BigInteger x;
    private final BigInteger y;

    public Point(final BigInteger x, final BigInteger y) {
        this.x = x;
        this.y = y;
    }

    static Point parsePoint(final String s) {
        String[] array = s.split(" ");
        return new Point(new BigInteger(array[0]), new BigInteger(array[1]));
    }

    boolean isLeftOfLine(final Point from, final Point to) {
        return calcCrossProduct(from, to).compareTo(new BigInteger("0")) > 0;
    }

    private BigInteger calcCrossProduct(final Point p1, final Point p2) {
        return ((p2.x.subtract(p1.x)).multiply(this.y.subtract(p1.y)))
                .subtract((p2.y.subtract(p1.y)).multiply(this.x.subtract(p1.x)));
    }

    int furtherFromLine(final Point pLine1, final Point pLine2, final Point p) {
        BigInteger common = pLine2.x.multiply(pLine1.y).subtract((pLine2.y).multiply(pLine1.x));
        BigInteger dist1 = ((pLine2.y.subtract(pLine1.y)).multiply(this.x)).
                subtract((pLine2.x.subtract(pLine1.x)).multiply(this.y)).add(common).abs();
        BigInteger dist2 = ((pLine2.y.subtract(pLine1.y)).multiply(p.getX())).
                subtract((pLine2.x.subtract(pLine1.x)).multiply(p.getY())).add(common).abs();
        return dist1.compareTo(dist2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            if (x.compareTo(((Point) obj).x) == 0 && y.compareTo(((Point) obj).y) == 0)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "main.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }
}
