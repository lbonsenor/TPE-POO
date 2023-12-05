package backend.model;

public class Circle implements Figure {

    protected final Point centerPoint;
    protected final double radius;

    public Circle(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
		return new Circle(startPoint, circleRadius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

}
