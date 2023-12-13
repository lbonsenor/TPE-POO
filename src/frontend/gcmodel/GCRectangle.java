package frontend.gcmodel;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

public class GCRectangle extends Rectangle implements GCFigure{

    private Paint color;
    private boolean shadow, bisel, grad;

    public GCRectangle(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        if (shadow) {
            gc.setFill(Color.GRAY);
            gc.fillRect(getTopLeft().getX() + 10.0,
                getTopLeft().getY() + 10.0,
                Math.abs(getTopLeft().getX() - getBottomRight().getX()),
                Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        }
        if (bisel) {
            double x = getTopLeft().getX();
            double y = getTopLeft().getY();
            double width = Math.abs(x - getBottomRight().getX());
            double height = Math.abs(y - getBottomRight().getY());
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeLine(x, y, x + width, y);
            gc.strokeLine(x, y, x, y + height);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x + width, y, x + width, y + height);
            gc.strokeLine(x, y + height, x + width, y + height);
        }
        gc.setFill( (grad) ? gradColor() : color );
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
			Math.abs(getTopLeft().getX() - getBottomRight().getX()), 
			Math.abs(getTopLeft().getY() - getBottomRight().getY()));
		gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
			Math.abs(getTopLeft().getX() - getBottomRight().getX()), 
			Math.abs(getTopLeft().getY() - getBottomRight().getY()));
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
