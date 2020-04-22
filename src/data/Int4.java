package data;

public class Int4 {
    private int x, y, z, w;

    public Int4(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public boolean Equals(Int4 compareTo) {
        return x == compareTo.getX() &&
                y == compareTo.getY() &&
                z == compareTo.getZ() &&
                w == compareTo.getW();
    }

    public Int4 plus(Int4 other) {
        x += other.getX();
        y += other.getY();
        z += other.getZ();
        w += other.getW();
        return this;
    }

    public Int4 multiply(Int4 other) {
        x *= other.getX();
        y *= other.getY();
        z *= other.getZ();
        w *= other.getW();
        return this;
    }

    public Int4 divide(Int4 other) {
        x /= other.getX();
        y /= other.getY();
        z /= other.getZ();
        w /= other.getW();
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getW() {
        return w;
    }
}
