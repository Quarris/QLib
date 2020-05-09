package quarris.qlib.api.util.math;

import java.util.Objects;

public class Rectangle implements Cloneable {

    /* STATICS */

    public static Rectangle fromCorners(Point firstCorner, Point oppositeCorner) {
        Point topLeft = Point.min(firstCorner, oppositeCorner);
        Point bottomRight = Point.max(firstCorner, oppositeCorner);
        return new Rectangle(topLeft, bottomRight.subtract(topLeft));
    }

    /* OBJECT */

    public final int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point position, Point size) {
        this(position.x, position.y, size.x, size.y);
    }

    public Rectangle add(int sizeX, int sizeY) {
        return new Rectangle(this.x, this.y, this.width + sizeX, this.height + sizeY);
    }

    public Rectangle subtract(int x, int y) {
        return this.add(-x, -y);
    }

    public Rectangle grow(int left, int top, int right, int bottom) {
        return new Rectangle(this.x - left, this.y - top, this.width + left + right, this.height + top + bottom);
    }

    public Rectangle grow(int size) {
        return this.grow(size, size, size, size);
    }

    public Rectangle shrink(int left, int top, int right, int bottom) {
        return new Rectangle(this.x + left, this.y + top, this.width - right - left, this.height - bottom - top);
    }

    public Rectangle shrink(int size) {
        return this.shrink(size, size, size, size);
    }

    public Rectangle move(int x, int y) {
        return new Rectangle(this.x+x, this.y+y, this.width, this.height);
    }

    public Point getCenter() {
        return new Point(this.x + this.width / 2, this.y + this.height / 2);
    }

    public Point getSize() {
        return new Point(this.width, this.height);
    }

    public Point getPosition() {
        return this.getTopLeft();
    }

    public Point getTopLeft() {
        return new Point(this.x, this.y);
    }

    public Point getTopRight() {
        return this.getTopLeft().add(this.width, 0);
    }

    public Point getBottomLeft() {
        return this.getTopLeft().add(0, this.height);
    }

    public Point getBottomRight() {
        return this.getTopLeft().add(this.getSize());
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append(x);
        sb.append(", ").append(y);
        sb.append(", ").append(width);
        sb.append(", ").append(height);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rectangle rectangle = (Rectangle) o;
        return x == rectangle.x &&
                y == rectangle.y &&
                width == rectangle.width &&
                height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
