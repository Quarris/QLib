package quarris.qlib.api.client.util;

public class Padding {
    public final int left, right, top, bottom;

    public Padding(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public Padding(int horizontal, int vertical) {
        this(horizontal, horizontal, vertical, vertical);
    }

    public Padding(int padding) {
        this(padding, padding, padding, padding);
    }

    public int getHeight() {
        return this.top + this.bottom;
    }

    public int getWidth() {
        return this.left + this.right;
    }
}