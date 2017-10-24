package test;

import main.Point;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;

public class Window extends JFrame{

    final int SCALE = 500;

    public Window(final int width, final int height) throws HeadlessException {
        super("Test");
        setBounds(SCALE, SCALE, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void draw(Window window, final ArrayList<Point> points,
                     final ArrayList<Integer> indeces) {
        JPanel contentPane = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                resultGraphics(points, indeces, g);
            }
        };
        window.setContentPane(contentPane);
        contentPane.updateUI();
    }

    private void resultGraphics(final ArrayList<Point> points, final ArrayList<Integer> indeces,
                                final Graphics g) {
        g.setColor(Color.BLACK);
        BigInteger maxX = points.stream().max(Comparator.comparing(Point::getX)).get().getX();
        BigInteger maxY = points.stream().max(Comparator.comparing(Point::getY)).get().getY();
        BigInteger minX = points.stream().min(Comparator.comparing(Point::getX)).get().getX();
        BigInteger minY = points.stream().min(Comparator.comparing(Point::getY)).get().getY();
        BigInteger scaleX = maxX.subtract(minX);
        BigInteger scaleY = maxY.subtract(minY);
        for (Point point : points) {
            g.fillOval(point.getX().multiply(BigInteger.valueOf(SCALE)).divide(scaleX).intValue(),
                    point.getY().multiply(BigInteger.valueOf(SCALE)).divide(scaleY).intValue(),
                    10, 10);
        }
        int earlyIndex = indeces.get(0);
        int x1 = points.get(earlyIndex).getX().multiply(BigInteger.valueOf(SCALE)).divide(scaleX).intValue();
        int y1 = points.get(earlyIndex).getY().multiply(BigInteger.valueOf(SCALE)).divide(scaleY).intValue();
        int firstX = x1;
        int firstY = y1;
        int x2, y2;
        for (int index : indeces) {
            if (earlyIndex == index) continue;
            x2 = points.get(index).getX().multiply(BigInteger.valueOf(SCALE)).divide(scaleX).intValue();
            y2 = points.get(index).getY().multiply(BigInteger.valueOf(SCALE)).divide(scaleY).intValue();
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
        g.drawLine(x1, y1, firstX, firstY);

    }

}
