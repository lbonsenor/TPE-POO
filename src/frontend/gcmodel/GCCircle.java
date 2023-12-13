package frontend.gcmodel;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GCCircle extends Circle implements GCFigure{

    private Paint color;
    private boolean shadow, bisel, grad;

    public GCCircle(Point centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public void createFigure(GraphicsContext gc){
        double diameter = getRadius() * 2;
	    gc.fillOval(getCenterPoint().getX() - getRadius(), 
                    getCenterPoint().getY() - getRadius(), 
                    diameter, diameter);
		gc.strokeOval(getCenterPoint().getX() - getRadius(), 
                    getCenterPoint().getY() - getRadius(), diameter, diameter);
    }

    @Override
    public void setFillColor(Paint color) {
        this.color = color;
    }

    @Override
    public Paint getFillColor() {
        return color;
    }

    @Override
    public void setShadow(boolean value) {
        this.shadow = value;
    }

    @Override
    public boolean getShadow() {
        return this.shadow;
    }

    @Override
    public void setBisel(boolean value) {
        this.bisel = value;
    }

    @Override
    public boolean getBisel() {
        return this.bisel;
    }

    @Override
    public void setGrad(boolean value) {
        this.grad = value;
    }

    @Override
    public boolean getGrad() {
        return this.grad;
    }

}