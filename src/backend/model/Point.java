package backend.model;

public class Point{

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // chequeo esta distancia para saber si dibujo o no una figura
    // utilidad? -> ahorrar procesamiento
    public double distanceSquaredTo(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    public void changePos(double diffX, double diffY){
        this.x += diffX;
        this.y += diffY;
    }

    public void setNewValues(double newX, double newY){
        this.x = newX;
        this.y = newY;
    }

    public boolean equals( Object other ){
        return this == other || ( other instanceof Point point
            && this.x == point.x && this.y == point.y
        );
    }

    public static Point getTopLeft(Point p1, Point p2){
        return new Point(p1.getX()<p2.getX() ? p1.getX():p2.getX(), p1.getY()<p2.getY() ? p1.getY():p2.getY());
    }

    public static Point getBottomRight(Point p1, Point p2){
        return new Point(p1.getX()>p2.getX() ? p1.getX():p2.getX(), p1.getY()>p2.getY() ? p1.getY():p2.getY());
    }

}
