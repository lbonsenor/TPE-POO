package frontend.paintFigures;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class PaintFigure implements Movable, Drawable{

    private Color fillColor;
    private Color borderColor;
    protected Figure figure;
    protected GraphicsContext gc;

    public PaintFigure(Figure figure, GraphicsContext gc, Color fillColor, Color borderColor) {
        this.figure = figure;
        this.gc = gc;
        setBorderColor(borderColor);
        setFillColor(fillColor);
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void changePos(double diffX, double diffY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePos'");
    }
}
