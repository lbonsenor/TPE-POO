package frontend.gcmodel;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class GCCircle extends Circle implements GCFigure{

    private Paint color;
    private boolean shadow, bisel, grad;

    public GCCircle(Point centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public void createFigure(GraphicsContext gc){
        double diameter = getRadius() * 2;
        if (shadow) {
            gc.setFill(Color.GRAY);
            gc.fillOval(getCenterPoint().getX() - getRadius() + 10.0,
            getCenterPoint().getY() - getRadius() + 10.0, diameter, diameter);
        }
        if( bisel ){
            double arcX = getCenterPoint().getX() - getRadius();
            double arcY = getCenterPoint().getY() - getRadius();
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX, arcY, diameter, diameter, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX, arcY, diameter, diameter, 225, 180, ArcType.OPEN);
        }
        gc.setFill( (grad) ? gradColor() : color );
	    gc.fillOval(getCenterPoint().getX() - getRadius(), 
                    getCenterPoint().getY() - getRadius(), 
                    diameter, diameter);
		gc.strokeOval(getCenterPoint().getX() - getRadius(), 
                    getCenterPoint().getY() - getRadius(), diameter, diameter);
    }

    private LinearGradient gradColor(){
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
            CycleMethod.NO_CYCLE,
            new Stop(0, (Color)color),
            new Stop(1, ((Color)color).invert() ));
        return linearGradient;
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