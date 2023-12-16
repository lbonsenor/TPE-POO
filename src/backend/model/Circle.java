package backend.model;

import java.util.Objects;

public class Circle extends Ellipse {
    private double radius;

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, radius*2, radius*2);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    @Override
    public boolean found(Point eventPoint){
        return Math.sqrt(Math.pow(this.centerPoint.getX() - eventPoint.getX(), 2) +
			   Math.pow(this.centerPoint.getY() - eventPoint.getY(), 2)) < this.radius;
    }

    @Override
    public void scale(double multiplier){
        this.radius *= Math.sqrt(multiplier);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Circle circle
                && radius == circle.radius
                && super.equals(circle));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }

}
