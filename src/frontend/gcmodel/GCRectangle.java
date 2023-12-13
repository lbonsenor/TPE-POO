package frontend.gcmodel;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GCRectangle extends Rectangle implements GCFigure{

    private Color color;
    private boolean shadow, bisel, grad;

    public GCRectangle(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
			Math.abs(getTopLeft().getX() - getBottomRight().getX()), 
			Math.abs(getTopLeft().getY() - getBottomRight().getY()));
		gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
			Math.abs(getTopLeft().getX() - getBottomRight().getX()), 
			Math.abs(getTopLeft().getY() - getBottomRight().getY()));
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
