package backend.model;

public class Circle extends Ellipse {
    private final double radius;

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, radius, radius);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    @Override
    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
		return new Circle(startPoint, circleRadius);
    }

    public double getRadius() {
        return radius;
    }

}
