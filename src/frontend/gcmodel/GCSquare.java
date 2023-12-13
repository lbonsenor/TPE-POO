package frontend.gcmodel;

import backend.model.Point;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

public class GCSquare extends Square implements GCFigure{

    private Paint color;
    private boolean shadow, bisel, grad;

    public GCSquare(Point topLeft, double size) {
        super(topLeft, size);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        new GCRectangle(getTopLeft(), getBottomRight()).createFigure(gc);
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
