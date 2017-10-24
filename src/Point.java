import java.math.BigInteger;

class Point {

    private final BigInteger x;
    private final BigInteger y;

    Point(final BigInteger x, final BigInteger y) {
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

    boolean furtherFromLine(final Point pLine1, final Point pLine2, final Point p) {
        return ((pLine2.y.subtract(pLine1.y)).multiply(this.x.subtract(p.getX())))
                .compareTo((pLine2.x.subtract(pLine1.x)).multiply(this.y.subtract(p.getY()))) > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            if (x.compareTo(((Point) obj).x) == 0 && y.compareTo(((Point) obj).y) == 0)
                return true;
        }
        return false;
    }

    BigInteger getX() {
        return x;
    }

    BigInteger getY() {
        return y;
    }
}
