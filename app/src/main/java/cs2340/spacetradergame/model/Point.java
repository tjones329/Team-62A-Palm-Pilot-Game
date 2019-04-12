package cs2340.spacetradergame.model;

/**
 * class
 */
public class Point {
    private int x;
    private int y;

    /**
     * constructor
     */
    public Point() {

    }

    /**
     * constructor
     * @param x int
     * @param y int
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * dist
     * @param a point
     * @param b point
     * @return douoble as distance
     */
    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    /**
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return x == p.x && y == p.y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
