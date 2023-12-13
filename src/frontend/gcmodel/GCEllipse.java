package frontend.gcmodel;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

public class GCEllipse extends Ellipse implements GCFigure{

    private Paint color;
    private boolean shadow, bisel, grad;

    public GCEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        gc.setFill( (grad) ? gradColor() : color );
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), 
			getCenterPoint().getY() - (getsMinorAxis() / 2), 
			getsMayorAxis(), getsMinorAxis());
		gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), 
			getCenterPoint().getY() - (getsMinorAxis() / 2), 
			getsMayorAxis(), getsMinorAxis());
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
