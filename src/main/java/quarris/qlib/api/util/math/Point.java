package quarris.qlib.api.util.math;

import java.util.Objects;

public class Point {

    /* STATICS */

    public static final Point ZERO = new Point(0, 0);
    public static final Point ONE = new Point(1, 1);

    public static Point min(Point first, Point second) {
        return new Point(Math.min(first.x, second.x), Math.min(first.y, second.y));
    }

    public static Point max(Point first, Point second) {
        return new Point(Math.max(first.x, second.x), Math.max(first.y, second.y));
    }

    /* OBJECT */

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point add(Point point) {
        return this.add(point.x, point.y);
    }

    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point subtract(int x, int y) {
        return add(-x, -y);
    }

    public Point subtract(Point point) {
        return subtract(point.x, point.y);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append(x);
        sb.append(", ").append(y);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
