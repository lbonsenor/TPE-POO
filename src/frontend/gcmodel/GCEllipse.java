package frontend.gcmodel;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GCEllipse extends Ellipse implements GCFigure{

    private Color color;
    private boolean shadow, bisel, grad;

    public GCEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), 
			getCenterPoint().getY() - (getsMinorAxis() / 2), 
			getsMayorAxis(), getsMinorAxis());
		gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), 
			getCenterPoint().getY() - (getsMinorAxis() / 2), 
			getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public void setFillColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getFillColor() {
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
