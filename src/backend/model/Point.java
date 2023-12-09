package backend.model;

import backend.Movable;

public class Point implements Movable{

    public double x, y;

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
        return dx * dx + dy * dy;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public void changePos(double diffX, double diffY) {
        x += diffX;
        y += diffY;
    }

}
