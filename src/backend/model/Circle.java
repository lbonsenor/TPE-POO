package backend.model;

import javafx.scene.canvas.GraphicsContext;

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
    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
		return new Circle(startPoint, circleRadius);
    }

    @Override
    public void redraw(GraphicsContext gc){
        double diameter = this.radius * 2;
	    gc.fillOval(this.centerPoint.getX() - this.radius, 
                    this.centerPoint.getY() - this.radius, 
                    diameter, diameter);
		gc.strokeOval(this.centerPoint.getX() - this.radius, 
                      this.centerPoint.getY() - this.radius, diameter, diameter);
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

}
