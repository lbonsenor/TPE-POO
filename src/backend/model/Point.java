package backend.model;

public class Point {

    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void changePos(double diffX, double diffY){
        this.x += diffX;
        this.y += diffY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean equals(Point o){
        if (this == o) return true;
        return this.x == o.x && this.y == o.y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
