import java.math.BigInteger;

class Point {

    final private BigInteger x;
    final private BigInteger y;

    Point(final BigInteger x, final BigInteger y) {
        this.x = x;
        this.y = y;
    }

    static Point parsePoint(final String s) {
        String[] array = s.split(" ");
        return new Point(new BigInteger(array[0]), new BigInteger(array[1]));
    }
}
