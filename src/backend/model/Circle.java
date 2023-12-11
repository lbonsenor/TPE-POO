package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

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
    public void shadow(GraphicsContext gc){
        double diameter = this.radius * 2;
        gc.fillOval(this.getCenterPoint().getX() - this.getRadius() + SHADOWOFFSET,
      this.getCenterPoint().getY() - this.getRadius() + SHADOWOFFSET, diameter, diameter);
    }

    @Override
    public void bisel(GraphicsContext gc){
        double diameter = this.radius * 2;
        double arcX = this.getCenterPoint().getX() - this.getRadius();
        double arcY = this.getCenterPoint().getY() - this.getRadius();
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeArc(arcX, arcY, diameter, diameter, 45, 180, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(arcX, arcY, diameter, diameter, 225, 180, ArcType.OPEN);
    }

    @Override
    public void gradient(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gradient'");
    }

    @Override
    public boolean contains(Point eventPoint){
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
